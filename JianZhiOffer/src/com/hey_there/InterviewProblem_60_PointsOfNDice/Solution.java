package com.hey_there.InterviewProblem_60_PointsOfNDice;

import java.util.Arrays;

public class Solution {
    public double[] twoSum(int n) {
        //掷n个骰子能产生6n-n+1种不同的点数
        int diffKindsOfPoints = 5 * n + 1;
        //dp[i]表示点数之和为n+i的情况数量
        double[] dp = new double[diffKindsOfPoints];
        /* 初始化base case，将数组前6个位置赋值为1
         * 因为掷一个骰子能产生6种不同点数，每种点数只有一种组成方式*/
        Arrays.fill(dp, 0, 6, 1);

        //计算掷i个骰子的各个点数的不同组成方式数
        for (int i = 2; i <= n; i++) {
            diffKindsOfPoints = 5 * i + 1;//掷i个骰子能产生6i-i+1种不同的点数
            //要用前一次循环的结果来计算此次的值，避免产生覆盖，故从后往前计算
            for (int j = diffKindsOfPoints - 1; j > 0; j--) {
                double temp = 0;
                for (int k = 0; k <= 5; k++) {
                    if (j - k >= 0) temp += dp[j - k];
                    else break;
                }
                dp[j] = temp;
            }
        }
        //n个骰子的点数排列数有6^n种
        double arrangements = Math.pow(6, n);
        for (int i = 0; i < dp.length; i++) {
            dp[i] /= arrangements;
        }
        return dp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        double[] possibilities = solution.twoSum(2);
        for (double p : possibilities) {
            System.out.print(p + "   ");
        }
        System.out.println();
    }
}
