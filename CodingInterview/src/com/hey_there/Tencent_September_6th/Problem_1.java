package com.hey_there.Tencent_September_6th;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len1 = Integer.parseInt(sc.nextLine());
        String[] line = sc.nextLine().split(" ");
        int[] list1 = new int[len1];
        for (int i = 0; i < len1; i++) {
            list1[i] = Integer.parseInt(line[i]);
        }

        int len2 = Integer.parseInt(sc.nextLine());
        line = sc.nextLine().split(" ");
        int[] list2 = new int[len2];
        for (int i = 0; i < len2; i++) {
            list2[i] = Integer.parseInt(line[i]);
        }

        ArrayList<Integer> res = new ArrayList<>();
        int idx1 = 0, idx2 = 0;
        while (idx1 < len1 && idx2 < len2) {
            if (list1[idx1] == list2[idx2]) {
                res.add(list1[idx1]);
                idx1++;
                idx2++;
                continue;
            }

            if (list1[idx1] > list2[idx2]) {
                idx1++;
            } else {
                idx2++;
            }
        }
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i));
            if (i < res.size() - 1) System.out.print(" ");
        }
    }
}
