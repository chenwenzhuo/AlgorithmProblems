package com.hey_there.IncreasingSubsequences;

import java.util.LinkedList;
import java.util.List;

public class Solution_mine {
    private List<List<Integer>> res = new LinkedList<>();
    private LinkedList<Integer> temp = new LinkedList<>();
    private int[] nums;

    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(i + 1);
            temp.removeLast();
        }
        return res;
    }

    private void backtrack(int curPos) {
        if (curPos == nums.length) {
            return;
        }

        for (int i = curPos; i < nums.length; i++) {
            if (nums[i] >= temp.getLast()) {
                temp.add(nums[i]);
                if (res.contains(temp)) {
                    temp.removeLast();
                    continue;
                }
                res.add(new LinkedList<>(temp));
                backtrack(i + 1);
                temp.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Solution_mine solution = new Solution_mine();
        List<List<Integer>> result = solution.findSubsequences(nums);
        System.out.println(result);
    }
}
