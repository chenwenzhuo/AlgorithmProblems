package com.hey_there._398_RandomPickIndex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Solution {
    private Random random;//用于产生随机数
    private HashMap<Integer, ArrayList<Integer>> key2Index;//哈希表存储数值和其所有出现的下标

    public Solution(int[] nums) {
        this.random = new Random();
        this.key2Index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (key2Index.containsKey(nums[i])) {
                ArrayList<Integer> indexes = key2Index.get(nums[i]);
                indexes.add(i);
                continue;
            }
            ArrayList<Integer> indexes = new ArrayList<>();
            indexes.add(i);
            key2Index.put(nums[i], indexes);
        }
    }

    public int pick(int target) {
        //获取target出现的下标
        ArrayList<Integer> indexes = key2Index.get(target);
        return indexes.get(random.nextInt(indexes.size()));
    }
}
