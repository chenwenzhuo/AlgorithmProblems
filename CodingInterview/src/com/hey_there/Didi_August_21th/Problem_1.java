package com.hey_there.Didi_August_21th;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 读入n
        int n = Integer.parseInt(sc.nextLine());

        int temp1, temp2;
        ArrayList<Integer> list = new ArrayList<>();
        for (int a = 1; a < 10; a++) {
            if (200 * a >= n)
                break;
            temp1 = n - a * 200;
            for (int b = 0; b < 10; b++) {
                if (a == b)
                    continue;
                if (10 * b > temp1)
                    break;
                temp2 = temp1 - 10 * b;
                for (int c = 0; c < 10; c++) {
                    if (c == a || c == b)
                        continue;
                    if (12 * c == temp2)
                        list.add(a * 100 + b * 10 + c);
                }
            }
        }
        System.out.println(list.size());
        int idx = 0;
        while (idx < list.size()) {
            int cur = list.get(idx);
            System.out.println(cur + " " + (n - cur));
            idx++;
        }
    }
}
