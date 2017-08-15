package com.lin.study.hadoop.wyp;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import java.io.IOException;

/**
 * User: wyp
 * Date: 13-10-25
 * Time: 下午3:40
 * Email:wyphao.2007@163.com
 */
public class MaxTemperature {

    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.err.println("Error!");
            System.exit(1);
        }

        JobConf conf = new JobConf(MaxTemperature.class);
        conf.setJobName("Max Temperature");

        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));
        conf.setMapperClass(MaxTemperatureMapper.class);
        conf.setReducerClass(MaxTemperatureReducer.class);
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        JobClient.runJob(conf);

    }
}