package com.hey_there._1984_MinimumDifferenceBetweenHighestAndLowestOfKScores;

import java.util.Arrays;

public class Solutions {
    public int minimumDifference(int[] nums, int k) {
        if (k == 1) return 0;//只取一个数时，差值为0
        //对数组排序
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;//差值的最小值，初始化为最大int数
        //应在排序后的数组中连续取k个数
        for (int i = 0, j = k - 1; j < nums.length; i++, j++) {
            int curDiff = nums[j] - nums[i];//当前k个数中，最大值与最小值的差
            if (curDiff < minDiff) minDiff = curDiff;
        }
        return minDiff;
    }
}
