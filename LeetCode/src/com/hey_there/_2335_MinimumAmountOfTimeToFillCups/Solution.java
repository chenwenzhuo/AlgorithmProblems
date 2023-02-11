package com.hey_there._2335_MinimumAmountOfTimeToFillCups;

import java.util.Arrays;

public class Solution {
    public int fillCups_1(int[] amount) {
        int ans = 0;
        while (amount[0] != 0 || amount[1] != 0 || amount[2] != 0) {
            Arrays.sort(amount);
            //较小的二者之和不超过最大者，则最少要处理amount[2]次
            if (amount[0] + amount[1] <= amount[2]) {
                ans += amount[2];
                break;
            }
            //将较小的二者各减一
            amount[0]--;
            amount[1]--;
            ans++;
        }
        return ans;
    }

    public int fillCups_2(int[] amount) {
        Arrays.sort(amount);
        if (amount[2] > amount[1] + amount[0]) {
            return amount[2];
        }
        return (amount[0] + amount[1] + amount[2] + 1) / 2;
    }
}
