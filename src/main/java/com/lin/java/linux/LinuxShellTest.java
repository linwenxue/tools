package com.lin.java.linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by linwenxue on 2015/1/5.
 */
public class LinuxShellTest {
    public static void main(String[] args) {
        MyThread mt = new LinuxShellTest().new MyThread();
        mt.start();
    }

    class MyThread extends Thread {
        private volatile Process process;
        public void run() {
            String[] command = {"hive.sh"};
            ProcessBuilder builder = new ProcessBuilder(command);
            try {
                Iterator<Map.Entry<String, String>> iterator = builder.environment().entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    System.out.println("key-value:"+next.getKey()+"="+next.getValue());
                }
                process = builder.start();
                StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR");
                StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream(), "OUTPUT");
                errorGobbler.start();
                outputGobbler.start();
                int exitVal = process.waitFor();
                System.out.println("ExitValue: " + exitVal);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                process.destroy();
            }
        }
    }

    class StreamGobbler extends Thread {
        InputStream is;
        String type;
        StreamGobbler(InputStream is, String type){
            this.is = is;
            this.type = type;
        }

        public void run(){
            try{
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }
}

