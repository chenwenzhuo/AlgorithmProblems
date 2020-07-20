package com.hey_there.InterviewProblem_10_FibonacciSequence.FrogJumpsSteps;

public class Solution {
    public int numWays(int n) {
        if (n == 0) {
            return 1;
        } else if (n <= 2) {
            return n;
        }
        int twoStepPrev = 1;//跳到前两级台阶的方式数
        int oneStepPrev = 2;//跳到前一级台阶的方式数
        int toCurStep;//到当前台阶的方式数
        int stepCount = 2;//记录当前台阶是第几级
        do {
            toCurStep = (twoStepPrev + oneStepPrev) % 1000000007;
            twoStepPrev = oneStepPrev;
            oneStepPrev = toCurStep;
            stepCount++;
        } while (stepCount < n);
        return toCurStep;
    }
}
