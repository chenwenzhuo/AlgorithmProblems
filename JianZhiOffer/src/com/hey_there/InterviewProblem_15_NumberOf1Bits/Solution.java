package com.hey_there.InterviewProblem_15_NumberOf1Bits;

public class Solution {
    //循环固定执行32次
    public int hammingWeight_1(int n) {
        int mask = 1, count = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) == 1) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }

    //n的二进制表示中，有几个1，循环执行几次
    public int hammingWeight_2(int n) {
        int count = 0;
        while (n != 0) {
            count++;
            n &= (n - 1);
        }
        return count;
    }
}
