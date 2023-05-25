package com.hey_there._870_AdvantageShuffle;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        //大顶堆，数组元素0为nums2下标，元素1为nums2中元素值
        //相当于将nums2元素按降序排序
        PriorityQueue<int[]> pqMax = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < nums2.length; i++)
            pqMax.offer(new int[]{i, nums2[i]});
        Arrays.sort(nums1);//nums1升序排序
        // nums1[left] 是最小值，nums1[right] 是最大值
        int left = 0, right = nums1.length - 1;
        int[] res = new int[nums1.length];
        while (!pqMax.isEmpty()) {
            int[] pair = pqMax.poll();
            //nums1的最大可用元素大于nums2的最大可用元素，此时nums1占据优势
            if (nums1[right] > pair[1]) {
                res[pair[0]] = nums1[right];
                right--;
            }else {//nums1无法占据优势时，用最小可用元素“田忌赛马”
                res[pair[0]] = nums1[left];
                left++;
            }
        }
        return res;
    }
}
