package com.hey_there._1155_NumberOfDiceRollsWithTargetSum;

public class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        //n个骰子，点数最小为n，最大为n*k，target不在范围内时返回0
        if (target < n || target > n * k)
            return 0;
        //dp[i][j]表示用i个骰子扔出点数j的方式数
        int[][] dp = new int[n + 1][target + 1];
        /* base case：一个骰子，扔出点数1到k，只有一种方式
         * i个骰子扔出点数i，只有一种方式*/
        for (int j = 1; j <= k && j <= target; j++)
            dp[1][j] = 1;
        for (int i = 1; i <= n; i++)
            dp[i][i] = 1;
        //计算其他情况
        for (int i = 2; i <= n; i++) {
            for (int j = i + 1; j <= target; j++) {
                //i个骰子，点数最小为i，最大为i*k，j不在范围内时保持默认值0
                if (j > i * k)
                    continue;
                //第i个骰子扔出点数x（1<=x<=k）时，累加dp[i-1][j-x]
                for (int x = 1; x <= k && x < j; x++) {
                    dp[i][j] += dp[i - 1][j - x];
                    dp[i][j] %= 1000000007;
                }
            }
        }
        return dp[n][target];
    }
}
