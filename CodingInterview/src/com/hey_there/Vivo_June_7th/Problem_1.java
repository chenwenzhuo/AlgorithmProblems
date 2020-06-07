package com.hey_there.Vivo_June_7th;

import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] originalState = new int[len];
        int idx = 0;
        while (idx < len) {
            originalState[idx] = sc.nextInt();
            idx++;
        }

        int ans = 0;
        idx = 0;
        while (idx < len) {
            if (originalState[idx] == 1) {
                idx++;
                continue;
            }
            if ((idx - 1 >= 0 && originalState[idx - 1] == 1) ||
                    (idx + 1 < len && originalState[idx + 1] == 1)) {
                idx++;
                continue;
            }
            originalState[idx] = 1;
            ans++;
            idx++;
        }
        System.out.println(ans);
    }
}
