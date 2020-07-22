package com.hey_there.InterviewProblem_14_CutRope;

public class Solution {
    //基于数学推导，可直接得出答案，无需使用dp
    public int cuttingRope_1(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int quotient = n / 3;
        int remainder = n % 3;
        if (remainder == 0) {
            return (int) Math.pow(3, quotient);
        } else if (remainder == 1) {
            return ((int) Math.pow(3, quotient - 1)) * 4;
        }
        return ((int) Math.pow(3, quotient)) * 2;
    }

    /* 相比于cuttingRope_1，cuttingRope_2中可能出现数值溢出的情况，
     * 故需要对大数取余，模上1000000007*/
    public int cuttingRope_2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int mod = 1000000007;
        int quotient = n / 3;
        int remainder = n % 3;
        //要计算Math.pow(3, quotient - 1)对mod取余后的值
        long rem = 1, x = 3;
        for (int exp = quotient - 1; exp > 0; exp /= 2) {
            if (exp % 2 == 1)
                rem = (rem * x) % mod;
            x = (x * x) % mod;
        }

        if (remainder == 0) {
            return (int) (rem * 3 % mod);
        }
        if (remainder == 1) {
            return (int) (rem * 4 % mod);
        }
        return (int) (rem * 6 % mod);
    }
}
