package com.hey_there.LastRemainingNumber;

import java.util.Arrays;

public class Solution {
    public int lastRemaining_recursive(int n, int m) {
        if (n == 1) {
            return 0;
        }
        return (m + lastRemaining_recursive(n - 1, m)) % n;
    }

    public int lastRemaining(int n, int m) {
        int ans = 0;
        for (int i = 2; i < n + 1; i++) {
            ans = (m + ans) % i;
        }
        return ans;
    }

    //使用数组迭代求解，超时
    public int lastRemaining_arrayIter(int n, int m) {
        int[] nums = new int[n];
        Arrays.fill(nums, 1);
        int removed = 0;
        int index = 0;
        int count = 0;
        while (removed < n - 1) {
            if (nums[index] > 0) {
                count++;
            }
            if (count == m) {
                nums[index] = -1;
                removed++;
                count = 0;
            }
            index++;
            if (index == n) {
                index = 0;
            }
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        long start = System.currentTimeMillis();
        System.out.println(solution.lastRemaining(64372, 115700));
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));
    }
}
