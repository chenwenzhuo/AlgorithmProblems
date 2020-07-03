package com.hey_there.RemoveDuplicatesFromSortedArray;

public class Solution {
    public int removeDuplicates(int[] nums) {
        int len = nums.length;//数组长度
        int actualIndex = 1, duplicateIndex;//用两个下标表示数组中元素

        for (duplicateIndex = 1; duplicateIndex < len; duplicateIndex++) {
            if (nums[duplicateIndex] == nums[duplicateIndex - 1]) {
                continue;
            }
            nums[actualIndex] = nums[duplicateIndex];
            actualIndex++;
        }
        return actualIndex;
    }
}
