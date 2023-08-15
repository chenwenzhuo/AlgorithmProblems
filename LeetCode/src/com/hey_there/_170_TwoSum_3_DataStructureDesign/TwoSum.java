package com.hey_there._170_TwoSum_3_DataStructureDesign;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    //存入数字及其出现次数
    private HashMap<Integer, Integer> num2Cnt;

    public TwoSum() {
        this.num2Cnt = new HashMap<>();
    }

    public void add(int number) {
        num2Cnt.put(number, num2Cnt.getOrDefault(number, 0) + 1);
    }

    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : num2Cnt.entrySet()) {
            int num_1 = entry.getKey();
            int num_2 = value - num_1;
            if (value == num_1 * 2 && entry.getValue() >= 2)
                return true;
            if (value != num_1 * 2 && num2Cnt.containsKey(num_2))
                return true;
        }
        return false;
    }
}
