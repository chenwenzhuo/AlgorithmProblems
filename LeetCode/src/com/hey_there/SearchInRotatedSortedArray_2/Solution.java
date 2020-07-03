package com.hey_there.SearchInRotatedSortedArray_2;

public class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[start] == nums[mid]) {
                start++;
                continue;
            }
            //前半部分有序
            if (nums[start] < nums[mid]) {
                //target在前半部分
                if (nums[mid] > target && nums[start] <= target) {
                    end = mid - 1;
                } else {  //否则，去后半部分找
                    start = mid + 1;
                }
            } else {
                //后半部分有序
                //target在后半部分
                if (nums[mid] < target && nums[end] >= target) {
                    start = mid + 1;
                } else {  //否则，去后半部分找
                    end = mid - 1;

                }
            }
        }
        //一直没找到，返回false
        return false;
    }

    private int findRotateIndex(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;

        if (nums[left] < nums[right]) {
            return 0;
        }
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1])
                return mid + 1;
            else {
                if (nums[mid] <= nums[left]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return 0;
    }

    private boolean binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return true;
            } else {
                if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /*int[] nums = {1, 3, 1, 1, 1};
        int target = 3;*/
        /*int[] nums = {1, 1};
        int target = 0;*/
        int[] nums = {2, 2, 2, 3, 1};
        int target = 1;
        /*int[] nums = {3, 1, 1};
        int target = 3;*/

        Solution solution = new Solution();
        boolean exist = solution.search(nums, target);
        System.out.println("exist: " + exist);
    }
}
