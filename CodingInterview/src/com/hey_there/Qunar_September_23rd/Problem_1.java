package com.hey_there.Qunar_September_23rd;

import java.util.HashMap;
import java.util.Scanner;

public class Problem_1 {
    private static HashMap<String, Long> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long m = sc.nextLong();
        long n = sc.nextLong();
        sc.close();

        long res = comb(m, n);
        System.out.println(res);
    }

    public static long comb(long m, long n) {
        String key = m + "," + n;
        if (n == 0) return 1;
        if (n == 1) return m;

        if (n > m / 2) return comb(m, m - n);
        if (n > 1) {
            if (!map.containsKey(key)) {
                map.put(key, comb(m - 1, n - 1) + comb(m - 1, n));
            }
            return map.get(key);
        }
        return -1;
    }
}
