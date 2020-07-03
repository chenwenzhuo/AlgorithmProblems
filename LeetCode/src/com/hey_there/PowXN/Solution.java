package com.hey_there.PowXN;

public class Solution {
    //自顶向下递归
    public double myPow_recursive(double x, int n) {
        if (n >= 0) {
            return powHelper(x, n);
        } else {
            return 1 / powHelper(x, n * (-1));
        }
    }

    private double powHelper(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return x;
        }
        double half = powHelper(x, n / 2);
        return (n % 2 == 0) ? (half * half) : (half * half * x);
    }

    //对n进行二进制拆分，迭代
    public double myPow(double x, int n) {
        //对指数n取绝对值
        //先强转为long，避免在n==Integer.MIN_VALUE时取绝对值产生溢出
        long abs_n = Math.abs((long)n);
        double powResult = 1;
        double tempPow = x;
        while (abs_n > 0) {
            if (abs_n % 2 == 1) {
                powResult *= tempPow;
            }
            tempPow = tempPow * tempPow;
            abs_n /= 2;
        }
        return n >= 0 ? powResult : 1 / powResult;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        double ans = solution.myPow(3, -5);
        System.out.println(ans);
    }
}
