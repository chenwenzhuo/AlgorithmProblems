package com.hey_there.SearchInsertPosition;

public class Solution_linear {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        for (int index = 0; index < len; index++) {
            if (nums[index] >= target) {
                return index;
            }
        }
        return len;
    }
}
