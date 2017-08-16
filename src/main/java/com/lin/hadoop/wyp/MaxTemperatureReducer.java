package com.lin.hadoop.wyp;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.Iterator;

/**
 * User: wyp
 * Date: 13-10-25
 * Time: 下午3:36
 * Email:wyphao.2007@163.com
 */
public class MaxTemperatureReducer extends MapReduceBase
        implements Reducer<Text, IntWritable,
    Text, IntWritable> {
    @Override
    public void reduce(Text key, Iterator<IntWritable> values,
                       OutputCollector<Text, IntWritable> output,
                       Reporter reporter) throws IOException {
        int maxValue = Integer.MIN_VALUE;
        while (values.hasNext()){
            maxValue = Math.max(maxValue, values.next().get());
        }

        output.collect(key, new IntWritable(maxValue));
    }
}
