package com.hey_there.DailyProblems.April.WaysToChangeCoin;

public class Solution {
    public int waysToChange(int n) {
        int mod = 1000000007;
        int[] coins = {1, 5, 10, 25};
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 0; i < 4; i++) {
            for (int j = coins[i]; j <= n; j++) {
                dp[j] = (dp[j] + dp[j - coins[i]]) % mod;
            }
        }
        return dp[n];
    }
}
