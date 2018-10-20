package com.heythere;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> nums = new ArrayList<>();
        int index_1 = 0, index_2 = 0;

        int finalLength = (nums1.length + nums2.length) / 2;//循环退出时nums的长度
        while (nums.size() <= finalLength) {
            if (nums1[index_1] < nums2[index_2]) {
                nums.add(nums1[index_1]);
                index_1++;
            } else {
                nums.add(nums2[index_2]);
                index_2++;
            }
        }

        double median;
        if (1 == (nums1.length + nums2.length) % 2) {
            median = nums.get(nums.size() - 1);
        } else {
            int size = nums.size();
            median = (nums.get(size - 1) + nums.get(size - 2)) / 2.0;
        }

        return median;
    }

    /**
     * 给定两个有序递增数组，
     * 返回两数组中从beginIndex1和beginIndex2开始的最小数
     *
     * @param nums1       第一个有序数组
     * @param beginIndex1 nums1开始遍历的下标
     * @param nums2       第二个有序数组
     * @param beginIndex2 nums2开始遍历的下标
     * @return 两数组中从beginIndex1和beginIndex2开始的最小数
     */
    public int smallestOfTwoArrays(int[] nums1, int beginIndex1, int[] nums2, int beginIndex2) {


        return 0;
    }
}
