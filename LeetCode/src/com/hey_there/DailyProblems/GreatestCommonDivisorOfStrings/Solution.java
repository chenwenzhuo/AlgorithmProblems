package com.hey_there.DailyProblems.GreatestCommonDivisorOfStrings;

public class Solution {
    public static String gcdOfStrings(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length();//两个字符串的长度

        //str1+str2与str2+str1相同时有解
        String stitchedString1 = str1 + str2;
        String stitchedString2 = str2 + str1;
        if (!stitchedString1.equals(stitchedString2)) {
            return "";//若两拼接字符串不相同，则无解
        }

        //求两长度的最大公约数
        int bigger = Math.max(len1, len2);
        int smaller = Math.min(len1, len2);
        int gcd;
        //辗转相除法
        while (true) {
            int remainder = bigger % smaller;
            if (remainder == 0) {
                gcd = smaller;
                break;
            }
            bigger = smaller;
            smaller = remainder;
        }
        return str1.substring(0, gcd);
    }

    public static void main(String[] args) {
        String str1 = "ABCABC";
        String str2 = "ABC";
        System.out.println(gcdOfStrings(str1, str2));
    }
}
