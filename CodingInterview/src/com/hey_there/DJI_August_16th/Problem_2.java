package com.hey_there.DJI_August_16th;

import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String N_X = input.nextLine();//游戏数目N和天数X的字符串形式
        String[] N_XArr = N_X.split(" ");
        //将N和X转为整型
        int N = Integer.parseInt(N_XArr[0]);
        int X = Integer.parseInt(N_XArr[1]);
        //价值数组和重量数组
        int[] values = new int[N];
        int[] weights = new int[N];
        //读入values和weights数组
        for (int i = 0; i < N; i++) {
            String[] vwPair = input.nextLine().split(" ");
            values[i] = Integer.parseInt(vwPair[0]);
            weights[i] = Integer.parseInt(vwPair[1]);
        }

        //dp[i][j]表示容量为j时前i个物品的最大价值
        int[] dp = new int[X + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = X; j >= 0; j--) {
                if (j >= weights[i]) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        System.out.println(dp[X]);
    }
}
