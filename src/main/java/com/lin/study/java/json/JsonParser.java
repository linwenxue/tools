package com.lin.study.java.json;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by wenxuelin on 2017/2/14.
 */
public class JsonParser {

    @Test
    public void parse() {
        File file = new File("/Users/wenxuelin/Downloads/test.txt");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                Map<String, Object> values = mapper.readValue(tempString, Map.class);
                Map<String, Object> aa = (Map<String, Object>)values.get("createdDate");
                System.out.println("time :" + aa.get("time"));

                for(Map.Entry<String, Object> e : values.entrySet()) {
                    System.out.println(e.getKey()+":"+e.getValue());
                }
                System.out.println("line " + line + ": " + tempString);
                System.out.println("____________________________________");
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
     }
}
