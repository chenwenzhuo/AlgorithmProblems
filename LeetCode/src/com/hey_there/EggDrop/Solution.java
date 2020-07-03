package com.hey_there.EggDrop;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    private int[][] eggAndFloor;

    public int superEggDrop_recursive(int K, int N) {
        eggAndFloor = new int[K + 1][N + 1];
        return recursiveDP(K, N);
    }

    private int recursiveDP(int K, int N) {
        if (K == 1) {
            return N;
        }
        if (N == 0) {
            return 0;
        }
        if (K >= 0 && N >= 0 && eggAndFloor[K][N] > 0) {
            return eggAndFloor[K][N];
        }
        int ans = N;
        for (int i = 1; i <= N + 1; i++) {
            ans = Math.min(ans,
                    Math.max(recursiveDP(K - 1, i - 1), recursiveDP(K, N - i)) + 1);
        }
        if (K >= 0 && N >= 0) {
            eggAndFloor[K][N] = ans;
        }
        return ans;
    }

    private Map<Integer, Integer> memo;

    public int superEggDrop(int K, int N) {
        memo = new HashMap<>();
        return dp(K, N);
    }

    private int dp(int K, int N) {
        if (!memo.containsKey(N * 100 + K)) {
            int ans;
            if (N == 0)
                ans = 0;
            else if (K == 1)
                ans = N;
            else {
                int lo = 1, hi = N;
                while (lo + 1 < hi) {
                    int x = (lo + hi) / 2;
                    int t1 = dp(K - 1, x - 1);
                    int t2 = dp(K, N - x);

                    if (t1 < t2)
                        lo = x;
                    else if (t1 > t2)
                        hi = x;
                    else
                        lo = hi = x;
                }

                ans = 1 + Math.min(Math.max(dp(K - 1, lo - 1), dp(K, N - lo)),
                        Math.max(dp(K - 1, hi - 1), dp(K, N - hi)));
            }

            memo.put(N * 100 + K, ans);
        }

        return memo.get(N * 100 + K);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.superEggDrop(2, 6));
    }
}
