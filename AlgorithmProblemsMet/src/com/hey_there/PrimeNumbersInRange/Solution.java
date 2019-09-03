package com.hey_there.PrimeNumbersInRange;

public class Solution {
    public int primeNumbersInRange(int low, int high) {
        int[] nums = new int[high - 2];
        for (int i = 0; i < high - 2; i++) {
            nums[i] = i + 2;
        }

        for (int i = 0; i < high - 2; i++) {
            int currentNum_i = nums[i];//当前i位置上的数
            if (currentNum_i == -1) {
                continue;
            }

            for (int j = 0; j < i; j++) {
                int currentNum_j = nums[j];
                if (currentNum_j == -1) {
                    continue;
                }

                if (currentNum_i % currentNum_j == 0) {
                    nums[i] = -1;
                    break;
                }
            }
        }

        //求个位数字的和，与十位数字的和
        int oneDigitSum = 0, tenDigitSum = 0;
        for (int i = high - 2 - 1; i >= low - 2; i--) {
            if (nums[i] != -1) {
                oneDigitSum += nums[i] % 10;
                tenDigitSum += (nums[i] / 10) % 10;
            }
        }

        return Math.min(oneDigitSum, tenDigitSum);
    }
}
