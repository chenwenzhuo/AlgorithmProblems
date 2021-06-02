package com.hey_there.ContinuousSubarraySum;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) {
            return false;
        }
        if (k == 0) {
            //k为0时，需要数组中有两个连续的0，才返回true
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == 0 && nums[i - 1] == 0) {
                    return true;
                }
            }
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            sum %= k;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) {
                    return true;
                }
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }

    //动态规划，复杂度O(n^2)，超时
    public boolean checkSubarraySum_dp(int[] nums, int k) {
        int lenNums = nums.length;//nums数组的长度
        if (lenNums < 2) return false;//数组长度不足2，直接返回false
        if (k == 0) {
            //k为0时，需要数组中有两个连续的0，才返回true
            for (int i = 1; i < lenNums; i++) {
                if (nums[i] == 0 && nums[i - 1] == 0) return true;
            }
            return false;
        }

        //k不为0时，动态规划求解
        int[] dp = new int[lenNums];//dp数组用来记录子数组的和
        //计算dp数组中其他元素的值
        for (int i = 0; i < lenNums; i++) {
            dp[i] = nums[i];//长度为1的子数组的和
            //计算从下标i开始的更长的子数组和
            for (int j = i + 1; j < lenNums; j++) {
                dp[j] = dp[j - 1] + nums[j];
                if (dp[j] == 0 || dp[j] % k == 0) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0};//k=2
        Solution solution = new Solution();
        boolean ans = solution.checkSubarraySum(nums, 2);
        System.out.println(ans);
    }
}
