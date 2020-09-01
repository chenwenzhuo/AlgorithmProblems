package com.hey_there.Pinduoduo_September_2nd;

import java.util.HashSet;
import java.util.Scanner;

public class Problem_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] NMStr = sc.nextLine().split(" ");
        int N = Integer.parseInt(NMStr[0]);
        int M = Integer.parseInt(NMStr[1]);
        HashSet<Integer> setM = new HashSet<>();
        for (int i = 0; i < M; i++) {
            setM.add(Integer.parseInt(sc.nextLine()));
        }

        HashSet<Integer> setN = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            setN.add(i);
        }

        int res = 0;
        for (int n : setN) {
            for (int m : setM) {
                if (n % m == 0) res++;
            }
        }
        System.out.println(res);
    }
}
