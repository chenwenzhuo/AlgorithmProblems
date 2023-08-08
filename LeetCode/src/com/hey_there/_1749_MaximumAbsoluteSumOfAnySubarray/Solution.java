package com.hey_there._1749_MaximumAbsoluteSumOfAnySubarray;

public class Solution {
    /* 子数组和的绝对值最大，对应两种情况：子数组和最大/最小
     * 两种情况分别计算，取绝对值较大者*/
    public int maxAbsoluteSum_1(int[] nums) {
        //记录以nums[i]结尾的子数组和，正数负数和分开记录
        int sumPositive = 0, sumNegative = 0;
        //记录nums数组的最大正数、最小负数子数组和
        int maxPos = 0, minNeg = 0;
        for (int num : nums) {
            sumPositive += num;
            sumNegative += num;
            //更新最大/最小子数组和
            maxPos = Math.max(sumPositive, maxPos);
            minNeg = Math.min(sumNegative, minNeg);
            //更新正数、负数和
            sumPositive = Math.max(0, sumPositive);
            sumNegative = Math.min(0, sumNegative);
        }
        return Math.max(maxPos, -minNeg);
    }

    //子数组和等于两个前缀和的差，则取前缀和中的最大值与最小值，作差就是答案
    public int maxAbsoluteSum_2(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        int maxPreSum = 0, minPreSum = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            maxPreSum = Math.max(maxPreSum, prefixSum[i]);
            minPreSum = Math.min(minPreSum, prefixSum[i]);
        }
        return maxPreSum - minPreSum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-3, -5, -3, -2, -6, 3, 10, -10, -8,
                -3, 0, 10, 3, -5, 8, 7, -9, -9, 5, -8};
        int res = solution.maxAbsoluteSum_2(nums);
        System.out.println(res);
    }
}
