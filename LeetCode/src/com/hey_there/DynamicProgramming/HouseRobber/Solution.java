package com.hey_there.DynamicProgramming.HouseRobber;

public class Solution {
    public int rob(int[] nums) {
        int lenNums = nums.length;
        //特殊值手动处理
        if (lenNums == 0) {
            return 0;
        } else if (lenNums == 1) {
            return nums[0];
        } else if (lenNums == 2) {
            return Math.max(nums[0], nums[1]);
        }
        //dp[i]表示在nums[0]到num[i]的房子中能得到的最高金额
        int[] dp = new int[lenNums];
        //对前三个值手动初始化
        dp[0] = nums[0];
        dp[1] = nums[1];
        dp[2] = Math.max(nums[0] + nums[2], nums[1]);
        //计算后续值
        for (int i = 3; i < lenNums; i++) {
            //只需要考虑前面三个值
            dp[i] = Math.max(Math.max(dp[i - 1], dp[i - 2] + nums[i]),
                    dp[i - 3] + nums[i]);
        }
        return dp[lenNums - 1];
    }

    public static void main(String[] args) {
        //int[] nums = {1, 2, 3, 1};
        //int[] nums = {2, 7, 9, 3, 1};
        int[] nums = {2, 1, 1, 2};
        Solution solution = new Solution();
        int ans = solution.rob(nums);
        System.out.println(ans);
    }
}
