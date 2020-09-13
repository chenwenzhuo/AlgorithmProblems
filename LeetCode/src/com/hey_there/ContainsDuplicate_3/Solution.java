package com.hey_there.ContainsDuplicate_3;

import java.util.HashSet;

public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length <= 1 || k == 0) return false;

        if (t == 0) return containsNearbyDuplicate(nums, k);

        HashSet<Integer> prevK = new HashSet<>();//当前位置的保存前k个元素
        prevK.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            long up = ((long) nums[i]) + t;
            long down = ((long) nums[i]) - t;
            for (int n : prevK) {
                if (down <= n && n <= up) return true;
            }

            prevK.add(nums[i]);
            if (prevK.size() > k) prevK.remove(nums[i - k]);
        }
        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> prevK = new HashSet<>();//当前位置的保存前k个元素
        for (int i = 0; i < nums.length; i++) {
            if (prevK.contains(nums[i])) return true;

            prevK.add(nums[i]);
            if (prevK.size() > k) prevK.remove(nums[i - k]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2147483640, 2147483641};
        int k = 1;
        int t = 100;

        Solution solution = new Solution();
        boolean res = solution.containsNearbyAlmostDuplicate(nums, k, t);
        System.out.println(res);
    }
}
