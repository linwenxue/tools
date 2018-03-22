package com.lin.java.sort;

import java.util.Arrays;

/**
 * 快速排序基本思想：
 * 通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 */
public class QuickSort {

    public void quicksort(int n[], int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(n, left, right);
            quicksort(n, left, dp - 1);
            quicksort(n, dp + 1, right);
        }
    }


    public int partition(int n[], int left, int right) {
        int pivot = n[left];
        while (left < right) {
            while (left < right && n[right] > pivot)
                right--;
            if (left < right)
                n[left++] = n[right];
            while (left < right && n[left] < pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }


    /*public int partition2(int[] n, int start, int end) {
        int flag = n[start];
        while (start < end) {
            while(start < end && n[end] > flag) end--;
            n[start++] = n[end];
            while(start < end && n[start] < flag) start++;
            n[end--] = n[start];
        }
        n[start] = flag;
        return start;
    }*/


    public static void main(String[] args) {
        QuickSort sorter = new QuickSort();
        int[] a = {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
        sorter.quicksort(a, 0, a.length - 1);
        Arrays.stream(a).boxed().map(x -> String.valueOf(x) + " ").forEach(System.out::print);
        System.out.println();
    }
}
