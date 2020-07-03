package com.hey_there.UglyNumber_2;

public class Solution {
    public int nthUglyNumber(int n) {
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int idx = 1; idx < n; idx++) {
            int nextUgly_i2 = uglyNums[i2] * 2;
            int nextUgly_i3 = uglyNums[i3] * 3;
            int nextUgly_i5 = uglyNums[i5] * 5;
            int nextUgly = Math.min(nextUgly_i2, Math.min(nextUgly_i3, nextUgly_i5));
            uglyNums[idx] = nextUgly;

            if (nextUgly == nextUgly_i2) {
                i2++;
            }
            if (nextUgly == nextUgly_i3) {
                i3++;
            }
            if (nextUgly == nextUgly_i5) {
                i5++;
            }
        }
        return uglyNums[n - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.nthUglyNumber(10);
        System.out.println(res);
    }
}
