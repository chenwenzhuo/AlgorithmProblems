package com.hey_there.DailyProblems.June.BestSightseeingPair;

public class Solution {
    public int maxScoreSightseeingPair(int[] A) {
        int maxScore = 0;
        int Ai_i = A[0];
        for (int idx = 1; idx < A.length; idx++) {
            maxScore = Math.max(maxScore, Ai_i + A[idx] - idx);
            Ai_i = Math.max(Ai_i, A[idx] + idx);
        }
        return maxScore;
    }
}
