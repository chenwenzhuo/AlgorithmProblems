package com.hey_there.Alibaba_August_26th;

import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        int T = Integer.parseInt(reader.nextLine());
        int processed = 0;
        while (processed < T) {
            int N = reader.nextInt();
            int V = reader.nextInt();
            int[] v = new int[N + 1];
            int[] w = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                v[i] = reader.nextInt();
                w[i] = reader.nextInt();
            }

            int[] dp = new int[V + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = V; j >= v[i]; j--) {
                    dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i] + 1);
                }
            }
            int maxSum = 0, index = -1;
            for (int i = 0; i < v.length; i++) {
                if (dp[i] >= N) {
                    maxSum = dp[i];
                    index = i;
                    break;
                } else {
                    if (dp[i] > maxSum) {
                        maxSum = dp[i];
                        index = i;
                    }
                }
            }
            System.out.println(maxSum + " " + index);
            processed++;
        }
    }
}
