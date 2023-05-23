package com.hey_there._410_SplitArrayLargestSum;

public class Solution {
    public int splitArray(int[] nums, int k) {
        int sumNums = 0, maxElem = 0;//整个数组元素和、数组中最大元素
        for (int n : nums) {
            sumNums += n;
            maxElem = Math.max(maxElem, n);
        }
        //分割为一个子数组，则最大子数组和为sumNums
        //分割为nums.length个子数组，则最大子数组和为maxElem
        int low = maxElem, high = sumNums;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            //计算当最大子数组和为mid时，需要分割成几个子数组
            int cnt = 0;
            for (int i = 0; i < nums.length; ) {
                int tempSum = mid;
                while (i < nums.length) {
                    if (tempSum < nums[i]) break;
                    tempSum -= nums[i];
                    i++;
                }
                cnt++;
            }
            if (cnt > k)//分割成的子数组数量比规定数量多，则需要增大子数组和，以减少子数组数量
                low = mid + 1;
            else//分割成的子数组数量不超过规定数量，则尝试减小子数组和，
                high = mid - 1;
        }
        return low;
    }
}
