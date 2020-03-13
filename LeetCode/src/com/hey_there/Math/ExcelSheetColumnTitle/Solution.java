package com.hey_there.Math.ExcelSheetColumnTitle;

public class Solution {
    public static String convertToTitle(int n) {
        StringBuilder titleBuilder = new StringBuilder();
        while (n > 0) {
            int remainder = n % 26;
            if (remainder == 0) {
                titleBuilder.append('Z');
                n = n / 26 - 1;
            } else {
                titleBuilder.append((char) ('A' + remainder - 1));
                n /= 26;
            }
        }
        return titleBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(52));
    }
}
