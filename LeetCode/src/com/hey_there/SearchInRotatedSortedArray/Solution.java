package com.hey_there.SearchInRotatedSortedArray;

public class Solution {
    public int search(int[] nums, int target) {
        int len = nums.length;//数组长度
        //处理特殊值
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return nums[0] == target ? 0 : -1;
        }

        //计算最小数的下标
        int rotateIndex = findRotateIndex(nums);

        //以rotateIndex分界，二分搜索两侧数组
        int leftIndex = binarySearch(nums, 0, rotateIndex - 1, target);
        int rightIndex = binarySearch(nums, rotateIndex, len - 1, target);

        if (leftIndex != -1) {//若在rotateIndex左侧找到target，返回下标
            return leftIndex;
        } else if (rightIndex != -1) {//若在rotateIndex右侧找到target，返回下标
            return rightIndex;
        }
        return -1;//若都未找到，返回-1
    }

    public int findRotateIndex(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;

        if (nums[left] < nums[right]) {
            return 0;
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1])
                return mid + 1;
            else {
                if (nums[mid] < nums[left])
                    right = mid - 1;
                else
                    left = mid + 1;
            }
        }
        return 0;
    }

    public int binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        Solution solution = new Solution();
        System.out.println(solution.search(nums, 3));
    }
}
