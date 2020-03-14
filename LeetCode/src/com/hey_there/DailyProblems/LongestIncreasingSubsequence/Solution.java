package com.hey_there.DailyProblems.LongestIncreasingSubsequence;

public class Solution {
    public static int lengthOfLIS_dp_my(int[] nums) {
        if (nums.length == 0) {
            return 0;//数组长度为0，直接返回0
        }
        //dp[i]表示以nums[i]结尾的最长上升子序列的长度
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int longest = 1;
        for (int i = 1; i < nums.length; i++) {
            //对每个nums[i]都向前遍历一遍，判断nums[i]能否接在某个递增子序列后方
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            //遍历完成后若dp[i]仍为0，表示nums[i]不能接在某个递增子序列后方
            if (dp[i] == 0) {
                dp[i] = 1;//nums[i]单独形成一个子序列，dp[i]为1
            }
            //检查是否有更长的递增子序列被找到
            if (dp[i] > longest) {
                longest = dp[i];
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 4};
        System.out.println(lengthOfLIS_dp_my(nums));
    }
}
