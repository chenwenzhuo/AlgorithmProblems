package com.hey_there.PermutationSequence;

public class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder allDigits = new StringBuilder("123456789");
        StringBuilder result = new StringBuilder();

        int base = 0;
        while (result.length() < n) {
            int fac = factorial(n - 1 - result.length());

            for (int i = 1; i <= n - result.length(); i++) {
                if (i * fac + base >= k) {
                    base += (i - 1) * fac;

                    result.append(allDigits.charAt(i - 1));
                    allDigits.delete(i - 1, i);
                    break;
                }
            }
        }

        return result.toString();
    }

    //计算n的阶乘(n>=0)
    private int factorial(int n) {
        if (n == 0) {
            return 1;
        }

        if (n <= 2) {
            return n;
        }

        //递归计算阶乘
        //return n * factorial(n - 1);

        //循环计算阶乘
        int fac = 1;
        for (int i = 1; i <= n; i++) {
            fac *= i;
        }
        return fac;
    }
}
