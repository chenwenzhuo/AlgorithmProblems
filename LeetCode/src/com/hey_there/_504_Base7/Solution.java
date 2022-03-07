package com.hey_there._504_Base7;

public class Solution {
    public String convertToBase7(int num) {
        if (num == 0) return "0";
        int temp = Math.abs(num);
        StringBuilder base7 = new StringBuilder();
        while (temp > 0) {
            base7.append(temp % 7);
            temp /= 7;
        }
        if (num < 0) base7.append("-");
        return base7.reverse().toString();
    }
}
