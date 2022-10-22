package com.hey_there._583_DeleteOperationForTwoStrings;

public class Solution {
    // 删除字符得到的结果即为最长公共子序列
    // 计算最长公共子序列长度，与原字符串的长度之差，即为最小步数
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        //dp[i][j]表示 s1[0..i-1]和s2[0...j-1] 的最长公共子序列长度
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return word1.length() + word2.length() - 2 * dp[m][n];
    }
}
