package com.hey_there.IntersectionOfTwoArrays_2;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int idx1 = 0, idx2 = 0;
        ArrayList<Integer> intersection = new ArrayList<>(Math.min(len1, len2));
        while (idx1 < len1 && idx2 < len2) {
            if (nums1[idx1] < nums2[idx2]) {
                idx1++;
            } else if (nums1[idx1] > nums2[idx2]) {
                idx2++;
            } else {
                intersection.add(nums1[idx1]);
                idx1++;
                idx2++;
            }
        }
        int[] intersectionArr = new int[intersection.size()];
        for (int i = 0; i < intersectionArr.length; i++) {
            intersectionArr[i] = intersection.get(i);
        }
        return intersectionArr;
    }
}
