package com.hey_there.Duoyi_September_27th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> genSubsets(int[] nums) {
        List<List<Integer>> subsetsOfNums = recursiveSubsets(nums);
        //将空集从最后的结果中移除
        for (int i = 0; i < subsetsOfNums.size(); i++) {
            List<Integer> curSubset = subsetsOfNums.get(i);
            if (curSubset.size() == 0) {
                subsetsOfNums.remove(i);
                break;
            }
        }
        return subsetsOfNums;
    }

    public List<List<Integer>> recursiveSubsets(int[] nums) {
        //对于null数组和长度为0的数组，返回一个空的list
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        } else if (nums.length == 1) {//对于长度为1的数组，直接构造一个大小为2的list集合
            List<List<Integer>> lists = new ArrayList<>();
            lists.add(new ArrayList<>());
            List<Integer> list = new ArrayList<>();
            list.add(nums[0]);
            lists.add(list);
            return lists;
        }
        int len_nums = nums.length;
        //取前n-1个元素的子数组
        int[] subarray = Arrays.copyOfRange(nums, 0, len_nums - 1);
        //递归计算子数组的子集
        List<List<Integer>> subsetsOfSubarray = recursiveSubsets(subarray);

        //将子数组的子集加入nums数组的子集集合
        List<List<Integer>> subsetsOfNums = new ArrayList<>();
        for (List<Integer> subset : subsetsOfSubarray) {
            List<Integer> copyOfSubset = new ArrayList<>(subset);
            subsetsOfNums.add(copyOfSubset);
            subset.add(nums[len_nums - 1]);
            subsetsOfNums.add(subset);
        }
        return subsetsOfNums;
    }
}
