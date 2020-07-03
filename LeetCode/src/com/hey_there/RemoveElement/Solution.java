package com.hey_there.RemoveElement;

public class Solution {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int actualIndex = 0, searchIndex;
        for (searchIndex = 0; searchIndex < len; searchIndex++) {
            if (nums[searchIndex] == val) {
                continue;
            }
            nums[actualIndex] = nums[searchIndex];
            actualIndex++;
        }
        return actualIndex;
    }
}
