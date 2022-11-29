package com.hey_there._1758_MinimumChangesToMakeAlternatingBinaryString;

public class Solution {
    public int minOperations(String s) {
        int length = s.length();
        char[] chS = s.toCharArray();
        // 统计修改次数，
        // cnt[0]表示将s改为以字符0开头的交替字符串所需的修改次数
        // cnt[1]表示将s改为以字符1开头的交替字符串所需的修改次数
        int[] cnt = new int[2];
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {//当前下标i为偶数
                if (chS[i] == '1') cnt[0]++;
                else cnt[1]++;
            } else {//当前下标i为奇数
                if (chS[i] == '0') cnt[0]++;
                else cnt[1]++;
            }
        }
        return Math.min(cnt[0], cnt[1]);
    }
}
