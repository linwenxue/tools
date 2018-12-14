package com.lin.java.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
* @Description: 重复时间段去重
* @author linwenxue
* @date 2015/12/30
*/
public class TimePeriodDup {

    public static void main(String[] args) {

        List<String[]> listIn = new ArrayList<String[]>();
        String[] a1 = {"01:01:23","02:30:30"};
        String[] a2 = {"03:00:00","04:30:25"};
        String[] a3 = {"04:10:00","06:30:00"};
        String[] b = {"07:30:00","11:01:21"};
        String[] c = {"11:00:00","21:25:00"};
        listIn.add(a1);
        listIn.add(a2);
        listIn.add(a3);
        listIn.add(c);
        listIn.add(b);
        List t = fib(listIn);
        for(int i = 0; i < t.size(); i++) {
            String[] s = (String[])t.get(i);
            System.out.println(s[0]);
            System.out.println(s[1]);
            System.out.println("----------");
        }
    }

    public static List<String[]> fib(List<String[]>listIn){
        Collections.sort(listIn, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return o1[0].compareTo(o2[0]);
            }
        });
        List<String[]> listOut = new ArrayList<String[]>();
        String begin = "";
        String end = "";
        for (int i = 0,size = listIn.size();i < size;i++){
            String[] tmp = listIn.get(i);
            if(i==0){
                begin = tmp[0];
                end = tmp[1];
            }else{
                if(tmp[0].compareTo(end) <=0 ){
                    if(tmp[1].compareTo(end) >0){
                        end = tmp[1];
                    }
                }else{
                    String[] str = {begin,end};
                    listOut.add(str);
                    begin = tmp[0];
                    end = tmp[1];
                }
            }
            if(i==(size-1)){
                String[] str = {begin,end};
                listOut.add(str);
            }
        }
        return listOut;
    }
}
