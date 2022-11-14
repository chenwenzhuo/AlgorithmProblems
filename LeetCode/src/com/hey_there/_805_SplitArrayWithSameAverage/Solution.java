package com.hey_there._805_SplitArrayWithSameAverage;

import java.util.ArrayList;
import java.util.HashSet;

public class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        if (nums.length == 1)//长度为1，无法分为两个非空子集
            return false;
        int length = nums.length, halfLen = length / 2;
        int sum = 0;
        for (int n : nums)//对数组nums求和
            sum += n;
        //剪枝，若没有i满足sum * i % length == 0，则无法分割
        boolean isPossible = false;
        for (int i = 1; i <= halfLen; i++)//剪枝，仅需要遍历长度小于等于length/2的子数组
            if (sum * i % length == 0) {
                isPossible = true;
                break;
            }
        if (!isPossible)
            return false;
        ArrayList<HashSet<Integer>> dp = new ArrayList<>(halfLen + 1);
        for (int i = 0; i <= halfLen; i++)
            dp.add(new HashSet<>());
        dp.get(0).add(0);
        for (int num : nums) {
            for (int i = halfLen; i >= 1; i--) {
                HashSet<Integer> prevSet = dp.get(i - 1);
                for (int x : prevSet) {
                    int curr = x + num;
                    if (curr * length == sum * i)
                        return true;
                    dp.get(i).add(curr);
                }
            }
        }
        return false;
    }
}
