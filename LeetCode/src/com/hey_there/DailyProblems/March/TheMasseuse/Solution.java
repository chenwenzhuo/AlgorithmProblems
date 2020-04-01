package com.hey_there.DailyProblems.March.TheMasseuse;

public class Solution {
    public int massage(int[] nums) {
        int len = nums.length;
        //对长度较小的情况直接比较
        if (len == 0) {
            return 0;
        }else if (len == 1) {
            return nums[0];
        } else if (len == 2) {
            return Math.max(nums[0], nums[1]);
        } else if (len == 3) {
            return Math.max(nums[0] + nums[2], nums[1]);
        }

        int[] reservationLens = new int[len];
        //手动填充前三位
        reservationLens[0] = nums[0];
        reservationLens[1] = nums[1];
        reservationLens[2] = nums[0] + nums[2];

        int ans = Math.max(reservationLens[0],
                Math.max(reservationLens[1], reservationLens[2]));
        for (int i = 3; i < len; i++) {
            reservationLens[i] = nums[i] +
                    Math.max(reservationLens[i - 2], reservationLens[i - 3]);
            ans = Math.max(ans, reservationLens[i]);
        }
        return ans;
    }
}
