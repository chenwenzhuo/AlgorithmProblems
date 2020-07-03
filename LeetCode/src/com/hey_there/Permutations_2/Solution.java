package com.hey_there.Permutations_2;

import java.util.*;

public class Solution {
    private int[] nums;
    private List<List<Integer>> permutations;
    private boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.nums = nums;
        this.permutations = new ArrayList<>();
        this.used = new boolean[nums.length];

        //对待选数组进行排序
        Arrays.sort(nums);
        Deque<Integer> pmt_temp = new ArrayDeque<>();
        backtrack(0, pmt_temp);
        return permutations;
    }

    private void backtrack(int pmtLen, Deque<Integer> pmt_temp) {
        if (pmtLen == nums.length) {
            permutations.add(new ArrayList<>(pmt_temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            pmt_temp.add(nums[i]);
            used[i] = true;
            backtrack(pmtLen + 1, pmt_temp);
            pmt_temp.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        Solution solution = new Solution();
        System.out.println(solution.permuteUnique(nums));
    }
}
