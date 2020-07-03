package com.hey_there.MajorityElement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int majorityElement_my(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        Map<Integer, Integer> map = new HashMap<>();//map用来存储数字及其出现次数
        for (int n : nums) {
            if (!map.containsKey(n)) {
                map.put(n, 1);
            } else {
                int occurrence = map.get(n);
                if (occurrence + 1 > nums.length / 2) {
                    return n;
                }
                map.put(n, occurrence + 1);
            }
        }
        return 0;
    }
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }
}
