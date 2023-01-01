package com.hey_there._1094_CarPooling;

public class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        //最远的距离
        int furthest = 0;
        for (int[] trip : trips)
            furthest = Math.max(furthest, trip[2]);
        int[] diff = new int[furthest + 1];//差分数组
        for (int[] trip : trips) {
            diff[trip[1]] += trip[0];
            diff[trip[2]] -= trip[0];
        }
        int passengers = 0;
        for (int n : diff) {
            passengers += n;
            if (passengers > capacity)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] trip = {{2, 1, 5}, {3, 5, 7}};
        int capacity = 3;
        Solution solution = new Solution();
        boolean res = solution.carPooling(trip, capacity);
        System.out.println(res);
    }
}
