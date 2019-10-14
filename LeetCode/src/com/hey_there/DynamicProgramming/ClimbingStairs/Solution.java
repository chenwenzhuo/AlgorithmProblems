package com.hey_there.DynamicProgramming.ClimbingStairs;

public class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        //数组中第i个元素表示爬到第i级台阶的方式数
        int[] waysOfClimbing = new int[n];

        //前两个元素直接初始化
        waysOfClimbing[0] = 1;
        waysOfClimbing[1] = 2;

        for (int i = 2; i < n; i++) {
            waysOfClimbing[i] = waysOfClimbing[i - 1] + waysOfClimbing[i - 2];
        }

        return waysOfClimbing[n - 1];
    }
}
