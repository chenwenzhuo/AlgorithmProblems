package com.hey_there.JD_August_27th;

import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读入三角形层数n
        int n = Integer.parseInt(sc.nextLine());
        //读入三角形
        int matrixLineLen = 2 * n + 1;//matrix数组的一行的长度
        int[][] matrix = new int[n][matrixLineLen];
        //循环读入三角形的第i层
        for (int i = 0; i < n; i++) {
            String[] curLevelStr = sc.nextLine().trim().split(" ");
            int triangleLineLen = 2 * (i + 1) - 1;//三角形当前行的长度
            int spacesLeft = (matrixLineLen - triangleLineLen) / 2;
            for (int j = 0; j < triangleLineLen; j++) {
                matrix[i][spacesLeft + j] = Integer.parseInt(curLevelStr[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < matrixLineLen; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        //dp[i][j]表示球滚到坐标为[i,j]的格子的最大可能得分
        int[][] dp = new int[n][matrixLineLen];
        dp[0][n] = matrix[0][n];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < matrixLineLen - 1; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i - 1][j], dp[i - 1][j + 1])) + matrix[i][j];
            }
        }
        //遍历最后一行，获得最大得分
        int maxScore = -1;
        for (int i = 1; i < matrixLineLen - 1; i++) {
            maxScore = Math.max(maxScore, dp[n - 1][i]);
        }
        System.out.println(maxScore);
    }
}
