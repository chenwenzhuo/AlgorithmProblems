package com.hey_there.DailyProblems.June.KthLargestElementInArray;

public class Solution {
    //基于快排的快速选择算法
    public int findKthLargest(int[] nums, int k) {
        return quickSelection(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelection(int[] nums, int begin, int end, int targetIdx) {
        if (begin == end) {
            return nums[begin];//起止点相等时，直接返回此位置上的数
        }
        //对[begin,end]范围内的数进行快排
        int pivot = nums[begin];
        int i = begin, j = end;
        while (i < j) {
            //从后向前搜索，直到找到比pivot小的数
            while (i < j && nums[j] > pivot) {
                j--;
            }
            //将数组后方比pivot小的数放到前面
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }
            //从前向后搜索，直到找到比pivot大的数
            while (i < j && nums[i] < pivot) {
                i++;
            }
            //将数组前方比pivot大的数放到后面
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = pivot;//将pivot放回数组中
        if (i > targetIdx) {
            //若pivot的位置在倒数第k个位置之后，则递归左半部分
            return quickSelection(nums, begin, i - 1, targetIdx);
        } else if (i < targetIdx) {
            //若pivot的位置在倒数第k个位置之前，则递归右半部分
            return quickSelection(nums, i + 1, end, targetIdx);
        }
        ////若pivot的位置就是倒数第k个位置，直接返回此位置上的值
        return nums[i];
    }
}
