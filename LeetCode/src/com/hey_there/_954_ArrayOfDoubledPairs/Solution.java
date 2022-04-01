package com.hey_there._954_ArrayOfDoubledPairs;

import java.util.*;

public class Solution {
    public boolean canReorderDoubled(int[] arr) {
        if (arr.length == 0) return false;
        //map记录arr中每个数及其出现次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : arr)
            map.put(n, map.getOrDefault(n, 0) + 1);
        //将keySet加入List中，并按绝对值升序排序
        ArrayList<Integer> keys = new ArrayList<>(map.keySet().size());
        keys.addAll(map.keySet());
        keys.sort((a, b) -> Math.abs(a) - Math.abs(b));
        for (Integer key : keys) {//按绝对值从小到大遍历map的keySet
            //key对应的value为0，表示key是其他数的二倍且已匹配过
            if (map.get(key) == 0) continue;
            Integer doubledKey = key * 2;
            //map中不存在doubledKey，或key的出现次数多余doubledKey，均无法成功匹配
            if (!map.containsKey(doubledKey) ||
                    map.get(key) > map.get(doubledKey)) {
                return false;
            }
            //将doubledKey的数量减去已匹配的key的数量
            map.put(doubledKey, map.get(doubledKey) - map.get(key));
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {4, -2, 2, -4};
        //int[] arr = {10, 20, 40, 80};
        Solution solution = new Solution();
        boolean res = solution.canReorderDoubled(arr);
        System.out.println(res);
    }
}
