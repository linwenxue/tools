package com.lin.hadoop.test;

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
 *
 */


public class MyReducer extends MapReduceBase
        implements Reducer<Text, IntWritable,
            Text, IntWritable> {
    @Override
    public void reduce(Text key, Iterator<IntWritable> values,
                       OutputCollector<Text, IntWritable> output,
                       Reporter reporter) throws IOException {
        int maxValue = 0;
        while (values.hasNext()){
            maxValue += values.next().get();
        }

        output.collect(key, new IntWritable(maxValue == 0 ? 1 : maxValue));
    }
}
