package com.hey_there.Beike_October_26th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Problem_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        line = sc.nextLine().split(" ");
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(line[i]);
        }

        ArrayList<Integer> vol = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            vol.add(weights[i]);
        }

        int N = (1 << n) - 1;
        ArrayList<Integer> state = new ArrayList<>();
        initState(state, vol, N, m);

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < state.size(); i++) {
            for (int j = N; j >= 0; j--) {
                if (dp[j] == Integer.MAX_VALUE) continue;
                if ((j & state.get(i)) == 0) {
                    dp[j | state.get(i)] = Math.min(dp[j | state.get(i)], dp[j] + 1);
                }
            }
        }
        System.out.println(dp[N]);
    }

    private static boolean isStateOK(int x, ArrayList<Integer> vol, int maxCarry) {
        int sum = 0;
        boolean[] visit = new boolean[maxCarry + 1];
        Arrays.fill(visit, false);

        for (int i = 0; i < vol.size(); i++) {
            if (((x >> i) & 1) > 0) {
                sum += vol.get(i);
                for (int j = maxCarry; j >= vol.get(i); j--) {
                    if (visit[j - vol.get(i)]) visit[j] = true;
                }
            }
        }
        return sum <= maxCarry;
    }

    private static void initState(ArrayList<Integer> state, ArrayList<Integer> vol, int N, int maxCarry) {
        for (int i = 1; i <= N; i++) {
            if (isStateOK(i, vol, maxCarry)) state.add(i);
        }
    }
}
