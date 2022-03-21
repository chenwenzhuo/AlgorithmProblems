package com.hey_there._167_TwoSum2_InputArraySorted;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] < target) {
                left++;
                continue;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
                continue;
            }
            //返回的是两数在数组中的顺序，不是下标，所以需要加1
            res[0] = left + 1;
            res[1] = right + 1;
            break;
        }
        return res;
    }
}
