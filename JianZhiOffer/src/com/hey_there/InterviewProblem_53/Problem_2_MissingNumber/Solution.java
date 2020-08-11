package com.hey_there.InterviewProblem_53.Problem_2_MissingNumber;

public class Solution {
    public int missingNumber(int[] nums) {
        int length = nums.length;
        //检查缺少的是不是最小或最大的那个数字
        if (nums[0] == 1) return 0;
        if (nums[length - 1] == length - 1) return length;

        int left = 0, right = length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == mid) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
