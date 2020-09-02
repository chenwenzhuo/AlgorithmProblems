package com.hey_there.KnapsackProblem.KnapsackProblemOf2DCost;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入物品数量、背包容积和背包承重
        String[] line = sc.nextLine().split(" ");
        int numObj = Integer.parseInt(line[0]);
        int volKnapsack = Integer.parseInt(line[1]);
        int weightKnapsack = Integer.parseInt(line[2]);
        //输入各个物品的体积、重量、价值
        int[] volumes = new int[numObj];
        int[] weights = new int[numObj];
        int[] values = new int[numObj];
        for (int i = 0; i < numObj; i++) {
            line = sc.nextLine().split(" ");
            volumes[i] = Integer.parseInt(line[0]);
            weights[i] = Integer.parseInt(line[1]);
            values[i] = Integer.parseInt(line[2]);
        }

        int[][] dp = new int[volKnapsack + 1][weightKnapsack + 1];
        for (int i = 1; i <= numObj; i++) {
            for (int j = volKnapsack; j >= 1; j--) {
                for (int k = weightKnapsack; k >= 1; k--) {
                    if (j >= volumes[i - 1] && k >= weights[i - 1]) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - volumes[i - 1]][k - weights[i - 1]] + values[i - 1]);
                    }
                }
            }
        }
        System.out.println(dp[volKnapsack][weightKnapsack]);
    }
}
