package com.hey_there.InterviewProblem_53.Problem_1_OccurrencesOfNumberInSortedArray;

public class Solution {
    public int search_1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        int beginIdx = -1, endIdx = -1;
        //查找beginIdx
        int left = 0, right = length - 1;
        while (left < right) {//当left与right相等时，即查找范围仅有一个数时退出循环
            int mid = (left + right) / 2;
            if (nums[mid] < target) left = mid + 1;
            else if (nums[mid] > target) right = mid - 1;
            else right--;
        }
        //nums[left]等于target则记录下标left，否则说明数组中没有值target
        if (nums[left] == target) beginIdx = left;
        else if (left + 1 < length && nums[left + 1] == target) beginIdx = left + 1;
        else return 0;

        //查找endIdx
        left = 0;
        right = length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) right = mid - 1;
            else if (nums[mid] < target) left = mid + 1;
            else left++;
        }
        if (nums[right] == target) endIdx = right;
        else endIdx = right - 1;
        return endIdx - beginIdx + 1;
    }

    public int search_2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;//数组长度
        int index = binarySearch(nums, target);//查找数组中是否存在target
        //index为-1表示数组中不存在target
        if (index == -1) return 0;
        //线性搜索起点和终点
        int start = index, end = index;
        while (start >= 0 && nums[start] == target) start--;
        while (end <= len - 1 && nums[end] == target) end++;
        return end - start - 1;
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) right = mid - 1;
            else if (nums[mid] < target) left = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 1;

        Solution solution = new Solution();
        int occ = solution.search_1(nums, target);
        System.out.println(occ);
    }
}
