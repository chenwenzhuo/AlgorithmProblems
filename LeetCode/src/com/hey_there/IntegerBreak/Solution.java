package com.hey_there.IntegerBreak;

public class Solution {
    public int integerBreak_dp(int n) {
        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        //dp[i]表示从i能获得的最大乘积
        int[] dp = new int[n + 1];
        dp[2] = 1;
        dp[3] = 2;

        for (int i = 4; i <= n; i++) {
            for (int j = 2; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]));
            }
        }
        return dp[n];
    }

    public int integerBreak(int n) {
        if (n <= 3) {
            return n-1;
        }
        int quotient = n / 3;//商
        int remainder = n % 3;//余数
        int maxProduct;//乘积
        if (remainder == 2) {
            maxProduct = (int) (Math.pow(3, quotient) * 2);
        } else if (remainder == 1) {
            maxProduct = (int) (Math.pow(3, quotient - 1) * 4);
        } else {
            maxProduct = (int) Math.pow(3, quotient);
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.integerBreak_dp(10);
        System.out.println(ans);
    }
}
