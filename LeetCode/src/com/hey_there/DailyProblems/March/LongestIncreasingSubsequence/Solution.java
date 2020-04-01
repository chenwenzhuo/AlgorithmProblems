package com.hey_there.DailyProblems.March.LongestIncreasingSubsequence;

import java.util.Arrays;

public class Solution {
    public static int lengthOfLIS_dp_my(int[] nums) {
        if (nums.length == 0) {
            return 0;//数组长度为0，直接返回0
        }
        //dp[i]表示以nums[i]结尾的最长上升子序列的长度
        int[] dp = new int[nums.length];
        //任意nums[i]都可单独形成一个长为1的子序列，故dp初始化为全1
        Arrays.fill(dp, 1);

        int longest = 1;//表示最长递增子序列的长度
        for (int i = 1; i < nums.length; i++) {
            //对每个nums[i]都向前遍历一遍，判断nums[i]能否接在某个递增子序列后方
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            //检查是否有更长的递增子序列被找到
            if (dp[i] > longest) {
                longest = dp[i];
            }
        }
        return longest;
    }

    public static int lengthOfLIS_binarySearch(int[] nums) {
        int lenNums = nums.length;
        int[] top = new int[lenNums];
        int piles = 0;
        for (int num : nums) {
            //搜索左边界的二分查找算法
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] >= num) {
                    right = mid;
                } else if (top[mid] < num) {
                    left = mid + 1;
                }
            }
            // 没找到合适的牌堆，新建一堆
            if (left == piles) {
                piles++;
            }
            // 把这张牌放到牌堆顶
            top[left] = num;
        }
        return piles;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 4};
        System.out.println(lengthOfLIS_dp_my(nums));
    }
}
