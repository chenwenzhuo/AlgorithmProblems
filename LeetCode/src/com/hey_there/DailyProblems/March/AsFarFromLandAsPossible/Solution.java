package com.hey_there.DailyProblems.March.AsFarFromLandAsPossible;

public class Solution {
    public int maxDistance(int[][] grid) {
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 1) {
                    dp[i][j] = 0;
                } else if (grid[i][j] == 0) {
                    //使用Short.MAX_VALUE表示无穷大，Integer.MAX_VALUE可能产生溢出
                    dp[i][j] = Short.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 1 || (i == 0 && j == 0)) {
                    continue;
                }
                int leftGrid;
                if (j == 0) {
                    leftGrid = Short.MAX_VALUE;
                } else {
                    leftGrid = dp[i][j - 1];
                }
                int upGrid;
                if (i == 0) {
                    upGrid = Short.MAX_VALUE;
                } else {
                    upGrid = dp[i - 1][j];
                }
                dp[i][j] = Math.min(dp[i][j], Math.min(leftGrid, upGrid) + 1);
            }
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = columns - 1; j >= 0; j--) {
                if (grid[i][j] == 1 || (i == rows - 1 && j == columns - 1)) {
                    continue;
                }
                int rightGrid;
                if (j == columns - 1) {
                    rightGrid = Short.MAX_VALUE;
                } else {
                    rightGrid = dp[i][j + 1];
                }
                int downGrip;
                if (i == rows - 1) {
                    downGrip = Short.MAX_VALUE;
                } else {
                    downGrip = dp[i + 1][j];
                }
                dp[i][j] = Math.min(dp[i][j], Math.min(rightGrid, downGrip) + 1);
            }
        }

        int ans = -1;
        for (int[] dpRow : dp) {
            for (int dpGrid : dpRow) {
                if (dpGrid > 0 && dpGrid < Short.MAX_VALUE && dpGrid > ans) {
                    ans = dpGrid;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //int[][] grid = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        int[][] grid = {
                {0, 0, 1, 1, 1},
                {0, 1, 1, 0, 0},
                {0, 0, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 1}};
        Solution solution = new Solution();
        System.out.println(solution.maxDistance(grid));
    }
}
