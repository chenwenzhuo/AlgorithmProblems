package com.hey_there.InterviewProblem_57.Problem_1_FindTwoNumbersWithSpecifiedSum;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) left++;
            else if (sum > target) right--;
            else break;
        }
        if (left >= right) return null;
        return new int[]{nums[left], nums[right]};
    }
}
