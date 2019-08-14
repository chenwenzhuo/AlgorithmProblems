package com.hey_there;

public class Solution {
    public int divide(int dividend, int divisor) {
        int tag_PosOrNeg;//两数是否同号的标记，同号为1，异号为-1
        if (dividend > 0) {
            if (divisor > 0) {
                tag_PosOrNeg = 1;
            } else {
                tag_PosOrNeg = -1;
            }
        } else {
            if (divisor > 0) {
                tag_PosOrNeg = -1;
            } else {
                tag_PosOrNeg = 1;
            }
        }

        long result = 0;//要返回的结果(无符号）
        //将被除数与除数转换为 long 类型并取绝对值
        long dividendLong = Math.abs((long) dividend);
        long divisorLong = Math.abs((long) divisor);

        while (dividendLong >= divisorLong) {
            result++;
            dividendLong -= divisorLong;
        }

        //给结果加上符号
        result *= tag_PosOrNeg;
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int) result;
        }
    }
}
