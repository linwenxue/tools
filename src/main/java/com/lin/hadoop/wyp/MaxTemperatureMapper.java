package com.lin.hadoop.wyp;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;

/**
 * User: wyp
 * Date: 13-10-25
 * Time: 下午3:26
 * Email:wyphao.2007@163.com
 */
public class MaxTemperatureMapper extends MapReduceBase
        implements Mapper<LongWritable, Text,
        Text,IntWritable>{
    private static final int MISSING = 9999;

    @Override
    public void map(LongWritable key, Text value,
                    OutputCollector<Text, IntWritable> output,
                    Reporter reporter) throws IOException {

        String line = value.toString();
        String year = line.substring(15, 19);
        int airTemperature;
        if(line.charAt(87) == '+'){
            airTemperature = Integer.parseInt(line.substring(88, 92));
        }else{
            airTemperature = Integer.parseInt(line.substring(87, 92));
        }

        String quality = line.substring(92, 93);
        if(airTemperature != MISSING && quality.matches("[01459]")){
            output.collect(new Text(year), new IntWritable(airTemperature));
        }
    }
}
