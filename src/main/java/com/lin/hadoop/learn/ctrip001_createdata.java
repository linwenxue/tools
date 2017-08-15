package com.lin.study.hadoop.learn;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class ctrip001_createdata {
    public static void main(String[] args) throws Exception {
        // 现有100万酒店坐标和20亿地标,里面记录地标的经纬度，请设计mapreduce计算所有酒店1公里范围内的地标。
        // 类型(酒店/地标) id(酒店id/地标id) 纬度 经度
        // 假设一个1600平方公里的小城市 = 40 公里 * 40 公里 = 40000 米* 40000米
        // 假设有1000个酒店，1000,000个地标

        int len = 40000;
        int hotel = 1000;
        int LM = 1000;
        Random ran = new Random();
        int type = 0; // 0酒店 1地标
        /*String hdfs_path = "/tmp/hotel";
        Configuration hadoopconf = new Configuration();
        hadoopconf.set("fs.default.name", "hdfs://m04.ct1.r01.hdp:9000");
        FileSystem myFS = FileSystem.get(hadoopconf);
        OutputStream out = myFS.create(new Path(hdfs_path));*/

        File dest = new File("D:/new.txt");
        BufferedWriter writer  = new BufferedWriter(new FileWriter(dest));

        for (int i = 0; i < hotel; i++) { // 用两个for循环嵌套，打乱酒店/地标数据
            // 类型(酒店/地标) id(酒店id/地标id) 纬度 经度
            int hx = ran.nextInt(len);
            int hy = ran.nextInt(len);
            type = 0;
            StringBuilder str = new StringBuilder();
            str.append(type); // 类型(酒店/地标)
            str.append("\t");
            str.append(i * 10000); // id(酒店id/地标id)
            str.append("\t");
            str.append(hx); // 酒店 X
            str.append("\t");
            str.append(hy);// 酒店 Y
            str.append("\r\n");
            type = 1;
            for (int k = 0; k < LM; k++) {
                int lmx = ran.nextInt(len);
                int lmy = ran.nextInt(len);
                str.append(type); // 类型(酒店/地标)
                str.append("\t");
                str.append(i * 10000 + k + 1); // id(酒店id/地标id)
                str.append("\t");
                str.append(lmx); // 地标 X
                str.append("\t");
                str.append(lmy);// 地标 Y
                str.append("\r\n");
            }
            //byte[] midbytes1 = (str.toString()).getBytes("UTF8");
            writer.write(str.toString());

            //out.write(midbytes1);
            System.out.println(i * 1000);
        }
        writer.flush();
        writer.close();
        //out.close();
    }
}
