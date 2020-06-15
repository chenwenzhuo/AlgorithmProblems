package com.hey_there.Array.Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        //对于null数组和长度为0的数组，返回一个空的list
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        } else if (nums.length == 1) {//对于长度为1的数组，直接构造一个大小为2的list集合
            List<List<Integer>> lists = new ArrayList<>();
            lists.add(new ArrayList<>());
            List<Integer> l = new ArrayList<>();
            l.add(nums[0]);
            lists.add(l);
            return lists;
        }
        int len_nums = nums.length;
        //取前n-1个元素的子数组
        int[] subarray = Arrays.copyOfRange(nums, 0, len_nums - 1);
        //递归计算子数组的子集
        List<List<Integer>> subsetsOfSubarray = subsets(subarray);

        //将子数组的子集加入nums数组的子集集合
        List<List<Integer>> subsetsOfNums = new ArrayList<>();
        for (List<Integer> subset : subsetsOfSubarray) {
            //将子数组的子集复制一份加入subsetsOfNums
            List<Integer> copyOfSubset = new ArrayList<>(subset);
            subsetsOfNums.add(copyOfSubset);
            //将nums的最后一个元素加入子数组的子集，再将子集加入subsetsOfNums
            subset.add(nums[len_nums - 1]);
            subsetsOfNums.add(subset);
        }
        return subsetsOfNums;
    }

    public static void main(String[] args) {
        //int[] nums = {1};
        //int[] nums = {1, 2};
        int[] nums = {1, 2, 3};
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.subsets(nums);
        System.out.println(ans);
    }
}
