package com.hey_there.KnapsackProblem.MultipleKnapsackProblem;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读入物品种数和背包容量
        String[] line = sc.nextLine().split(" ");
        int kindsOfObject = Integer.parseInt(line[0]);
        int volKnapsack = Integer.parseInt(line[1]);
        //读入各个物品的体积、价值、数量
        int[] volumes = new int[kindsOfObject];
        int[] values = new int[kindsOfObject];
        int[] nums = new int[kindsOfObject];
        for (int i = 0; i < kindsOfObject; i++) {
            line = sc.nextLine().split(" ");
            volumes[i] = Integer.parseInt(line[0]);
            values[i] = Integer.parseInt(line[1]);
            nums[i] = Integer.parseInt(line[2]);
        }

        int[] dp = new int[volKnapsack + 1];
        for (int i = 1; i <= kindsOfObject; i++) {
            for (int j = volKnapsack; j >= 1; j--) {
                for (int k = 1; k <= nums[i - 1]; k++) {
                    int smallerVol = j - volumes[i - 1] * k;
                    if (smallerVol >= 0) dp[j] = Math.max(dp[j], dp[smallerVol] + values[i - 1] * k);
                    else break;
                }
            }
        }
        System.out.println(dp[volKnapsack]);
    }
}
