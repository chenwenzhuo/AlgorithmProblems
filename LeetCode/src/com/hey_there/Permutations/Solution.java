package com.hey_there.Permutations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    private List<List<Integer>> permutations;

    public List<List<Integer>> permute(int[] nums) {
        permutations = new ArrayList<>();
        LinkedList<Integer> pmt_temp = new LinkedList<>();
        backtrack(nums, pmt_temp);
        return permutations;
    }

    private void backtrack(int[] nums, LinkedList<Integer> pmt_temp) {
        if (pmt_temp.size() == nums.length) {
            permutations.add(new ArrayList<>(pmt_temp));
            return;
        }

        for (int num : nums) {
            if (pmt_temp.contains(num)) {
                continue;
            }
            pmt_temp.add(num);
            backtrack(nums, pmt_temp);
            pmt_temp.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};

        Solution solution = new Solution();
        System.out.println(solution.permute(nums));
    }
}
