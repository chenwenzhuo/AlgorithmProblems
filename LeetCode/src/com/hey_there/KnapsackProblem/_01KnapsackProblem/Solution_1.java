package com.hey_there.KnapsackProblem._01KnapsackProblem;

import java.util.Scanner;

public class Solution_1 {
    //使用二维dp数组，时空复杂度均为O(N*M)，N为物品数量，M为背包体积
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入物品数量和背包容量
        String[] arr = sc.nextLine().split(" ");
        int numObject = Integer.parseInt(arr[0]);
        int knapsackVol = Integer.parseInt(arr[1]);
        //输入每个物品的体积和价值
        int[][] vol_val = new int[numObject][2];
        for (int i = 0; i < numObject; i++) {
            String[] vvStr = sc.nextLine().split(" ");
            vol_val[i][0] = Integer.parseInt(vvStr[0]);//体积
            vol_val[i][1] = Integer.parseInt(vvStr[1]);//价值
        }
        sc.close();

        /* dp[i][j]表示：
         * 从前i个物品中选择，当背包容量为j时，能获得的最大价值*/
        int[][] dp = new int[numObject + 1][knapsackVol + 1];
        for (int i = 1; i <= numObject; i++) {
            for (int j = 1; j <= knapsackVol; j++) {
                if (j >= vol_val[i - 1][0]) {
                    /* 当背包容量够装下第i个物品时，
                     * 可选择将此物品装进背包或不装，最大价值取二者中较大的*/
                    dp[i][j] = Math.max(dp[i - 1][j - vol_val[i - 1][0]] + vol_val[i - 1][1], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];//背包容量不够时只能选择不装
                }
            }
        }
        System.out.println(dp[numObject][knapsackVol]);
    }
}
