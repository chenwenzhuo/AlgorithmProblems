package com.hey_there.DivingBoard;

public class Solution {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];//没有板子时返回空数组
        }
        //shorter和longer相等时，只能组合出一种长度
        if (shorter == longer) {
            return new int[]{shorter * k};
        }
        //shorter和longer不等时，能组合出k+1种长度
        int[] res = new int[k + 1];
        res[0] = shorter * k;
        for (int i = 1; i <= k; i++) {
            res[i] = res[i - 1] - shorter + longer;
        }
        return res;
    }
}
