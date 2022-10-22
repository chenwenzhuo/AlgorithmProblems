package com.hey_there._712_MinimumASCIIDeleteSumForTwoStrings;

public class Solution {
    //为最长公共子序列问题的遍体，与第583题不同的地方在于求删除部分的长度，改为求删除部分的ASCII码之和
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        //base case：其中一个字符串为空时，另一个字符串需要全部删除
        for (int i = 1; i <= n; i++)
            dp[0][i] = dp[0][i - 1] + s2.charAt(i - 1);
        for (int i = 1; i <= m; i++)
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        //dp解法
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1),
                        dp[i][j - 1] + s2.charAt(j - 1));
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "sea";
        String s2 = "eat";
        Solution solution = new Solution();
        int res = solution.minimumDeleteSum(s1, s2);
        System.out.println(res);
    }
}
