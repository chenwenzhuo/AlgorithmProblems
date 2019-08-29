package com.hey_there;

public class Solution {
    public int numTrees(int n) {
        int[] treeCounts = new int[n + 1];
        treeCounts[0] = 1;

        for (int i = 1; i <= n; i++) {
            treeCounts[i] = 0;
            for (int j = 1; j <= i; j++) {
                treeCounts[i] += treeCounts[j - 1] * treeCounts[i - j];
            }
        }

        return treeCounts[n];
    }
}
