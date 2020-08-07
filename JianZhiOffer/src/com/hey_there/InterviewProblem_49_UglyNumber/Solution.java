package com.hey_there.InterviewProblem_49_UglyNumber;

public class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;
        for (int i = 1; i < n; i++) {
            int dpaMult2 = dp[a] * 2;
            int dpbMult3 = dp[b] * 3;
            int dpcMult5 = dp[c] * 5;
            dp[i] = Math.min(dpaMult2, Math.min(dpbMult3, dpcMult5));
            if (dp[i] == dpaMult2) a++;
            if (dp[i] == dpbMult3) b++;
            if (dp[i] == dpcMult5) c++;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int nth = solution.nthUglyNumber(10);
        System.out.println(nth);
    }
}
