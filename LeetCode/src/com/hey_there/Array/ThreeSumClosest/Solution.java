package com.hey_there.Array.ThreeSumClosest;

import java.util.Arrays;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int length = nums.length;
        Arrays.sort(nums);//对数组排序

        int closestSum = nums[0] + nums[1] + nums[2];//为closestSum赋初值

        for (int i = 0; i < length; i++) {
            int left = i + 1;
            int right = length - 1;

            while (left < right) {
                int threeSum = nums[i] + nums[left] + nums[right];//计算三数之和

                //若当前 threeSum 比 closestSum 更接近 target，更新 closestSum 的值
                if (Math.abs(threeSum - target) < Math.abs(closestSum - target)) {
                    closestSum = threeSum;
                }

                if (threeSum == target) {
                    //当前三数之和等于 target 时，可直接返回
                    return target;
                } else if (threeSum < target) {
                    //closestSum 小于 target 时，增大left
                    left++;
                } else {
                    //closestSum 大于 target 时，减小right
                    right--;
                }
            }
        }
        return closestSum;
    }
}
