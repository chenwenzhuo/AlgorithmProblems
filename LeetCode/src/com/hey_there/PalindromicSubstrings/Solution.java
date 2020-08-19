package com.hey_there.PalindromicSubstrings;

public class Solution {
    public int countSubstrings(String s) {
        int length = s.length();
        char[] chs = s.toCharArray();

        //dp[i][j]表示[i,j]闭区间内的子串是否是回文串
        boolean[][] dp = new boolean[length][length];
        //初始化base case，所有长度为1的子串都是回文串
        for (int i = 0; i < length; i++) dp[i][i] = true;
        int counter = length;//回文子串计数器
        //从后往前遍历并计算dp数组
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = chs[i] == chs[j];//检查i位置和j位置的字符是否相等
                //对于长度超过2的子串，还需要检查去掉i位置和j位置的字符后的子串是否回文
                if (j - i > 1) dp[i][j] = dp[i][j] && dp[i + 1][j - 1];
                if (dp[i][j]) counter++;
            }
        }
        return counter;
    }
}
