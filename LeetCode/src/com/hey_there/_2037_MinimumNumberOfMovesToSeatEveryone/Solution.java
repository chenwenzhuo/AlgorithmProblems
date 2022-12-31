package com.hey_there._2037_MinimumNumberOfMovesToSeatEveryone;

import java.util.Arrays;

public class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        //对两个数组排序
        Arrays.sort(seats);
        Arrays.sort(students);
        int total = 0;
        int n = seats.length;
        for (int i = 0; i < n; i++)
            total += Math.abs(seats[i] - students[i]);
        return total;
    }
}
