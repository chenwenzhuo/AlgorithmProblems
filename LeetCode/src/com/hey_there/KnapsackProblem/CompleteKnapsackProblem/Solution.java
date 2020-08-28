package com.hey_there.KnapsackProblem.CompleteKnapsackProblem;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入物品种数和背包容量
        String[] kvStr = sc.nextLine().split(" ");
        int kinds = Integer.parseInt(kvStr[0]);
        int knapsackVol = Integer.parseInt(kvStr[1]);
        //读入每个物品的体积和价值
        int[] volumes = new int[kinds];
        int[] values = new int[kinds];
        for (int i = 0; i < kinds; i++) {
            String[] vol_val_str = sc.nextLine().split(" ");
            volumes[i] = Integer.parseInt(vol_val_str[0]);
            values[i] = Integer.parseInt(vol_val_str[1]);
        }

        int[] dp = new int[knapsackVol + 1];
        for (int i = 1; i <= kinds; i++) {
            for (int j = 1; j <= knapsackVol; j++) {
                if (j >= volumes[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - volumes[i - 1]] + values[i - 1]);
                }
            }
        }
        System.out.println(dp[knapsackVol]);
    }
}
