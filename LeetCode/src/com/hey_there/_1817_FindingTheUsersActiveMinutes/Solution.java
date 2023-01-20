package com.hey_there._1817_FindingTheUsersActiveMinutes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        //统计每个用户的所有活动分钟
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int[] log : logs) {
            HashSet<Integer> curUserSet;
            if (map.containsKey(log[0])) curUserSet = map.get(log[0]);
            else curUserSet = new HashSet<>();
            curUserSet.add(log[1]);
            map.put(log[0], curUserSet);
        }
        int[] answer = new int[k];
        for (Map.Entry<Integer, HashSet<Integer>> entry : map.entrySet()) {
            HashSet<Integer> set = entry.getValue();
            answer[set.size() - 1]++;//answer下标从1开始，故减1
        }
        return answer;
    }
}
