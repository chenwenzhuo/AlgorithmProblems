package com.hey_there._790_DominoAndTrominoTiling;

public class Solution {
    public int numTilings(int n) {
        //当铺完前i-1列时，第i列可能有四种状态：
        //1、第i列为空；2、第i列上方第正方形被覆盖；
        //3、第i列下方第正方形被覆盖；4、第i列被铺满
        //dp[n][3]即为所有答案
        //base case：dp[0][0]==dp[0][1]==dp[0][2]==0，dp[0][3]=1
        int[][] dp = new int[n + 1][4];
        dp[0][3] = 1;
        int MOD = 1000000007;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][3];
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
            dp[i][3] = (((dp[i - 1][0] + dp[i - 1][1]) % MOD + dp[i - 1][2]) % MOD + dp[i - 1][3]) % MOD;
        }
        return dp[n][3];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.numTilings(3);
        System.out.println(res);
    }
}
