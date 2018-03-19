package com.lin.java.sort;

import java.util.Arrays;

/**
 * 快速排序：
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
            while (left < right && n[right] >= pivot)
                right--;
            if (left < right)
                n[left++] = n[right];
            while (left < right && n[left] <= pivot)
                left++;
            if (left < right)
                n[right--] = n[left];
        }
        n[left] = pivot;
        return left;
    }


    public static void main(String[] args) {
        QuickSort sorter = new QuickSort();
        int[] a = {49,38,65,97,76,13,27,49,78,34,12,64,5,4,62,99,98,54,56,17,18,23,34,15,35,25,53,51};
        sorter.quicksort(a, 0, a.length - 1);
        Arrays.stream(a).boxed().map(x -> String.valueOf(x) + " ").forEach(System.out::print);
    }
}
