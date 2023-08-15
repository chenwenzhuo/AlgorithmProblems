package com.hey_there._163_MissingRanges;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) {
            ArrayList<Integer> range = new ArrayList<>();
            range.add(lower);
            range.add(upper);
            res.add(range);
            return res;
        }
        if (nums[0] > lower) {
            ArrayList<Integer> range = new ArrayList<>();
            range.add(lower);
            range.add(nums[0] - 1);
            res.add(range);
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 1) {
                ArrayList<Integer> range = new ArrayList<>();
                range.add(nums[i - 1] + 1);
                range.add(nums[i] - 1);
                res.add(range);
            }
        }
        if (nums[nums.length - 1] < upper) {
            ArrayList<Integer> range = new ArrayList<>();
            range.add(nums[nums.length - 1] + 1);
            range.add(upper);
            res.add(range);
        }
        return res;
    }
}
