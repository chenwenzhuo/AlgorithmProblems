package com.hey_there._2006_CountNumberOfPairsWithAbsoluteDifferenceK;

import java.util.HashMap;

public class Solutions {
    //暴力搜索方法，两层嵌套循环
    public int countKDifference_nestedLoops(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (Math.abs(nums[i] - nums[j]) == k) count++;
        return count;
    }

    //哈希表+一次遍历
    public int countKDifference_hashMap(int[] nums, int k) {
        int count = 0;
        //哈希表的key表示nums数组中的数，value表示在当前遍历过的部分中，key出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            //与n绝对值相差k的数为n+k和n-k，检查n+k和n-k在map中的出现次数，与count相加
            count += (map.getOrDefault(n + k, 0) + map.getOrDefault(n - k, 0));
            map.put(n, map.getOrDefault(n, 0) + 1);//将当前数n存入map，出现次数增加1
        }
        return count;
    }
}
