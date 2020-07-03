package com.hey_there.CountPrimes;

import java.util.Arrays;

public class Solution {
    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        //isPrime[i]表示i是否是质数
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        //遍历isPrime数组，计算质数个数
        int count = 0;
        for (boolean i : isPrime) {
            if (i) {
                count++;
            }
        }
        return count;
    }
}
