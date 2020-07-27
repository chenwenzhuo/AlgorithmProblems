package com.hey_there.InterviewProblem_21_AdjustOddAndEvenNumbers;

public class Solution {
    public int[] exchange(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (true) {
            //从左向右，找到下一个偶数的位置
            while (low < nums.length && nums[low] % 2 == 1) {
                low++;
            }
            //从右向左，找到下一个奇数的位置
            while (high >= 0 && nums[high] % 2 == 0) {
                high--;
            }
            if (low < high) {
                swap(nums, low, high);
            } else {
                break;
            }
        }
        return nums;
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }
}
