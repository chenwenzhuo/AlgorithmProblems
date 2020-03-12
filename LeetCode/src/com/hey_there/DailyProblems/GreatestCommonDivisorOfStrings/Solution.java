package com.hey_there.DailyProblems.GreatestCommonDivisorOfStrings;

public class Solution {
    public String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();//两个字符串的长度

        //str1+str2与str2+str1相同时有解
        String stitchedString1 = str1 + str2;
        String stitchedString2 = str2 + str1;
        if (!stitchedString1.equals(stitchedString2)) {
            return "";//若两拼接字符串不相同，则无解
        }

        //取两长度的较小者
        int shorter = Math.min(len1, len2);
        //求长度的最大公约数
        int gcd;
        while (shorter > 0) {
            if (len1 % shorter == 0 && len2 % shorter == 0) {
                break;
            }
            shorter--;
        }
        return str1.substring(0, shorter);
    }
}
