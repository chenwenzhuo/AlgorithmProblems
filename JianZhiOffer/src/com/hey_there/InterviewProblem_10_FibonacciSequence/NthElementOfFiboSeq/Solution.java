package com.hey_there.InterviewProblem_10_FibonacciSequence.NthElementOfFiboSeq;

public class Solution {
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int prev_1 = 0, prev_2 = 1;
        int fibNum, idx = 1;
        do {
            fibNum = (prev_1 + prev_2) % 1000000007;
            prev_1 = prev_2;
            prev_2 = fibNum;
            idx++;
        } while (idx < n);
        return fibNum;
    }
}
