package com.hey_there.InterviewProblem_67_StringToInteger;

public class Solution {
    public int strToInt(String str) {
        if (str.equals("")) return 0;//空字符串，返回0

        //将字符串转为字符数组，方便使用
        char[] chs = str.toCharArray();
        //寻找第一个非空格字符
        int start = 0;
        while (start < chs.length && chs[start] == ' ') start++;
        if (start >= chs.length) return 0;//字符串仅包含空格，返回0

        int num = 0;
        if (chs[start] == '+') {
            num = processNum(chs, start + 1, true);
        } else if ('0' <= chs[start] && chs[start] <= '9') {
            num = processNum(chs, start, true);
        } else if (chs[start] == '-') {
            num = processNum(chs, start + 1, false);
        }
        return num;
    }

    private int processNum(char[] chs, int start, boolean isPositive) {
        long num = 0;
        while (start < chs.length &&
                '0' <= chs[start] && chs[start] <= '9') {
            if (isPositive) num = num * 10 + (chs[start] - '0');//正数用加
            else num = num * 10 - (chs[start] - '0');//负数用减
            //检查数字是否超过int值的范围
            if (num >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
            else if (num <= Integer.MIN_VALUE) return Integer.MIN_VALUE;

            start++;
        }
        return (int) num;
    }
}
