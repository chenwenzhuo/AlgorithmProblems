package com.hey_there.OnesAndZeroes;

import java.util.HashMap;

/* 0-1背包问题，其中m，n应视为背包容量，
 * 每选择装入一个字符串，都会在两个背包中占用容量*/
public class Solution {
    public int findMaxForm_1(String[] strs, int m, int n) {
        int numStrs = strs.length;
        //首先统计各个字符串中'0'的数量和'1'的数量
        HashMap<String, Integer> zeroesInStr = new HashMap<>();
        HashMap<String, Integer> onesInStr = new HashMap<>();
        for (String s : strs) {
            int countZeroes = 0, countOnes = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    countZeroes++;
                } else {
                    countOnes++;
                }
            }
            zeroesInStr.put(s, countZeroes);
            onesInStr.put(s, countOnes);
        }
        /* dp[i][j][k]表示在前i个字符串中做选择，
         * 0背包容量为j，1背包容量为k时，能装下的最大字符串数量*/
        int[][][] dp = new int[numStrs + 1][m + 1][n + 1];
        for (int i = 1; i <= numStrs; i++) {
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    int zeroesInCurStr = zeroesInStr.get(strs[i - 1]);
                    int onesInCurStr = onesInStr.get(strs[i - 1]);
                    if (j < zeroesInCurStr ||
                            k < onesInCurStr) {
                        //当背包容量不足以装下当前strs[i]时，无法装入当前strs[i]，故继承前一层的结果
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k],
                                dp[i - 1][j - zeroesInCurStr][k - onesInCurStr] + 1);
                    }
                }
            }
        }
        return dp[numStrs][m][n];
    }

    public int findMaxForm_2(String[] strs, int m, int n) {
        int numStrs = strs.length;
        //首先统计各个字符串中'0'的数量和'1'的数量
        //使用二维数组代替前一方法中的HashMap，提高效率
        int[][] zeroes_ones = new int[numStrs][2];//第一列表示字符串中0的数量，第二列表示1的数量
        for (int i = 0; i < numStrs; i++) {
            int len_str = strs[i].length();
            for (int j = 0; j < len_str; j++) {
                if (strs[i].charAt(j) == '0') {
                    zeroes_ones[i][0]++;
                } else {
                    zeroes_ones[i][1]++;
                }
            }
        }
        /* 前一方法中，dp[i][j][k]的值仅与前一层（i-1）有关
         * 故可用一个二维的数组代替三维数组*/
        int[][] dp = new int[m + 1][n + 1];
        for (int idx = 1; idx <= numStrs; idx++) {
            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    if (i >= zeroes_ones[idx - 1][0] && j >= zeroes_ones[idx - 1][1]) {
                        dp[i][j] = Math.max(dp[i][j],
                                dp[i - zeroes_ones[idx - 1][0]][j - zeroes_ones[idx - 1][1]] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }
}
