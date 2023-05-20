package com.hey_there._268_MissingNumber;

public class Solution {
    public int missingNumber(int[] nums) {
        int ans = 0, n = nums.length;
        //将ans与[1,n]闭区间每个数按位异或
        for (int i = 1; i <= n; i++) ans ^= i;
        //将ans与nums数组每个数按位异或
        for (int num : nums) ans ^= num;
        return ans;
    }
}
