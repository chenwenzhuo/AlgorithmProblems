package com.hey_there._503_NextGreaterElement2;

import java.util.ArrayDeque;

public class Solution {
    public int[] nextGreaterElement(int[] nums) {
        int len = nums.length;
        ArrayDeque<Integer> stack = new ArrayDeque<>();//单调栈
        int[] ans = new int[len];
        //模拟数组长度翻倍，实现循环遍历
        for (int i = 2 * len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % len])
                stack.pop();
            ans[i % len] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % len]);
        }
        return ans;
    }
}
