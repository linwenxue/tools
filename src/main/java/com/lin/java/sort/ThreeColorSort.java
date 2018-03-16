package com.lin.java.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 输入一个整型数组，每个元素在0~2之间，其中0，1，2分别代表红、白、蓝。
 * 现要求对数组进行排序，相同颜色的在一起，而且按红白蓝顺序先后排列。要求时间复杂度为O(n)。
 */
public class ThreeColorSort {
    public Integer[] sort(Integer[] array) {
        int low_pos = 0;
        int high_pos = array.length-1;

        for(int i = 0; i < array.length;) {
            if(array[i] == 0) {
                swap(array, i, low_pos);
                low_pos++;
                i++;
            } else if(array[i] == 2 && i<high_pos) {
                swap(array, i, high_pos);
                high_pos--;
            } else if(array[i] == 1 && i<high_pos){
                i++;
            } else if(i>=high_pos){
                break;
            }
        }
        return array;
    }

    private void swap(Integer[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    public Integer[] genIntArray(Integer length) {
        Integer[] array = new Integer[length];
        for(int i = 0; i < length; i++) {
            array[i] = new Random().nextInt(3);
        }
        return array;
    }



    public static void main(String[] args) {
        ThreeColorSort tcs = new ThreeColorSort();
        Integer[] array = tcs.genIntArray(10);
        Arrays.stream(array).forEach(System.out::print);
        System.out.println("\n");
        Arrays.stream(tcs.sort(array)).forEach(System.out::print);
    }
}
