package com.hey_there.Array.MergeSortedArrays;

public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] copyOfNums1 = new int[m];
        if (m >= 0) {
            System.arraycopy(nums1, 0, copyOfNums1, 0, m);
        }
        int idx1 = 0, idx2 = 0;
        for (int i = 0; i < m + n; i++) {
            if (idx1 < m && (idx2 >= n || copyOfNums1[idx1] < nums2[idx2])) {
                nums1[i] = copyOfNums1[idx1];
                idx1++;
            } else if (idx2 < n && (idx1 >= m || copyOfNums1[idx1] >= nums2[idx2])) {
                nums1[i] = nums2[idx2];
                idx2++;
            }
        }
    }
}
