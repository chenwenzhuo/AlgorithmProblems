package com.hey_there.ContainsDuplicate_2;

import java.util.HashSet;

public class Solution {
    public boolean containsNearbyDuplicate_1(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= i - k && j >= 0; j--) {
                if (nums[i] == nums[j]) return true;
            }
        }
        return false;
    }

    public boolean containsNearbyDuplicate_2(int[] nums, int k) {
        HashSet<Integer> prevK = new HashSet<>();//当前位置的保存前k个元素
        for (int i = 0; i < nums.length; i++) {
            if (prevK.contains(nums[i])) return true;

            prevK.add(nums[i]);
            if (prevK.size() > k) prevK.remove(nums[i - k]);
        }
        return false;
    }
}
