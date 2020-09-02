package com.hey_there.KnapsackProblem.GroupKnapsackProblem;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入物品组数和背包容量
        String[] line = sc.nextLine().split(" ");
        int groupsOfObj = Integer.parseInt(line[0]);
        int volKnapsack = Integer.parseInt(line[1]);
        //输入各组物品的体积、价值
        int[][] volumes = new int[groupsOfObj][];
        int[][] values = new int[groupsOfObj][];
        for (int i = 0; i < groupsOfObj; i++) {
            //输入此组物品的数量
            int numObjInGroup = Integer.parseInt(sc.nextLine());
            volumes[i] = new int[numObjInGroup];
            values[i] = new int[numObjInGroup];
            for (int j = 0; j < numObjInGroup; j++) {
                line = sc.nextLine().split(" ");
                volumes[i][j] = Integer.parseInt(line[0]);
                values[i][j] = Integer.parseInt(line[1]);
            }
        }

        int[] dp = new int[volKnapsack + 1];
        for (int i = 1; i <= groupsOfObj; i++) {
            for (int j = volKnapsack; j >= 1; j--) {
                for (int k = 0; k < volumes[i - 1].length; k++) {
                    if (j >= volumes[i - 1][k])
                        dp[j] = Math.max(dp[j], dp[j - volumes[i - 1][k]] + values[i - 1][k]);
                }
            }
        }
        System.out.println(dp[volKnapsack]);
    }
}
