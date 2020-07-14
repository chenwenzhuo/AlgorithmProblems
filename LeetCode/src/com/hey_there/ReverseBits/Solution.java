package com.hey_there.ReverseBits;

public class Solution {
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int tailDigit = n % 2;
            res = res * 2 + tailDigit;
            n >>= 1;//无符号右移
        }
        return res;
    }
}
