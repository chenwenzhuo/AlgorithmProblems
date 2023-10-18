package com.hey_there._1726_TupleWithSameProduct;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int tupleSameProduct(int[] nums) {
        int len = nums.length;
        //统计nums中所有不同乘积的出现次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int prod = nums[i] * nums[j];//乘积
                map.put(prod, map.getOrDefault(prod, 0) + 1);
            }
        }
        /* 对于满足a×b=c×d的元组(a,b,c,d)，通过不同顺序的排列，
         * 共可有8中不同情况，满足乘积相等。
         * 对于每个出现次数大于1次的乘积，从中任取两个的组合数，乘以8*/
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            //乘积的出现次数
            int occurrence = entry.getValue();
            if (occurrence > 1) {
                int curCnt = occurrence * (occurrence - 1) * 4;
                res += curCnt;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 6, 8, 12};
        int res = new Solution().tupleSameProduct(nums);
        System.out.println(res);
    }
}
