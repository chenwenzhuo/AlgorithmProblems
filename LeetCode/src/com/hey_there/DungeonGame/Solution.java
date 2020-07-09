package com.hey_there.DungeonGame;

import java.util.Arrays;

public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int dRows = dungeon.length;
        int dCols = dungeon[0].length;
        //dp[i][j]表示从坐标[i,j]的格子开始，走到右下角所需的最少初始生命值
        int[][] dp = new int[dRows + 1][dCols + 1];
        for (int[] minHPRow : dp) {
            Arrays.fill(minHPRow, Integer.MAX_VALUE);
        }
        dp[dRows][dCols - 1] = dp[dRows - 1][dCols] = 1;
        for (int i = dRows - 1; i >= 0; --i) {
            for (int j = dCols - 1; j >= 0; --j) {
                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(1, minn - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        Solution solution = new Solution();
        int minInitHP = solution.calculateMinimumHP(dungeon);
        System.out.println(minInitHP);
    }
}
