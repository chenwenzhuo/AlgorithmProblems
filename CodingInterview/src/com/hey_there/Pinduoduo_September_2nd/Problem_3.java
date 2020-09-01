package com.hey_there.Pinduoduo_September_2nd;

import java.util.Scanner;

public class Problem_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入商品总数N和背包大小M
        String[] NMStr = sc.nextLine().split(" ");
        int N = Integer.parseInt(NMStr[0]);
        int M = Integer.parseInt(NMStr[1]);
        //输入每件商品的空间占用和价值
        int[] volumes = new int[N];
        int[] values = new int[N];
        for (int i = 0; i < N; i++) {
            String[] curLine = sc.nextLine().split(" ");
            volumes[i] = Integer.parseInt(curLine[0]);
            values[i] = Integer.parseInt(curLine[1]);
        }

        int[] dp = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = M; j >= 0; j--) {
                if (j >= volumes[i - 1]) {
                    dp[j] = Math.max(dp[j], dp[j - volumes[i - 1]] + values[i - 1]);
                }
            }
        }
        System.out.println(dp[M]);
    }
}
