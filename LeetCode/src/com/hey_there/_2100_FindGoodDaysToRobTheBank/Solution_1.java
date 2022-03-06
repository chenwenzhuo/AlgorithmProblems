package com.hey_there._2100_FindGoodDaysToRobTheBank;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        List<Integer> res = new ArrayList<>();
        //"好日子"前后各有time天，加上自身，security的长度不能少于time*2+1
        if (security.length < time * 2 + 1) return res;
        if (time == 0) {//time为0时，任意一天都是"好日子"
            for (int i = 0; i < security.length; i++)
                res.add(i);
            return res;
        }
        //计算以下标time结尾，连续的递减天数
        int straightDec = getStraightDec(security, time);//连续递减的天数
        //计算以下标time+1开头，连续的递增天数
        int straightInc = getStraightInc(security, time);//连续递增的天数

        int potential = time;//从下标time开始遍历数组
        while (potential + time < security.length) {
            if (straightDec >= time + 1 && straightInc >= time + 1) {
                res.add(potential);
            }
            if (straightInc > time) {
                potential++;
                straightInc--;
            } else {
                potential += straightInc;
                straightInc = getStraightInc(security, potential);
                straightDec = getStraightDec(security, potential);
                continue;
            }
            if (security[potential] <= security[potential - 1])
                straightDec++;
            else
                straightDec = 1;
        }
        return res;
    }

    //计算数组中，以下标target结尾，有多少个连续减少的值
    private int getStraightDec(int[] nums, int target) {
        int res = 1;
        while (target > 0) {
            if (nums[target] <= nums[target - 1]) {
                res++;
                target--;
            } else
                break;
        }
        return res;
    }

    //计算数组中，以下标target开头，有多少个连续增加的值
    private int getStraightInc(int[] nums, int target) {
        if (target >= nums.length) return 0;
        int res = 1;
        while (target < nums.length - 1) {
            if (nums[target] <= nums[target + 1]) {
                res++;
                target++;
            } else
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] security = {77, 9, 111, 138, 139, 183, 112, 127, 38, 123, 198, 86, 163, 50, 140, 106, 99, 140, 152, 176,
                124, 181, 14, 113, 53, 186, 76, 165, 32, 26, 137, 4, 186, 193, 130, 157, 173, 52, 27, 101, 154, 129, 34,
                200, 51, 184, 127, 147, 197, 151, 190, 95, 62, 182, 77, 34, 174, 162, 7, 84, 105, 103, 128};
        int time = 2;
        Solution_1 solution = new Solution_1();
        List<Integer> goodDays = solution.goodDaysToRobBank(security, time);
        for (int n : goodDays) {
            System.out.print(n + "   ");
        }
        System.out.println();
    }
}
