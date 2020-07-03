package com.hey_there.FindPeakElement;

public class Solution {
    private int[] nums;

    public int findPeakElement(int[] nums) {
        //处理特殊情况
        if (nums.length == 1) {
            return 0;//数组长度为1时，直接得出答案（规定nums[-1]和nums[length]为-1）
        }
        this.nums = nums;
        //递归二分查找
        return recursiveBinarySearch(0, nums.length - 1);
    }

    private int recursiveBinarySearch(int left, int right) {
        //递归结束条件
        if (left > right) {
            return -1;
        }
        //判断left和right的中间元素是否是peak
        int mid = (left + right) / 2;
        if ((mid == 0 && nums[mid] > nums[mid + 1]) ||//判断peak是否出现在数组第一个元素
                (mid == nums.length - 1 && nums[mid] > nums[mid - 1]) ||//判断peak是否出现在数组最后一个元素
                //判断peak是否出现在数组中间，首先要保证mid的值不为0和length-1
                (mid > 0 && mid < nums.length && nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1])) {
            return mid;
        }
        //left和right的中间元素不是peak，递归向左右查找
        //递归向左
        int leftPeak = recursiveBinarySearch(left, mid - 1);
        //若左边找到则直接返回
        if (leftPeak != -1) {
            return leftPeak;
        }
        //左边未找到则递归向右
        return recursiveBinarySearch(mid + 1, right);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        Solution solution = new Solution();
        System.out.println(solution.findPeakElement(nums));
    }
}
