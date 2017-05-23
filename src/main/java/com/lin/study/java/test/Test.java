package com.lin.study.java.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
* @Description: 测试类
* @author linwenxue
* @date 2015/12/30
*/
public class Test {

    public static void main(String[] args) throws Exception{
        //String utfCode = "%E7%AE%80%E5%8E%86ppt%E6%A8%A1%E6%9D%BF";
        //System.out.println(URLEncoder.encode("简历模板免费下载", "UTF-8"));
        ////computeNumber()
        //InetAddress addr = InetAddress.getLocalHost();
        //String p = addr.getHostAddress();//获得本机IP
        //String address = addr.getHostName();//获得本机名称

        //System.out.println(Integer.MIN_VALUE);
        //String regStr = "aaa[ddd]ddd[]ccc";
        //String[] strings = regStr.split("[\\[\\]]");
        //for(String s : strings) {
        //   System.out.println(s);
        System.out.println("aa-aa".split("-")[0]);
        System.out.println("aa-aa".split("-")[1]);
        System.out.println("--------------------------");
        System.out.println(Object.class.isAssignableFrom(Test.class));
        System.out.println(new Test() instanceof Object);
        System.out.println(int.class.isPrimitive());

    }

    public static String SMSsend(String url) {
        String result = "";
        try{
            URL U = new URL(url);
            URLConnection connection = U.openConnection();
            connection.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine())!= null)
            {
                result += line;
            }
            in.close();
        }catch(Exception e){
            System.out.println("没有结果！"+e);
            result="产生异常";
        }
        return result;
    }

    public static void computeNumber() {
        int total = 0;
        for(int i = 1; i < 5; i++)
            for(int j = 1; j < 5; j++)
                for(int k = 1; k < 5; k++) {
                    if(i != j && i != k && j != k) {
                        total++;
                        System.out.println(i*100 + j*10 + k);
                    }
                }

        System.out.println("total:"+total);
    }


    @org.junit.Test
    public void test() {
        System.out.println(String.format("aaa_%d", 1));
    }
}
