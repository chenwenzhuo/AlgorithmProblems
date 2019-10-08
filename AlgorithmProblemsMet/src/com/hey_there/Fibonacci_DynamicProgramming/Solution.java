package com.hey_there.Fibonacci_DynamicProgramming;

public class Solution {
    public int dynamicFibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }

        int fibonacci_current = 0;//此次循环要计算的fibonacci数
        int fibonacci_pre_pre = 1;//前两个fibonacci数
        int fibonacci_pre = 1;//前一个fibonacci数
        for (int i = 3; i <= n; i++) {
            fibonacci_current = fibonacci_pre_pre + fibonacci_pre;//当前值等于前两个值之和

            //更新前两个fibonacci数的值
            fibonacci_pre_pre = fibonacci_pre;
            fibonacci_pre = fibonacci_current;
        }

        return fibonacci_current;
    }

    public int recursiveFibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        for (int i = 1; i <= 30; i++) {
            System.out.print(solution.recursiveFibonacci(i) + "   ");
        }
        System.out.println();

        for (int i = 1; i <= 30; i++) {
            System.out.print(solution.dynamicFibonacci(i) + "   ");
        }
        System.out.println();
    }
}
