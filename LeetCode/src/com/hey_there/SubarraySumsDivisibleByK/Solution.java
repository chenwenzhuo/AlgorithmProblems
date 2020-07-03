package com.hey_there.SubarraySumsDivisibleByK;

public class Solution {
    public int subarraysDivByK(int[] A, int K) {
        int[] mem = new int[K];
        mem[0] = 1;
        int ans = 0, preSum = 0;
        for (int n : A) {
            preSum += n;
            //java中 % 操作符表示取余，需要将其修正为取模
            int mod = (preSum % K + K) % K;
            int same = mem[mod];
            ans += same;
            mem[mod] = same + 1;
        }
        return ans;
    }
}
