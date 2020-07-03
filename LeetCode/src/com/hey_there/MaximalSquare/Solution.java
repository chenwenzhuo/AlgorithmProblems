package com.hey_there.MaximalSquare;

public class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int mxRows = matrix.length;
        int mxColumns = matrix[0].length;

        int maxSquareSide = 0;//最大边长
        //dp[i][j]表示以[i,j]位置为右下角的最大正方形边长
        int[][] dp = new int[mxRows][mxColumns];
        //初始化dp数组的第一行和第一列
        for (int i = 0; i < mxColumns; i++) {
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                maxSquareSide = 1;
            }
        }
        for (int i = 1; i < mxRows; i++) {
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                maxSquareSide = 1;
            }
        }
        //计算dp数组中其他位置的值并尝试更新maxSquareSide
        for (int i = 1; i < mxRows; i++) {
            for (int j = 1; j < mxColumns; j++) {
                //matrix[i][j]为1时，计算dp[i][j]的值
                if (matrix[i][j] == '1') {
                    //检查dp[i][j]的左边和上方位置是否存在值为0
                    if (dp[i][j - 1] == 0 || dp[i - 1][j] == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                    }
                    maxSquareSide = Math.max(maxSquareSide, dp[i][j]);
                }
            }
        }
        return maxSquareSide * maxSquareSide;
    }
}
