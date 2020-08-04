package com.hey_there.InterviewProblem_42_MaximumSubarray;

public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int length = nums.length;
        //dp[i]表示以nums[i]结尾的子数组的最大和
        int[] dp = new int[length];
        dp[0] = nums[0];
        int maxSubArrSum = Integer.MIN_VALUE;
        for (int i = 1; i < length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            maxSubArrSum = Math.max(maxSubArrSum, dp[i]);
        }
        return maxSubArrSum;
    }

    public int maxSubArray_1(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int length = nums.length;
        int prevMax = nums[0];
        int curMax;
        int maxSubArrSum = nums[0];
        for (int i = 1; i < length; i++) {
            curMax = Math.max(nums[i], prevMax + nums[i]);
            maxSubArrSum = Math.max(maxSubArrSum, curMax);
            prevMax = curMax;
        }
        return maxSubArrSum;
    }
}
