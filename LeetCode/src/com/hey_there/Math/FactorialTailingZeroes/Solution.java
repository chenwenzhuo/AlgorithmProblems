package com.hey_there.Math.FactorialTailingZeroes;

public class Solution {
    public int trailingZeroes(int n) {
        int zeroes = 0;
        while (n > 0) {
            zeroes += n / 5;
            n /= 5;
        }
        return zeroes;
    }
}
