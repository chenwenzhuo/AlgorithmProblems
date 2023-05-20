package com.hey_there._259_3SumSmaller;

import java.util.Arrays;

public class Solution2 {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3) return 0;//需要三个不同的数，数组长度不足3时直接返回0
        Arrays.sort(nums);
        int cnt = 0;
        int tempTarget;
        for (int i = 0; i < nums.length; i++) {
            //固定i时，需要找两个数加起来小于tempTarget
            tempTarget = target - nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] >= tempTarget) {
                    right--;
                } else {
                    cnt += (right - left);
                    left++;
                }
            }
        }
        return cnt;
    }
}
