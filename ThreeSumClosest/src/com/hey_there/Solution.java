package com.hey_there;

import java.util.Arrays;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int length = nums.length;
        Arrays.sort(nums);//对数组排序

        int closestSum = Integer.MAX_VALUE - 1;//初始值赋为int的最大值，方便计算
        //int closestSum = nums[length - 1] * 3 + 1;
        for (int i = 0; i < length; i++) {
            int left = i + 1;
            int right = length - 1;

            while (left < right) {
                int threeSum = nums[i] + nums[left] + nums[right];//计算三数之和

                if (81 == threeSum) {
                    System.out.println("三个数：" + nums[i] + "," + nums[left] + "," + nums[right]);
                }

                //若当前 threeSum 比 closestSum 更接近 target，更新 closestSum 的值
                /*System.out.println("threeSum与target相距：" + Math.abs(threeSum - target));
                System.out.println("closestSum与target相距：" + Math.abs(closestSum - target));*/
                if (Math.abs(threeSum - target) < Math.abs(closestSum - target)) {
                    closestSum = threeSum;
                }

                if (closestSum == target) {
                    //closestSum 等于 target 时，可直接返回
                    return closestSum;
                } else if (closestSum < target) {
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
