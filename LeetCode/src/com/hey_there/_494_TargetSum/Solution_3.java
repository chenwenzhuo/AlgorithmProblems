package com.hey_there._494_TargetSum;

public class Solution_3 {
    //动态规划解法，转化为子集背包问题
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        //以下两种情况一定无解
        if (sum < Math.abs(target) || (sum + target) % 2 == 1)
            return 0;
        return subsets(nums, (sum + target) / 2);
    }

    private int subsets(int[] nums, int sum) {
        //dp[i][j]表示，在前i个数中选择若干个，相加等于sum的方式数
        //base case：dp[0][...]为0，没有选择，无法凑成任何数；但dp[0][0]为1，凑成0可以有1种方式
        int[][] dp = new int[nums.length + 1][sum + 1];
        dp[0][0] = 1;
        //由于sum可能为0，故内层循环的j需要从0开始
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j < nums[i - 1])
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[nums.length][sum];
    }
}
