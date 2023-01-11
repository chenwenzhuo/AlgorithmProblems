package com.hey_there._2283_CheckIfNumberHasEqualDigitCountAndDigitValue;

public class Solution {
    public boolean digitCount(String num) {
        int length = num.length();
        int[] digits = new int[length];
        int[] count = new int[10];
        for (int i = 0; i < length; i++) {
            int n = num.charAt(i) - '0';
            digits[i] = n;
            count[n]++;
        }
        for (int i = 0; i < length; i++) {
            if (digits[i] == count[i])
                continue;
            return false;
        }
        return true;
    }
}
