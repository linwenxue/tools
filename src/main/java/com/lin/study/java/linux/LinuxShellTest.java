package com.lin.study.java.linux;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
            String[] command = {"/home/gridhadoop/test.sh", "flowInstId"};
            ProcessBuilder builder = new ProcessBuilder(command);
            try {
                process = builder.start();
                // any error message?
                StreamGobbler errorGobbler = new
                        StreamGobbler(process.getErrorStream(), "ERROR");

                // any output?
                StreamGobbler outputGobbler = new
                        StreamGobbler(process.getInputStream(), "OUTPUT");

                // kick them off
                errorGobbler.start();
                outputGobbler.start();

                // any error???
                int exitVal = process.waitFor();
                System.out.println("ExitValue: " + exitVal);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // 获取脚本错误输出
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                //process.destroy();
            }
        }
    }

    class StreamGobbler extends Thread {
        InputStream is;
        String type;

        StreamGobbler(InputStream is, String type)
        {
            this.is = is;
            this.type = type;
        }

        public void run()
        {
            try
            {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
            } catch (IOException ioe)
            {
                ioe.printStackTrace();
            }
        }
    }
}

