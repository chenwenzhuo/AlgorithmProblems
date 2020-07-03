package com.hey_there.FirstMissingPositive;

import java.util.Arrays;

public class Solution {
    private int[] numsArray;
    private int len;

    public int firstMissingPositive(int[] nums) {
        this.numsArray = nums;
        this.len = nums.length;

        Arrays.sort(numsArray);//先对数组排序
        //计算第一个正数的下标
        int firstPositiveIndex = findFirstPositiveIndex();
        if (firstPositiveIndex < 0 || //firstPositiveIndex小于0表示数组中没有正数，则缺失的第一个正数是1
                numsArray[firstPositiveIndex] > 1) {//firstPositiveIndex位置的数大于1，则缺失的第一个正数是1
            return 1;
        }

        int positive = 1;
        while (firstPositiveIndex < len) {
            if (numsArray[firstPositiveIndex] == positive) {
                firstPositiveIndex++;
                //向后遍历数组，直到firstPositiveIndex表示的值改变
                while (firstPositiveIndex < len &&
                        numsArray[firstPositiveIndex] == numsArray[firstPositiveIndex - 1]) {
                    firstPositiveIndex++;
                }
                positive++;
            } else {
                break;
            }
        }
        return positive;
    }

    //查找数组中第一个正数的下标，若没有正数则返回-1
    private int findFirstPositiveIndex() {
        for (int index = 0; index < len; index++) {
            if (numsArray[index] > 0) {
                return index;
            }
        }
        return -1;
    }
}
