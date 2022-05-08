package com.hey_there._442_FindAllDuplicatesInAnArray;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /* 数组长度为n，数组元素取值范围闭区间[1,n]。
     * 遍历数组，将值为 i 的元素放到下标 i-1 处，
     * 完成后再次遍历，若nums[i-1]不等于i，则nums[i-1]为重复元素*/
    public List<Integer> findDuplicates_1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1])
                swap(nums, i, nums[i] - 1);
        }
        List<Integer> dup = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) dup.add(nums[i]);
        }
        return dup;
    }

    private void swap(int[] nums, int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    /* 遍历数组，对于元素nums[i]，将下标nums[i]-1处的元素标记为负。
     * 遍历过程中若遇到nums[nums[i]-1]为负，说明nums[i]重复出现。*/
    public List<Integer> findDuplicates_2(int[] nums) {
        List<Integer> dup = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            //当前元素可能被标记为负，故需要取绝对值作为下标
            int x = Math.abs(nums[i]);
            if (nums[x - 1] > 0)
                nums[x - 1] *= -1;
            else
                dup.add(x);
        }
        return dup;
    }
}
