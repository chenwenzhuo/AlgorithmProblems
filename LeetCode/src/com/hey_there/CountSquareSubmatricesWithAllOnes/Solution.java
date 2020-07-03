package com.hey_there.CountSquareSubmatricesWithAllOnes;

public class Solution {
    public int countSquares(int[][] matrix) {
        int matrixRows = matrix.length;
        int matrixColumns = matrix[0].length;

        int totalSquares = 0;//全1正方形的总数
        //dp[i][j]表示以matrix[i][j]为右下角的正方形的个数
        int[][] dp = new int[matrixRows][matrixColumns];
        //初始化dp数组的第一行和第一列，值与matrix的第一行第一列相同
        //第一行
        for (int i = 0; i < matrixColumns; i++) {
            dp[0][i] = matrix[0][i];
            if (matrix[0][i] == 1) {
                //初始化的同时统计第一行第一列中1的数量
                totalSquares++;
            }
        }
        //第一列
        for (int j = 1; j < matrixRows; j++) {
            dp[j][0] = matrix[j][0];
            if (matrix[j][0] == 1) {
                totalSquares++;
            }
        }

        for (int i = 1; i < matrixRows; i++) {
            for (int j = 1; j < matrixColumns; j++) {
                if (matrix[i][j] == 0) {
                    //matrix[i][j]为0，则没有正方形以matrix[i][j]为右下角
                    dp[i][j] = 0;
                } else {
                    //对dp[i][j]左边，上边，左上方三个值取最小
                    int min = Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1]));
                    dp[i][j] = min + 1;
                    totalSquares += dp[i][j];
                }
            }
        }
        return totalSquares;
    }
}
