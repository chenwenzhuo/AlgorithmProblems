package com.hey_there.DailyProblems.April.ReversePairsInArray;

public class Solution {
    private int numReversePairs = 0;

    public int reversePairs(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        mergeSort(nums, 0, nums.length - 1);
        return numReversePairs;
    }

    private int[] mergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return new int[]{nums[left]};
        }
        //对左半部分和右半部分递归进行排序
        int mid = (left + right) / 2;
        int[] sortedLeft = mergeSort(nums, left, mid);
        int[] sortedRight = mergeSort(nums, mid + 1, right);
        //左半部分和右半部分均有序后，进行归并
        int[] merged = new int[right - left + 1];
        int mergedIndex = 0, leftIndex = 0, rightIndex = 0;
        while (leftIndex < sortedLeft.length && rightIndex < sortedRight.length) {
            if (sortedLeft[leftIndex] <= sortedRight[rightIndex]) {
                /* 右半部分的值较大时，表示右半部分比sortedLeft[leftIndex]更小的值都已合并到merged数组
                 * 将sortedLeft[leftIndex]对逆序对的“贡献值”累加到numReversePairs*/
                numReversePairs += rightIndex;
                merged[mergedIndex] = sortedLeft[leftIndex];
                leftIndex++;
            } else {
                merged[mergedIndex] = sortedRight[rightIndex];
                rightIndex++;
            }
            mergedIndex++;
        }
        //当右半部分全部合并进merged数组而左半部分还有剩余时，左半部分剩余的数均对numReversePairs有贡献
        while (leftIndex < sortedLeft.length) {
            numReversePairs += rightIndex;
            merged[mergedIndex] = sortedLeft[leftIndex];
            mergedIndex++;
            leftIndex++;
        }
        //右半部分有剩余时不计贡献值
        while (rightIndex < sortedRight.length) {
            merged[mergedIndex] = sortedRight[rightIndex];
            mergedIndex++;
            rightIndex++;
        }
        return merged;
    }

    public static void main(String[] args) {
        //int[] nums = {7, 5, 6, 4};
        int[] nums = {1, 3, 2, 3, 1};
        Solution solution = new Solution();
        int ans = solution.reversePairs(nums);
        System.out.println(ans);
    }
}
