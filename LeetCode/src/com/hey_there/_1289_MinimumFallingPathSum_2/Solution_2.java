package com.hey_there._1289_MinimumFallingPathSum_2;

//动态规划
public class Solution_2 {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        //dp[i][j]表示，当偏移下降路径从grid[i][j]开始时的最小路径和
        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++)//base case
            dp[n - 1][j] = grid[n - 1][j];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                //当前行选择第j列时，下一行可选择的最小值
                int minNextLine = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (k == j) continue;
                    minNextLine = Math.min(minNextLine, dp[i + 1][k]);
                }
                dp[i][j] = grid[i][j] + minNextLine;
            }
        }
        //遍历dp[0]，取最小值
        int ans = Integer.MAX_VALUE;
        for (int s : dp[0])
            ans = Math.min(ans, s);
        return ans;
    }
}
