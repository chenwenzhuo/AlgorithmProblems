package com.hey_there.FindFirstAndLastPositionOfElementInSortedArray;

public class Solution_my {
    private int[] nums;
    private int target;

    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;//数组长度
        //处理特殊值
        if (len == 0) {
            return new int[]{-1, -1};
        }
        if (len == 1) {
            if (nums[0] == target) {
                return new int[]{0, 0};
            } else {
                return new int[]{-1, -1};
            }
        }

        this.nums = nums;
        this.target = target;
        int index = binarySearch();

        if (index == -1) {//index为-1表示数组中不存在target
            return new int[]{-1, -1};
        }

        int start = index, end = index;
        while (start >= 0 && nums[start] == target) {
            start--;
        }
        while (end <= len - 1 && nums[end] == target) {
            end++;
        }
        return new int[]{start + 1, end - 1};
    }

    //找到target在数组中的一个位置，若不存在则返回-1
    public int binarySearch() {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
