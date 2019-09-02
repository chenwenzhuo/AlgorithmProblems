package com.heythere;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        int[] nums3 = {};
        int[] nums4 = {1, 2, 3, 4};
        Solutions solutions = new Solutions();
        System.out.println(solutions.findMedianSortedArrays(nums1, nums2));
        System.out.println(solutions.findMedianSortedArrays(nums3, nums4));
        System.out.println("nums3 length " + nums3.length);
    }
}
