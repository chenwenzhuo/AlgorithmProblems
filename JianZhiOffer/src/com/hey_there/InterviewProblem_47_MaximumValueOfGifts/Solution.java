package com.hey_there.InterviewProblem_47_MaximumValueOfGifts;

public class Solution {
    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int gRows = grid.length;
        int gCols = grid[0].length;
        //dp[i][j]表示走到坐标(i,j)的位置能得到的最大价值
        int[][] dp = new int[gRows][gCols];
        //初始化dp数组的第一行和第一列
        dp[0][0] = grid[0][0];
        for (int i = 1; i < gCols; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < gRows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < gRows; i++) {
            for (int j = 1; j < gCols; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[gRows - 1][gCols - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};

        Solution solution = new Solution();
        int maxVal = solution.maxValue(grid);
        System.out.println("maxVal  " + maxVal);
    }
}
