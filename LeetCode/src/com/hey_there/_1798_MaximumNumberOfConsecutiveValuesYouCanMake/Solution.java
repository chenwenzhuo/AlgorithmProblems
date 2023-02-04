package com.hey_there._1798_MaximumNumberOfConsecutiveValuesYouCanMake;

import java.util.Arrays;

public class Solution {
    public int getMaximumConsecutive(int[] coins) {
        Arrays.sort(coins);
        int res = 1;//已有的连续值的数量，初始为0
        for (int coin : coins) {
            if (coin > res)
                break;
            res += coin;
        }
        return res;
    }
}
