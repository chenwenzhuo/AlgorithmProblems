package com.hey_there.KnapsackProblem._01KnapsackProblem;

import java.util.Scanner;

public class Solution_2 {
    //使用一维dp数组，时间复杂度为O(N*M)，空间复杂度O(M)，N为物品数量，M为背包体积
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入物品数量和背包容量
        String[] arr = sc.nextLine().split(" ");
        int numObject = Integer.parseInt(arr[0]);
        int knapsackVol = Integer.parseInt(arr[1]);
        //输入每个物品的体积和价值
        int[] volumes = new int[numObject];//体积
        int[] values = new int[numObject];//价值
        for (int i = 0; i < numObject; i++) {
            String[] vvStr = sc.nextLine().split(" ");
            volumes[i] = Integer.parseInt(vvStr[0]);
            values[i] = Integer.parseInt(vvStr[1]);
        }
        sc.close();

        /* dp[i][j]表示：
         * 从前i个物品中选择，当背包容量为j时，能获得的最大价值。
         * dp[i][...]的值仅与dp[i - 1][...]有关，故空间复杂度可压缩至O(knapsackVol)*/
        int[] dp = new int[knapsackVol + 1];
        for (int i = 1; i <= numObject; i++) {
            for (int j = knapsackVol; j >= 1; j--) {
                if (j >= volumes[i - 1]) {
                    /* 当背包容量够装下第i个物品时，
                     * 可选择将此物品装进背包或不装，最大价值取二者中较大的
                     * 背包容量不够时只能选择不装，即保持dp[j]上一轮的值*/
                    dp[j] = Math.max(dp[j], dp[j - volumes[i - 1]] + values[i - 1]);
                }
            }
        }
        System.out.println(dp[knapsackVol]);
    }
}
