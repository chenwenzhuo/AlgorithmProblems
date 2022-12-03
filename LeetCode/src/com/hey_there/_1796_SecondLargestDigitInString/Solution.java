package com.hey_there._1796_SecondLargestDigitInString;

public class Solution {
    public int secondHighest(String s) {
        int first = -1, second = -1;//第一大、第二大的数字
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (!Character.isDigit(ch))
                continue;
            int numCh = ch - '0';//将字符转为数字
            if (first == -1) {//first为-1，找到第一个数字
                first = numCh;
            } else {//对于后续的数字
                if (numCh > first) {
                    second = first;
                    first = numCh;
                } else if (second < numCh && numCh < first) {
                    second = numCh;
                }
            }
        }
        return second;
    }
}
