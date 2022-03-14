package com.hey_there._599_MinimumIndexSumOfTwoLists;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        //将list1所有元素及下标存入map中
        HashMap<String, Integer> list1_map = new HashMap<>();
        for (int i = 0; i < list1.length; i++)
            list1_map.put(list1[i], i);
        Set<String> resSet = new HashSet<>();//最小索引和的字符串集合
        int minIdxSum = 10000;//最小索引和
        //遍历list2，计算最小索引和
        for (int i = 0; i < list2.length; i++) {
            //不是公共元素，直接跳过
            if (!list1_map.containsKey(list2[i])) continue;
            //公共元素，计算索引和
            int curIdxSum = i + list1_map.get(list2[i]);
            //当前索引和等于最小索引和，将公共元素加入集合
            if (curIdxSum == minIdxSum) resSet.add(list2[i]);
            else if (curIdxSum < minIdxSum) {//找到更小的索引和，重新记录公共元素
                resSet.clear();
                resSet.add(list2[i]);
                minIdxSum = curIdxSum;
            }
        }
        //将集合转为数组
        return resSet.toArray(new String[0]);
    }
}
