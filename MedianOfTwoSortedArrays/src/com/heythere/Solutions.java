package com.heythere;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> nums = new ArrayList<>();
        int index_1 = 0, index_2 = 0;

        while (nums.size() <= (nums1.length + nums2.length) / 2) {
            if (nums1[index_1] < nums2[index_2]) {

            }
        }



        return 0;
    }

    /**
     * 给定两个有序递增数组，
     * 返回两数组中从beginIndex1和beginIndex2开始的最小数
     *
     * @param nums1 第一个有序数组
     * @param beginIndex1 nums1开始遍历的下标
     * @param nums2 第二个有序数组
     * @param beginIndex2 nums2开始遍历的下标
     * @return 两数组中从beginIndex1和beginIndex2开始的最小数
     */
    public int smallestOfTwoArrays(int[] nums1, int beginIndex1, int[] nums2, int beginIndex2) {


        return 0;
    }
}
