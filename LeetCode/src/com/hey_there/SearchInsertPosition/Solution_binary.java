package com.hey_there.SearchInsertPosition;

public class Solution_binary {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;

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
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7};
        Solution_binary solution_binary = new Solution_binary();
        System.out.println(solution_binary.searchInsert(nums, 2));
    }
}
