package com.lin.hadoop.test;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: wyp
 * Date: 13-10-25
 * Time: 下午3:26
 * Email:wyphao.2007@163.com
 */
public class MyMapper extends MapReduceBase
        implements Mapper<LongWritable, Text,
        Text,IntWritable>{

    @Override
    public void map(LongWritable key, Text value,
                    OutputCollector<Text, IntWritable> output,
                    Reporter reporter) throws IOException {

        String line = value.toString();
        String[] words = line.split(" ");
        HashMap<String, Integer> wordList = new HashMap<String, Integer>();
        for(String s : words) {
            if(wordList.size() == 0) {
                wordList.put(s,1);
            } else if(!wordList.containsKey(s)){
                wordList.put(s,1);
            } else {
                wordList.put(s,wordList.get(s)+1);
            }
        }

        for(Map.Entry s : wordList.entrySet()) {
            output.collect(new Text(s.getKey().toString()), new IntWritable(wordList.get(s.getKey().toString())));
        }
    }
}
