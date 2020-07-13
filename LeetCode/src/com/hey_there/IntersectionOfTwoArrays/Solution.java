package com.hey_there.IntersectionOfTwoArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    public int[] intersection_1(int[] nums1, int[] nums2) {
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
                while (idx1 < len1 && nums1[idx1] == nums1[idx1 - 1]) {
                    idx1++;
                }
                while (idx2 < len2 && nums2[idx2] == nums2[idx2 - 1]) {
                    idx2++;
                }
            }
        }
        int[] intersectionArr = new int[intersection.size()];
        for (int i = 0; i < intersectionArr.length; i++) {
            intersectionArr[i] = intersection.get(i);
        }
        return intersectionArr;
    }

    public int[] intersection_2(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int n : nums1) {
            set1.add(n);
        }
        for (int n : nums2) {
            set2.add(n);
        }
        /* retainAll()方法生命在Collection接口中，所有实现Collection接口的集合都实现了此方法。
         * collection1.retainAll(collection2):
         * 1）如果集合collection1=collection2，即两个集合元素完全一样，不执行删除操作，返回值为false；
         * 2）collection1包含于collection2中，不执行删除操作，返回值为false；
         * 3）其他情况下，从collection1中移除不包含在collection2中的元素，返回true。
         * 即，可将retainAll方法看作求两个集合的交集的方法。*/
        set1.retainAll(set2);
        int[] intersection = new int[set1.size()];
        int idx = 0;
        for (int n : set1) {
            intersection[idx++] = n;
        }
        return intersection;
    }
}
