package com.hey_there.IncreasingSubsequences;

import java.util.LinkedList;
import java.util.List;

class Solution {
    LinkedList<Integer> temp = new LinkedList<>();
    LinkedList<List<Integer>> ans = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfs(0, Integer.MIN_VALUE, nums);
        return ans;
    }

    public void dfs(int cur, int last, int[] nums) {
        if (cur == nums.length) {
            if (temp.size() >= 2) {
                ans.add(new LinkedList<>(temp));
            }
            return;
        }
        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            dfs(cur + 1, nums[cur], nums);
            temp.removeLast();
        }
        if (nums[cur] != last) {
            dfs(cur + 1, last, nums);
        }
    }
}
