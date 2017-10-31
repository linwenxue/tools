package com.lin.algorithm;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 */
public class MaximumProductSubarray {
    /**
     * Note:
     * There's no need to use O(n) space, as all that you need is a minhere and maxhere.
     * (local max and local min), then you can get maxsofar (which is global max) from them.
     * There's a chapter in Programming Pearls 2 that discussed the MaxSubArray problem, the idea is similar.
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int maxherepre = nums[0];
        int minherepre = nums[0];
        int maxsofar = nums[0];
        int maxhere, minhere;

        for (int i = 1; i < nums.length; i++) {
            maxhere = Math.max(Math.max(maxherepre * nums[i], minherepre * nums[i]), nums[i]);
            minhere = Math.min(Math.min(maxherepre * nums[i], minherepre * nums[i]), nums[i]);
            maxsofar = Math.max(maxhere, maxsofar);
            maxherepre = maxhere;
            minherepre = minhere;
        }
        return maxsofar;
    }


    public static void main(String[] args) {
        int[] array = new int[]{-2, 0, -1};
        System.out.println(new MaximumProductSubarray().maxProduct(array));
    }
}
