package com.hey_there._2016_MaximumDifferenceBetweenIncreasingElements;

public class Solutions {
    public int maximumDifference(int[] nums) {
        if (nums.length <= 1) return -1;
        int minPrefix = nums[0], maxDiff = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > minPrefix)
                maxDiff = Math.max(maxDiff, nums[i] - minPrefix);
            else
                minPrefix = Math.min(minPrefix, nums[i]);
        }
        return maxDiff;
    }
}
