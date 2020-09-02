package com.hey_there.KnapsackProblem.KnapsackProblemWithDependency;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入物品个数和背包容量
        String[] line = sc.nextLine().split(" ");
        int numObj = Integer.parseInt(line[0]);
        int volKnapsack = Integer.parseInt(line[1]);
        //输入各个物品体积、价值、父节点编号
        int[] volumes = new int[numObj];
        int[] values = new int[numObj];
        int[] parents = new int[numObj];
        for (int i = 0; i < numObj; i++) {
            line = sc.nextLine().split(" ");
            volumes[i] = Integer.parseInt(line[0]);
            values[i] = Integer.parseInt(line[1]);
            parents[i] = Integer.parseInt(line[2]);
        }

        int[] dp = new int[volKnapsack + 1];
        for (int i = 1; i <= numObj; i++) {
            for (int j = volKnapsack; j >= 1; j--) {
                if (j >= volumes[i - 1]) {
                    int smallerVol = j;
                    int totalVal = 0;
                    int idx = i;
                    while (idx != -1) {
                        smallerVol -= volumes[idx - 1];
                        totalVal += values[idx - 1];
                        idx = parents[idx - 1] == -1 ? -1 : parents[idx - 1];
                    }
                    if (smallerVol >= 0)
                        dp[j] = Math.max(dp[j], dp[smallerVol] + totalVal);
                }
            }
        }
        System.out.println(dp[volKnapsack]);
    }
}
