package com.hey_there.Math.ExcelSheetColumnNumber;

public class Solution {
    public int titleToNumber(String s) {
        int len = s.length();
        int number = 0;
        for (int i = len - 1; i >= 0; i--) {
            int digit = s.charAt(i) - 'A' + 1;
            number += digit * Math.pow(26, len - 1 - i);
        }
        return number;
    }
}
