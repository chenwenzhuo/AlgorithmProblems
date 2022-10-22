package com.hey_there._1143_LongestCommonSubsequence;

import java.util.Arrays;

public class Solution {
    private int[][] memo;

    public int longestCommonSubsequence_1(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        memo = new int[len1][len2];//创建备忘录
        for (int[] row : memo)//初始化为-1，表示未计算
            Arrays.fill(row, -1);
        return calcLCS(text1, 0, text2, 0);
    }

    //定义：计算 s1[i..] 和 s2[j..] 的最长公共子序列长度
    //base case：i == len(s1)或j == len(s2)时，s1[i..]和s2[j..]为空串，返回0
    private int calcLCS(String s1, int idx1, String s2, int idx2) {
        if (idx1 == s1.length() || idx2 == s2.length())
            return 0;
        //若memo[idx1][idx2]已计算过，则直接返回
        if (memo[idx1][idx2] != -1)
            return memo[idx1][idx2];

        //memo[idx1][idx2]未计算，比较两字符串中当前位置的字符
        if (s1.charAt(idx1) == s2.charAt(idx2))//相等时，将两下标同时后移
            memo[idx1][idx2] = 1 + calcLCS(s1, idx1 + 1, s2, idx2 + 1);
        else//不等时，后移其中一个，取结果较大者
            memo[idx1][idx2] = Math.max(
                    calcLCS(s1, idx1, s2, idx2 + 1),
                    calcLCS(s1, idx1 + 1, s2, idx2));

        return memo[idx1][idx2];
    }

    public int longestCommonSubsequence_2(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        // 定义：s1[0..i-1] 和 s2[0..j-1] 的 lcs 长度为 dp[i][j]
        // 目标：s1[0..m-1] 和 s2[0..n-1] 的 lcs 长度，即 dp[m][n]
        // base case: dp[0][..] = dp[..][0] = 0
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                //比较当前位置的两字符
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    //相等时，两字符均在LCS中
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    continue;
                }
                //不等时，二者至少一个不在LCS中
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }
}
