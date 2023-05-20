package com.hey_there._259_3SumSmaller;

import java.util.Arrays;

public class Solution1 {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3) return 0;//需要三个不同的数，数组长度不足3时直接返回0
        Arrays.sort(nums);
        int cnt = 0;
        int tempTarget;
        for (int i = 0; i < nums.length; i++) {
            //固定i时，需要找两个数加起来小于tempTarget
            tempTarget = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                //固定i、j时，需要找一个数小于tempTarget
                tempTarget -= nums[j];
                int bound = binarySearch(nums, j + 1, tempTarget);
                //边界下标bound为负，表示对于当前i、j，第三个下标k无解，
                //由于数组是升序，更大的j也不会有解，直接继续外层循环
                if (bound < 0) break;
                cnt += (bound - j);//有解时，区间[j+1,bound]内都是有效解
                tempTarget += nums[j];//进行下一次循环前，恢复tempSum的值
            }
        }
        return cnt;
    }

    //二分查找，在升序数组下标start到尾部的范围内查找小于target的最大的数
    private int binarySearch(int[] nums, int start, int target) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                if (mid + 1 < nums.length && nums[mid + 1] >= target) {
                    left = mid;
                    break;
                } else left = mid + 1;
            }
        }
        //nums[left]<target则有解，否则返回-1表示无解
        return left < nums.length && nums[left] < target ? left : -1;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        /*int[] nums = {2, 0, 0, 2, -2};
        int target = 2;*/
        int[] nums = {-1, 1, -1, -1};
        int target = -1;
        int cnt = solution.threeSumSmaller(nums, target);
        System.out.println(cnt);
    }
}
