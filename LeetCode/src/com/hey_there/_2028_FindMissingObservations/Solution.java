package com.hey_there._2028_FindMissingObservations;

import java.util.Arrays;

public class Solution {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int total = (m + n) * mean;
        //计算rolls数组的和
        int sumRolls = 0;
        for (int r : rolls)
            sumRolls += r;
        int missingSum = total - sumRolls;//缺失的n个数之和
        //n个数之和不能小于n，不能大于6n
        if (missingSum < n || missingSum > 6 * n)
            return new int[]{};
        //计算n个数的平均值及余数
        int meanN = missingSum / n, remainder = missingSum % n;
        int[] res = new int[n];
        Arrays.fill(res, meanN);
        for (int i = 0; i < remainder; i++)
            res[i]++;
        return res;
    }
}
