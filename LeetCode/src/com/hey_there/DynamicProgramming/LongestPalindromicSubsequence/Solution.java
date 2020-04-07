package com.hey_there.DynamicProgramming.LongestPalindromicSubsequence;

public class Solution {
    public int longestPalindromeSubseq(String s) {
        int lenS = s.length();
        char[] chS = s.toCharArray();
        //dp[i][j]表示s中从下标i到下标j的子串中的最长回文子序列长度
        int[][] dp = new int[lenS][lenS];
        //初始化基础情况，即所有单个字符都是长度为 1 的回文序列
        for (int i = 0; i < lenS; i++) {
            dp[i][i] = 1;
        }
        //计算其他dp[i][j]
        //倒着遍历dp数组
        for (int i = lenS - 2; i >= 0; i--) {
            for (int j = i + 1; j <= lenS - 1; j++) {
                if (chS[i] == chS[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][lenS - 1];
    }
}
