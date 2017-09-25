package com.lin.java.sort;

import java.util.Arrays;

/**
 * Created by linwenxue on 2015/1/12.
 */
public class InsertSort {
    public static void main(String[] args) {
        InsertSort is = new InsertSort();
        int[] a = {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
        Arrays.stream(is.sort(a)).forEach(index -> System.out.println(index));
    }


    public int[] sort(int[] array) {
        for(int i=1; i<array.length; i++) {
            int key = array[i];
            int j = i-1;
            while(j>=0 && array[j]>key) {
                array[j+1] = array[j];
                j--;
            }
            array[++j] = key;
        }

        return array;
    }
}
