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

    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        for (int i = T.length - 2; i >= 0; i--) {
            if (T[i] < T[i + 1]) {
                result[i] = 1;
            } else if (T[i] == T[i + 1]) {
                if (result[i + 1] != 0) {
                    result[i] = result[i + 1] + 1;
                }
            } else {
                for (int index = i + 1; ; ) {
                    index += result[index];
                    if (T[i] < T[index]) {
                        result[i] = index - i;
                        break;
                    }
                    if (result[index] == 0) {
                        break;
                    }
                }

            }
        }
        return result;
    }
}
