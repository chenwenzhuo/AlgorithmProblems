package com.hey_there.UniqueNumberOfOccurrences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        //map中存储数字与其出现次数之间的映射
        HashMap<Integer, Integer> num2Occurrence = new HashMap<>();
        //统计数组中每个数字的出现次数
        for (int n : arr) {
            int oldOcc = num2Occurrence.getOrDefault(n, 0);
            num2Occurrence.put(n, oldOcc + 1);
        }
        //检查数组中有没有两个数字的出现次数相同
        HashSet<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : num2Occurrence.entrySet()) {
            Integer occurrence = entry.getValue();
            if (!set.add(occurrence)) return false;
        }
        return true;
    }
}
