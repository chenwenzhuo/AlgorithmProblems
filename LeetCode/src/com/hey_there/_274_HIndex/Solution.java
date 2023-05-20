package com.hey_there._274_HIndex;

import java.util.Arrays;

public class Solution {
    public int hIndex_1(int[] citations) {
        Arrays.sort(citations);
        int left = 0, right = citations.length;
        while (left < right) {
            int mid = (left + right) / 2;
            int count = citations.length - mid;
            if (citations[mid] >= count)
                right = mid;
            else left = mid + 1;
        }
        return citations.length - left;
    }

    public int hIndex_2(int[] citations) {
        int n = citations.length, tot = 0;
        int[] counter = new int[n + 1];
        for (int citation : citations) {
            if (citation >= n) counter[n]++;
            else counter[citation]++;

        }
        for (int i = n; i >= 0; i--) {
            tot += counter[i];
            if (tot >= i) return i;
        }
        return 0;
    }
}
