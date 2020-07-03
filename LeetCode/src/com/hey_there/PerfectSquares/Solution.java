package com.hey_there.PerfectSquares;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int numSquares(int n) {
        if (n <= 3) {
            return n;
        }
        //dp[i]表示和为i的完全平方数的最少个个数
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);//n最多由n个完全平方数相加得到
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        //当前已找到的完全平方数的集合
        Set<Integer> perfectSquares = new HashSet<>();
        perfectSquares.add(1);//起始时至少有一个完全平方数1
        for (int i = 4; i <= n; i++) {
            double sqrt = Math.sqrt(i);//求i的平方根
            //对平方根取整，若与原值相等，则i是完全平方数，dp[i]为1
            if (sqrt == ((int) sqrt)) {
                dp[i] = 1;
                perfectSquares.add(i);//找到一个完全平方数，将其加入结合
                continue;
            }
            //逐个枚举集合中的完全平方数
            for (int ps : perfectSquares) {
                dp[i] = Math.min(dp[i], dp[i - ps] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSquares(14253));
    }
}
