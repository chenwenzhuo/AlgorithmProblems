package com.hey_there.DistinctSubsequences;

import java.util.Arrays;

public class Solution {
    public int numDistinct(String s, String t) {
        int len_s = s.length(), len_t = t.length();

        //dp[i][j]表示t的前i个字符子串在s的前j个字符子串中的出现次数
        int[][] dp = new int[len_t + 1][len_s + 1];
        //初始化base case
        //t的前0个字符在s的任意长度子串中均出现一次
        Arrays.fill(dp[0], 1);
        //t的前1...len_t个字符在s的前0个字符中均不出现
        for (int i = 1; i <= len_t; i++) {
            dp[i][0] = 0;
        }

        /*
        * rabbbit  rabbit*/
        char[] ch_s = s.toCharArray();
        char[] ch_t = t.toCharArray();
        //根据状态转移公式计算dp数组其余值
        //由于当j<i时dp[i][j]一定为0，故j的起始值可设为i
        for (int i = 1; i <= len_t; i++) {
            for (int j = i; j <= len_s; j++) {
                if (ch_t[i - 1] == ch_s[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[len_t][len_s];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDistinct("rabbbit", "rabbit"));
    }
}
