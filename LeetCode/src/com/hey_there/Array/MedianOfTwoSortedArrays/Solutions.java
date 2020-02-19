package com.hey_there.Array.MedianOfTwoSortedArrays;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (0 == nums1.length && 0 == nums2.length) {
            throw new IllegalArgumentException("两数组不可同时为空");
        } else if (0 == nums1.length) {
            return medianOfArray(nums2);
        } else if (0 == nums2.length) {
            return medianOfArray(nums1);
        }

        List<Integer> nums = new ArrayList<>();
        int index_1 = 0, index_2 = 0;

        int finalLength = (nums1.length + nums2.length) / 2;//循环退出时nums的长度
        /*
        由于每次将两数组中最小的数字填入nums中，
        故当nums长度大于总长度一半时，
        可确保中位数已填入nums
         */
        while (nums.size() <= finalLength) {
            //两个index同时超过数组长度，则全部数字都已填入nums，跳出循环
            if (index_1 >= nums1.length && index_2 >= nums2.length) {
                break;
            }
            //当index_1超过nums1长度时，说明nums1所有元素都已填入nums
            //应向nums中填入nums2当前元素
            if (index_1 >= nums1.length && index_2 < nums2.length) {
                nums.add(nums2[index_2]);
                index_2++;
                continue;//填入数字后继续下一次循环
            }
            //当index_2超过nums2长度时，说明nums2所有元素都已填入nums
            //应向nums中填入nums1当前元素
            if (index_2 >= nums2.length && index_1 < nums1.length) {
                nums.add(nums1[index_1]);
                index_1++;
                continue;//填入数字后继续下一次循环
            }

            //两个index都未超过数组长度，则选一个小的填入nums
            if (nums1[index_1] < nums2[index_2]) {
                nums.add(nums1[index_1]);
                index_1++;
            } else {
                nums.add(nums2[index_2]);
                index_2++;
            }
        }

        double median;//中位数
        if (1 == (nums1.length + nums2.length) % 2) {
            median = nums.get(nums.size() - 1);
        } else {
            int size = nums.size();
            median = (nums.get(size - 1) + nums.get(size - 2)) / 2.0;
        }
        return median;
    }

    //返回数组的中位数
    public double medianOfArray(int[] intArray) {
        if (0 == intArray.length) {
            throw new IllegalArgumentException("数组不可为空！");
        }

        int arraySize = intArray.length;
        if (1 == arraySize) {
            return intArray[0];
        } else if (2 == arraySize) {
            return (intArray[0] + intArray[1]) / 2.0;
        } else if (1 == arraySize % 2) {
            return intArray[arraySize / 2];
        } else {
            return (intArray[arraySize / 2 - 1] + intArray[arraySize / 2]) / 2.0;
        }
    }
}
