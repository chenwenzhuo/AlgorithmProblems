package com.hey_there._327_CountOfRangeSum;

public class Solution {
    private long[] prefixSumTemp;

    public int countRangeSum(int[] nums, int lower, int upper) {
        //计算nums的前缀和数组
        long[] prefixSum = new long[nums.length + 1];
        for (int i = 1; i < prefixSum.length; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        prefixSumTemp = new long[prefixSumTemp.length];
        //对prefixSum归并排序，计算满足条件的区间数量
        return countRangeSum(prefixSum, 0, prefixSum.length - 1, lower, upper);
    }

    //返回值表示，子数组prefixSum[start,...,end]范围内，有多少子数组的和在区间[lower,upper]之间
    private int countRangeSum(long[] prefixSum, int start, int end, int lower, int upper) {
        if (start == end)
            return 0;
        int mid = (start + end) / 2;
        int leftRet = countRangeSum(prefixSum, start, mid, lower, upper);
        int rightRet = countRangeSum(prefixSum, mid + 1, end, lower, upper);
        //统计下标对的数量
        int ret = leftRet + rightRet;//prefixSum[start,...,end]范围内满足条件的子区间数量
        int L = mid + 1, R = mid + 1;
        for (int i = start; i <= mid; i++) {
            while (L <= end && prefixSum[L] < prefixSum[i] + lower)
                L++;
            while (R <= end && prefixSum[R] <= prefixSum[i] + upper)
                R++;
            ret += (R - L);
        }
        //合并子区间排序结果
        for (int i = start; i <= end; i++)//将prefixSum复制到prefixSumTemp
            prefixSumTemp[i] = prefixSum[i];
        //复用变量
        L = start;
        R = mid + 1;
        int p = start;
        while (L <= mid && R <= end) {
            if (prefixSumTemp[L] <= prefixSumTemp[R])
                prefixSum[p] = prefixSumTemp[L++];
            else prefixSum[p] = prefixSumTemp[R++];
            p++;
        }
        while (L <= mid) {
            prefixSum[p] = prefixSumTemp[L++];
            p++;
        }
        while (R <= end) {
            prefixSum[p] = prefixSumTemp[R++];
            p++;
        }
        return ret;
    }
}
