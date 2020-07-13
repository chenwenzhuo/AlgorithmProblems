package com.hey_there.IntersectionOfTwoArrays_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public int[] intersect_1(int[] nums1, int[] nums2) {
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
        int idx = 0;
        for (int n : intersection) {
            intersectionArr[idx++] = n;
        }
        return intersectionArr;
    }
    public int[] intersect_2(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        //将nums1数组所有元素存入HashMap中
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n1 : nums1) {
            int count = map.getOrDefault(n1, 0) + 1;
            map.put(n1, count);
        }

        ArrayList<Integer> intersection = new ArrayList<>(Math.min(len1, len2));
        //枚举nums2中所有元素
        for (int n2 : nums2) {
            int count = map.getOrDefault(n2, 0);//获取n2在nums1中的出现次数
            if (count > 0) {
                intersection.add(n2);
                count--;
                if (count > 0) {
                    map.put(n2, count);
                } else {
                    map.remove(n2);
                }
            }
        }
        int[] intersectionArr = new int[intersection.size()];
        int idx = 0;
        for (int n : intersection) {
            intersectionArr[idx++] = n;
        }
        return intersectionArr;
    }
}
