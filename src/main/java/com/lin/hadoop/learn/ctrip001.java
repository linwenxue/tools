package com.lin.study.hadoop.learn;


import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ctrip001 {
    public static void main(String[] args) throws Exception {
        String In = "/user/hue/new.txt";
        String Out = "/user/hue/new1.txt";

        Configuration conf = new Configuration();
        //conf.set("mapred.job.tracker", "m04.ct1.r01.hdp:9001");
        Job job = new Job(conf, "Hotel");
        job.setJarByClass(ctrip001.class);
        FileSystem fs = FileSystem.get(conf);
        fs.delete(new Path(Out), true);

        FileInputFormat.addInputPath(job, new Path(In));
        FileOutputFormat.setOutputPath(job, new Path(Out));

        job.setMapperClass(HotelMap.class);
        job.setMapOutputKeyClass(Hotel.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setReducerClass(HotelReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(5); // 根据酒店地标数据的行数，增减reduce。为了保证速度，20多亿行数据差不多要500到1000个Reduce（根据不同机型改变配置）
        job.setGroupingComparatorClass(HotelGrouping.class);
        job.setPartitionerClass(HotelPartitioner.class);
        job.waitForCompletion(true);
    }

    public static class HotelMap extends Mapper<LongWritable, Text, Hotel, NullWritable> {

        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            Hotel hotel = new Hotel();
            hotel.setHotel(value);
            context.write(hotel, NullWritable.get()); // 酒店或地标
            if (hotel.getType() == 0) { // 为酒店冗余8个中心点

                hotel.setXYa(1, 0);
                context.write(hotel, NullWritable.get());
                hotel.setXYa(1, 1);
                context.write(hotel, NullWritable.get());
                hotel.setXYa(1, -1);
                context.write(hotel, NullWritable.get());

                hotel.setXYa(-1, 0);
                context.write(hotel, NullWritable.get());
                hotel.setXYa(-1, 1);
                context.write(hotel, NullWritable.get());
                hotel.setXYa(-1, -1);
                context.write(hotel, NullWritable.get());

                hotel.setXYa(0, 1);
                context.write(hotel, NullWritable.get());
                hotel.setXYa(0, -1);
                context.write(hotel, NullWritable.get());

            }
            return;
        }
    }

    public static class HotelReducer extends Reducer<Hotel, NullWritable, Text, NullWritable> {

        @SuppressWarnings("unused")
        public void reduce(Hotel key, Iterable<NullWritable> value, Context context) throws IOException, InterruptedException {
            int count = 0;
            ArrayList<Hotel> MyHotel = new ArrayList<Hotel>(); // 一个group中可能会有多个酒店，用arraylist作为酒店容器
            for (NullWritable x : value) {
                if (count == 0 && key.getType() != 0)
                    break;
                if (key.getType() == 0) {
                    try {
                        MyHotel.add((Hotel) key.clone());
                    }
                    catch (CloneNotSupportedException e) {}
                } else {
                    for (Hotel h : MyHotel) {
                        double dis = Math.sqrt(Math.pow(h.getX() - key.getX(), 2) +
                                Math.pow(h.getY() - key.getY(), 2));
                        if (dis <= 1000) {
                            // 输出格式 ：酒店ID 地标ID 距离（测试用）。抛弃没有酒店关系的地标数据
                            context.write(new Text(h.getID() + "\t" + key.getID() + "\t"
                                    + (int) dis), NullWritable.get());
                        }
                    }
                }
                ++count;
            }
        }
    }
}

// 把酒店地标数据重新划分到不同的reduce
class HotelPartitioner extends Partitioner<Hotel, NullWritable> {
    public int getPartition(Hotel key, NullWritable value, int numPartitions) {
        return Math.abs((int) ((key.getXa() + key.getYa()) % numPartitions));
    };
}

//自定义Group 根据1000*1000的块重新洗牌聚合
class HotelGrouping extends WritableComparator
{
    protected HotelGrouping()
    {
        super(Hotel.class, true);
    }

    @SuppressWarnings("rawtypes")
    @Override
    // 比较 WritableComparables.
    public int compare(WritableComparable w1, WritableComparable w2)
    {
        Hotel h1 = (Hotel) w1;
        Hotel h2 = (Hotel) w2;
        int r1x = h1.getXa();
        int r1y = h1.getYa();
        int r2x = h2.getXa();
        int r2y = h2.getYa();
        int R = 0;
        if (r1x == r2x) {
            if (r1y == r2y) {
                R = 0;
            } else {
                if (r1y > r2y) {
                    R = 1;
                }
                else {
                    R = -1;
                }
            }
        } else {
            if (r1x > r2x) {
                R = 1;
            }
            else {
                R = -1;
            }
        }
        return R;
    }
}


class Hotel implements WritableComparable<Hotel>, Cloneable {

    private IntWritable Type;
    private IntWritable ID;
    private IntWritable X;
    private IntWritable Y;
    private IntWritable Xa;
    private IntWritable Ya;
    private DoubleWritable Distance;

    {
        Type = new IntWritable();
        ID = new IntWritable();
        X = new IntWritable();
        Y = new IntWritable();
        Xa = new IntWritable();
        Ya = new IntWritable();
        Distance = new DoubleWritable();
    }

    public int getType() {
        return Type.get();
    }

    public int getID() {
        return ID.get();
    }

    public int getX() {
        return X.get();
    }

    public int getY() {
        return Y.get();
    }

    public int getXa() {
        return Xa.get();
    }

    public int getYa() {
        return Ya.get();
    }

    public void setXYa(int xd, int yd) {
        this.Xa.set(X.get() / 1000 + xd);
        this.Ya.set(Y.get() / 1000 + yd);
    }

    public double getDistance() {
        return Distance.get();
    }

    public void setHotel(Text Str) {
        String[] v = Str.toString().split("\t");

        Type.set(Integer.parseInt(v[0]));
        ID.set(Integer.parseInt(v[1]));
        X.set(Integer.parseInt(v[2]));
        Y.set(Integer.parseInt(v[3]));
        Xa.set(Integer.parseInt(v[2]) / 1000);
        Ya.set(Integer.parseInt(v[3]) / 1000);
        Distance.set(Math.sqrt(Math.pow(getX(), 2) + Math.pow(getX(), 2)));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    @Override
    public void readFields(DataInput In) throws IOException {
        Type = new IntWritable(In.readInt());
        ID = new IntWritable(In.readInt());
        X = new IntWritable(In.readInt());
        Y = new IntWritable(In.readInt());
        Xa = new IntWritable(In.readInt());
        Ya = new IntWritable(In.readInt());
        Distance = new DoubleWritable(In.readDouble());
    }

    @Override
    public void write(DataOutput Out) throws IOException {
        Out.writeInt(Type.get());
        Out.writeInt(ID.get());
        Out.writeInt(X.get());
        Out.writeInt(Y.get());
        Out.writeInt(Xa.get());
        Out.writeInt(Ya.get());
        Out.writeDouble(Distance.get());
    }

    @Override
    public int compareTo(Hotel h) {
        int R = 0;
        if (this.getXa() == h.getXa()) {
            if (this.getYa() == h.getYa()) {
                R = this.getType() - h.getType(); // 实现shuffle阶段二次排序，把酒店放在每个group的最前面
            } else {
                if (this.getYa() > h.getYa()) {
                    R = 1;
                }
                else {
                    R = -1;
                }
            }
        } else {
            if (this.getXa() > h.getXa()) {
                R = 1;
            }
            else {
                R = -1;
            }
        }
        return R;
    }

    @Override
    public String toString() {
        return getXa() + "\t" + getYa() + "\t" + getType() + "\t" + getID() + "\t" + getX() + "\t" + getY();
    }
}

