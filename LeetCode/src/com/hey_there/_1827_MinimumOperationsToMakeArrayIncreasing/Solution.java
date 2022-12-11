package com.hey_there._1827_MinimumOperationsToMakeArrayIncreasing;

public class Solution {
    public int minOperations(int[] nums) {
        if (nums.length == 1)
            return 0;//数组长度为1，一定严格单增
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                continue;//当前元素比前一个元素大，无需处理
            //当前元素小于等于前一个元素，至少需要增加当前元素，直到比前一个大1
            res += (nums[i - 1] - nums[i] + 1);
            nums[i] = nums[i - 1] + 1;
        }
        return res;
    }
}
