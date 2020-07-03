package com.hey_there.HouseRobber_2;

public class Solution {
    public int rob(int[] nums) {
        int lenNums = nums.length;
        if (lenNums == 0) {
            return 0;
        } else if (lenNums == 1) {
            return nums[0];
        }
        //避免同时盗取第一家和最后一家，将两家去掉后分别计算，取其较大者
        //去掉第一个的最高金额和去掉最后一个的最高金额
        int highest_excludeFirst = rob(nums, 1, lenNums - 1);
        int highest_excludeLast = rob(nums, 0, lenNums - 2);
        return Math.max(highest_excludeFirst, highest_excludeLast);
    }

    private int rob(int[] nums, int rangeLeft, int rangeRight) {
        int numHouses = rangeRight - rangeLeft + 1;
        if (numHouses == 1) {
            return nums[rangeLeft];
        } else if (numHouses == 2) {
            return Math.max(nums[rangeLeft], nums[rangeRight]);
        }

        int[] dp = new int[numHouses];
        dp[0] = nums[rangeLeft];
        dp[1] = nums[rangeLeft + 1];
        dp[2] = Math.max(nums[rangeLeft] + nums[rangeLeft + 2], nums[rangeLeft + 1]);

        for (int i = 3; i < numHouses; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2], dp[i - 3]) + nums[i + rangeLeft]);
        }
        return dp[numHouses - 1];
    }
}
