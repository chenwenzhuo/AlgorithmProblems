package com.hey_there.Yuanfudao_August_22th;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] N_M = sc.nextLine().split(" ");
        int N = Integer.parseInt(N_M[0]);
        int M = Integer.parseInt(N_M[1]);
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] numsStr = sc.nextLine().split(" ");
            List<Integer> curLine = new ArrayList<>();
            for (String str : numsStr) {
                curLine.add(Integer.parseInt(str));
            }
            matrix.add(curLine);
        }
        int max = getMaxMatrix(matrix);
        System.out.println(max);
    }

    private static int getMaxMatrix(List<List<Integer>> matrix) {
        List<Integer> ans = new ArrayList<>();
        int N = matrix.size();
        int M = matrix.get(0).size();
        List<Integer> b = new ArrayList<>();
        int sum;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int t = 0; t < M; t++) b.add(0);
            for (int j = i; j < N; j++) {
                sum = 0;
                for (int k = 0; k < M; k++) {
                    int temp = b.get(k);
                    b.set(k, temp + matrix.get(j).get(k));
                    if (sum > 0) {
                        sum += b.get(k);
                    } else {
                        sum = b.get(k);
                    }
                    if (sum > maxSum) {
                        maxSum = sum;
                    }
                }

            }
        }
        return maxSum;
    }
}
