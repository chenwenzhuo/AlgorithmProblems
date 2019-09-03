package com.hey_there.Fibonacci_DynamicProgramming;

public class Main {

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
