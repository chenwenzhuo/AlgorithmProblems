package com.hey_there.Qunar_September_23rd;

import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[] sequence1 = sc.nextLine().split(" ");
        String[] sequence2 = sc.nextLine().split(" ");

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (sequence1[i - 1].equals(sequence2[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
            }
        }
        System.out.println(dp[n][n]);
    }
}
