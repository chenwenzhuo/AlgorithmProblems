package com.hey_there.Beike_October_26th;

import java.util.HashSet;
import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCases = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < testCases; i++) {
            String[] line = sc.nextLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);

            line = sc.nextLine().split(" ");
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = Integer.parseInt(line[j]);
            }

            if (m == 0) {
                boolean can = false;
                for (int j = 0; j < n; j++) {
                    if (nums[j] == 0) {
                        System.out.println("yes");
                        can = true;
                        break;
                    }
                }
                if (!can) System.out.println("no");
            } else {
                HashSet<Integer> set = new HashSet<>();
                boolean can = false;
                for (int j = 0; j < n; j++) {
                    if (nums[j] == 0) continue;
                    if (m % nums[j] == 0 && set.contains(m / nums[j])) {
                        System.out.println("yes");
                        can = true;
                        break;
                    } else {
                        set.add(nums[j]);
                    }
                }
                if (!can) System.out.println("no");
            }
        }
        sc.close();
    }
}
