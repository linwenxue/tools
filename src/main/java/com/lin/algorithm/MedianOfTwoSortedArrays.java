package com.lin.algorithm;


import com.lin.Print;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        if(nums1[len1-1]<=nums2[0])  {
            return getMidValue(nums1, nums2);
        } else if(nums2[len2-1]<=nums1[0]) {
            return getMidValue(nums2, nums1);
        } else {
            int[] nums3 = new int[nums1.length+nums2.length];
            int index = 0;
            int flag = 0;
            //int[] num1 = new int[]{2, 5, 6, 7, 8, 9};
            //int[] num2 = new int[]{1, 3, 4};
            for(int i=0; i<nums1.length; i++) {
                if(flag == nums2.length-1) {
                    nums3[index++] = nums1[i];
                    continue;
                }
                for(int j=flag; j<nums2.length; j++) {
                    flag = j;
                    if(nums1[i]<=nums2[j] && i!=nums1.length-1) {
                        nums3[index++] = nums1[i];
                        break;
                    } else {
                        nums3[index++] = nums2[j];
                    }
                }
            }
            if(nums3.length%2 == 0) {
                int start = nums3[nums3.length/2];
                int end = nums3[nums3.length/2+1];
                return (start+end)/2.0d;
            } else {
                return nums3[(nums3.length+1)/2];
            }
        }
    }


    public double getMidValue(int[] min, int[] max) {
        int len1 = min.length, len2 = max.length;
        int size = len1+len2;
        if(size % 2 == 0) {
            int midIndexStart = size/2;
            int midIndexEnd = size/2+1;
            int start = midIndexStart>len1 ? max[midIndexStart-len1-1] : min[midIndexStart-1];
            int end = midIndexEnd>len1 ? max[midIndexEnd-len1-1] : min[midIndexEnd-1];
            return (start+end)/2.0d;
        } else {
            int midIndex = (size+1)/2;
            if(midIndex>len1)
                return max[midIndex-len1-1];
            else
                return min[midIndex-1];
        }

    }


    public static void main(String[] args) {
        int[] num1 = new int[]{2, 5, 6, 7, 8, 9};
        int[] num2 = new int[]{1, 3, 4};
        Print.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(num1, num2));
    }
}
