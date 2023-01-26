package com.hey_there._1663_SmallestStringWithGivenNumericValue;

import java.util.Arrays;

public class Solution {
    public String getSmallestString_1(int n, int k) {
        //用一个数组表示字符串每一位的数值
        int[] arr = new int[n];
        Arrays.fill(arr, 1);//k>=n，故每一位至少为1
        k -= n;
        //将k剩余的值从后往前填入数组每一位
        int idx = n - 1;
        while (k > 0 && idx >= 0) {
            if (k >= 25) {
                arr[idx] += 25;
                k -= 25;
            } else {
                arr[idx] += k;
                k = 0;
            }
            idx--;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            char cur = (char) ('a' + num - 1);
            sb.append(cur);
        }
        return sb.toString();
    }

    public String getSmallestString_2(int n, int k) {
        StringBuilder ans = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int lower = Math.max(1, k - (n - i) * 26);
            k -= lower;
            ans.append((char) ('a' + lower - 1));
        }
        return ans.toString();
    }
}
