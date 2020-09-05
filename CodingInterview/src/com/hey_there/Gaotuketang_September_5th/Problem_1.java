package com.hey_there.Gaotuketang_September_5th;

import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] NMStr = sc.nextLine().split(" ");
        int days = Integer.parseInt(NMStr[0]);
        int numCandy = Integer.parseInt(NMStr[1]);

        int temp, now, mid;
        int left = 1, right = numCandy;
        boolean flag;
        while (left != right) {
            flag = true;
            mid = (left + right + 1) / 2;
            temp = numCandy;
            now = mid;
            for (int i = 0; i < days; i++) {
                if (temp < now) {
                    flag = false;
                    break;
                }
                temp -= now;
                now = (now + 1) / 2;
            }
            if (flag) left = mid;
            else right = mid - 1;
        }
        System.out.println(left);
    }
}
