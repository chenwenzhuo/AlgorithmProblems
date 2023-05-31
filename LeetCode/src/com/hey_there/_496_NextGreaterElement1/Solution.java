package com.hey_there._496_NextGreaterElement1;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //单调栈求出nums2中每个元素的下一个更大元素
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int[] ng = new int[nums2.length];
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i])
                stack.poll();
            ng[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums2[i]);
        }
        //数组不含重复元素，将nums1元素与下标存在map中
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++)
            map.put(nums1[i], i);
        //从nums2中找出nums1元素的下一个更大元素
        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                ans[map.get(nums2[i])] = ng[i];
            }
        }
        return ans;
    }
}
