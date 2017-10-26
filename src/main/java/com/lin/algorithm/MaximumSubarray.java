package com.lin.algorithm;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int si = nums[0];
        int ans = nums[0];
        int minSum = nums[0];
        for(int i = 1; i < nums.length; i++) {
            minSum = Math.min(minSum, si + nums[i]);
            ans = Math.max(ans, si + nums[i] - minSum);
            si += nums[i];
        }
        return ans;
    }

    public int maxSubArray2(int[] nums) {
        int sum = nums[0];
        int ans = nums[0];
        for(int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            ans = Math.max(ans, sum);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] array = new int[]{-2, 1};
        System.out.println(new MaximumSubarray().maxSubArray2(array));
    }
}
