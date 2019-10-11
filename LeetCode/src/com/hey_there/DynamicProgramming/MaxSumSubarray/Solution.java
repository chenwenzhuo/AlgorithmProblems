package com.hey_there.DynamicProgramming.MaxSumSubarray;

public class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;

        //maxSums数组的第i个元素表示以此元素结尾的所有子串的最大和
        int[] maxSums = new int[len];

        maxSums[0] = nums[0];//以第一个数字结尾的子串仅有一个，值为其本身
        int maxSubarraySum = maxSums[0];
        for (int i = 1; i < len; i++) {
            maxSums[i] = Math.max(nums[i], nums[i] + maxSums[i - 1]);
            //尝试更新maxSubarraySum的值
            maxSubarraySum = Math.max(maxSums[i], maxSubarraySum);
        }
        return maxSubarraySum;
    }
}
