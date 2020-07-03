package com.hey_there.MinPathSumOfTriangle;

import java.util.Arrays;
import java.util.List;

public class Solution {
    //空间复杂度O(n^2)
    public int minimumTotal_1(List<List<Integer>> triangle) {
        //获取三角形的行数
        int rows = triangle.size();
        //dp[i][j]表示以三角形第i行第j个数字结尾的路径的最小长度
        //0<=i<rows，0 <= j < triangle.get(i).size()
        int[][] dp = new int[rows][rows];//三角形的行数和最后一行的长度相等，故dp的行数列数相同
        //初始化dp
        for (int[] dpRow : dp) {
            Arrays.fill(dpRow, Integer.MAX_VALUE);
        }
        dp[0][0] = triangle.get(0).get(0);

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j <= i; j++) {
                int curPosVal = triangle.get(i).get(j);//第i行第j个位置的值
                if (j >= 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + curPosVal);
                }
                if (j < i) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + curPosVal);
                }
            }
        }
        int minPathSum = Integer.MAX_VALUE;
        for (int pathSum : dp[rows - 1]) {
            minPathSum = Math.min(minPathSum, pathSum);
        }
        return minPathSum;
    }

    //空间复杂度O(n)
    public int minimumTotal_2(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[] dp = new int[rows];
        dp[0] = triangle.get(0).get(0);

        int prev = 0, cur;
        for (int i = 1; i < rows; i++) {
            List<Integer> curRow = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                cur = dp[j];
                if (j == 0) {//最左边特殊处理
                    dp[j] = cur + curRow.get(j);
                } else if (j == i) {//最右边特殊处理
                    dp[j] = prev + curRow.get(j);
                } else {
                    dp[j] = Math.min(prev, cur) + curRow.get(j);
                }
                prev = cur;
            }
        }
        int minPathSum = Integer.MAX_VALUE;
        for (int pathSum : dp) {
            minPathSum = Math.min(minPathSum, pathSum);
        }
        return minPathSum;
    }
}
