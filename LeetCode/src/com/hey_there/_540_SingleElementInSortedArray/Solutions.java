package com.hey_there._540_SingleElementInSortedArray;

public class Solutions {
    public int singleNonDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i += 2) {
            if (i == nums.length - 1) break;
            if (nums[i] != nums[i + 1]) return nums[i];
        }
        return nums[nums.length - 1];
    }

    public int singleNonDuplicate_binarySearch(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (mid % 2 == 0) {
                if (nums[mid] == nums[mid + 1]) left = mid + 1;
                else right = mid;
            } else {
                if (nums[mid] == nums[mid - 1]) left = mid + 1;
                else right = mid;
            }
        }
        return nums[left];
    }
}
