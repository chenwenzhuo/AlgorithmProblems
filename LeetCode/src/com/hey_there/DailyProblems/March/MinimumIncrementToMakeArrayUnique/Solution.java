package com.hey_there.DailyProblems.March.MinimumIncrementToMakeArrayUnique;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int minIncrementForUnique_1(int[] arr) {
        //先对数组排序
        Arrays.sort(arr);

        //从下标1开始遍历数组
        int incrementCount = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {//发现重复值
                //将所有重复值加一
                incrementCount += duplicateValuesIncreaseByOne(arr, i);
            }
        }
        return incrementCount;
    }

    public int minIncrementForUnique(int[] arr) {
        //统计所有数字及其出现次数
        Map<Integer, Integer> numAndOccurrence = new HashMap<>();
        for (int n : arr) {
            if (numAndOccurrence.containsKey(n)) {
                numAndOccurrence.put(n, numAndOccurrence.get(n) + 1);
            } else {
                numAndOccurrence.put(n, 1);
            }
        }

        int incrementCount=0;
        return 0;
    }

    private int duplicateValuesIncreaseByOne(int[] arr, int pos) {
        int count = 0;
        int duplicateVal = arr[pos];
        while (pos < arr.length && arr[pos] == duplicateVal) {
            arr[pos] += 1;
            count++;
            pos++;
        }
        return count;
    }
}
