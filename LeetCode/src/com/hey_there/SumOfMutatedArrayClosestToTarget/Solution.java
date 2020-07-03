package com.hey_there.SumOfMutatedArrayClosestToTarget;

import java.util.Arrays;

public class Solution {
    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int len_arr = arr.length;
        //计算数组的前缀和
        int[] prefixSum = new int[len_arr + 1];
        for (int i = 1; i < len_arr; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i - 1];
        }
        //数组中的数，值的范围是[1,10000]，但答案的最大可能值为arr[n-1]
        int pAns = 0;//可能的答案，初值为0
        int ans = 0;
        int diff = Integer.MAX_VALUE;//差距初值设为“无穷大”
        int idx = 0;//遍历arr数组的下标
        while (pAns <= arr[len_arr - 1]) {
            //找到arr数组中第一个大于等于pAns当前值的下标
            while (idx < len_arr && arr[idx] < pAns) {
                idx++;
            }
            int mutatedSum = prefixSum[idx] + (len_arr - idx) * pAns;
            //当改变值的数组和超过target且差距在变大时，可直接退出循环
            //因为对于后续更大的pAns，mutatedSum值只会更大，差距也会更大
            if (mutatedSum > target && mutatedSum - target > diff) {
                break;
            }
            int curDiff = Math.abs(mutatedSum - target);
            if (curDiff < diff) {
                ans = pAns;
                diff = curDiff;
            }
            pAns++;
        }
        return ans;
    }
}
