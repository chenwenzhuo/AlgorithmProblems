package com.hey_there.DailyProblems.May.SingleNumber;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int singleNumber_sortArray(int[] nums) {
        Arrays.sort(nums);
        int index = 0;
        int target = 0;
        while (index < nums.length) {
            if (index == nums.length - 1 || nums[index] != nums[index + 1]) {
                target = nums[index];
                break;
            }
            index += 2;
        }
        return target;
    }

    public int singleNumber_withSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                set.remove(n);
                continue;
            }
            set.add(n);
        }
        return (Integer) set.toArray()[0];
    }

    public int singleNumber(int[] nums) {
        int single = 0;
        for (int n : nums) {
            single ^= n;//异或运算
        }
        return single;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 2, 1, 2};
        Solution solution = new Solution();
        int ans = solution.singleNumber(nums);
        System.out.println(ans);
    }
}
