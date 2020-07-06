package com.hey_there.FindMinimumInRotatedSortedArray;

public class Solution {
    public int findMin(int[] nums) {
        int len_nums = nums.length;
        if (len_nums == 1) {
            return nums[0];
        }
        int left = 0, right = len_nums - 1;
        int min = Integer.MIN_VALUE;
        //nums[left] < nums[right]说明数组未被旋转
        if (nums[left] < nums[right]) {
            return nums[left];
        }
        //在经过旋转的数组中二分搜索
        while (left <= right) {
            int mid = (left + right) / 2;
            if ((mid > 0 && nums[mid] < nums[mid - 1]) || left == right) {
                min = nums[mid];
                break;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        //int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] nums = {1, 2};
        Solution solution = new Solution();
        int min = solution.findMin(nums);
        System.out.println(min);
    }
}
