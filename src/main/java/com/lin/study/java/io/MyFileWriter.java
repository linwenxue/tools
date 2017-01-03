package com.lin.study.java.io;

import org.junit.Test;
import java.io.*;

/**
 * Created by linwenxue on 2015/9/21.
 */
public class MyFileWriter {

    public void write(String file, String content) throws IOException{
        FileOutputStream out = new FileOutputStream(new File(file));
        out.write(content.getBytes());
        out.close();
    }


    public void write1(String file, String content) throws IOException{
        FileOutputStream out = new FileOutputStream(new File(file));
        BufferedOutputStream buffWriter = new BufferedOutputStream(out);
        buffWriter.write(content.getBytes());
        buffWriter.flush();
        buffWriter.close();
        out.close();
    }

    public void write2(String file, String content) throws IOException{
        FileWriter fw = new FileWriter(file, true);
        fw.write(content);
        fw.flush();
        fw.close();
    }


    @Test
    public void test() {
    }
}
