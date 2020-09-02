package com.hey_there.KnapsackProblem.MixedKnapsackProblem;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入物品种数和背包容量
        String[] line = sc.nextLine().split(" ");
        int kindsOfObj = Integer.parseInt(line[0]);
        int volKnapsack = Integer.parseInt(line[1]);
        //输入各个物品的体积、价值和数量
        int[] volumes = new int[kindsOfObj];
        int[] values = new int[kindsOfObj];
        int[] nums = new int[kindsOfObj];
        for (int i = 0; i < kindsOfObj; i++) {
            line = sc.nextLine().split(" ");
            volumes[i] = Integer.parseInt(line[0]);
            values[i] = Integer.parseInt(line[1]);
            nums[i] = Integer.parseInt(line[2]);
        }

        int[] dp = new int[volKnapsack + 1];
        for (int i = 1; i <= kindsOfObj; i++) {
            if (nums[i - 1] < 0) {//01背包
                for (int j = volKnapsack; j >= 1; j--) {
                    if (j >= volumes[i - 1])
                        dp[j] = Math.max(dp[j], dp[j - volumes[i - 1]] + values[i - 1]);
                }
            } else if (nums[i - 1] == 0) {//完全背包
                for (int j = 1; j <= volKnapsack; j++) {
                    if (j >= volumes[i - 1])
                        dp[j] = Math.max(dp[j], dp[j - volumes[i - 1]] + values[i - 1]);
                }
            } else if (nums[i - 1] > 0) {//多重背包
                for (int j = volKnapsack; j >= 1; j--) {
                    int prevVol = j;
                    int extraVal = 0;
                    for (int k = 1; k <= nums[i - 1]; k++) {
                        prevVol -= volumes[i - 1];
                        extraVal += values[i - 1];
                        if (prevVol>=0)
                            dp[j] = Math.max(dp[j], dp[prevVol] + extraVal);
                        else break;
                    }
                }
            }
        }
        System.out.println(dp[volKnapsack]);
    }
}
