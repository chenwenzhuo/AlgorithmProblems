package com.hey_there.DailyProblems.June.DailyTemperatures;

import java.util.ArrayDeque;

public class Solution {
    public int[] dailyTemperatures_1(int[] T) {
        int lenT = T.length;
        int[] ans = new int[lenT];
        for (int i = 0; i < lenT; i++) {
            int j = i + 1;
            while (j < lenT && T[j] <= T[i]) {
                j++;
            }
            if (j < lenT) {
                ans[i] = j - i;
            }
        }
        return ans;
    }

    public int[] dailyTemperatures_2(int[] T) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int lenT = T.length;
        int[] ans = new int[lenT];
        for (int i = 0; i < lenT; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int stackTop = stack.pop();
                ans[stackTop] = i - stackTop;
            }
            stack.push(i);
        }
        return ans;
    }
}
