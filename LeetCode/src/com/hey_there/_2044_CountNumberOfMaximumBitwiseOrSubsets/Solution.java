package com.hey_there._2044_CountNumberOfMaximumBitwiseOrSubsets;

public class Solution {
    private int[] nums;
    private int maxOr = 0, count = 0;

    public int countMaxOrSubsets(int[] nums) {
        this.nums = nums;
        dfs(0, 0);
        return count;
    }

    private void dfs(int pos, int orVal) {
        if (pos == nums.length) {
            if (orVal == maxOr) count++;
            else if (orVal > maxOr) {
                maxOr = orVal;
                count = 1;
            }
            return;
        }
        dfs(pos + 1, orVal | nums[pos]);
        dfs(pos + 1, orVal);
    }
}
