package com.hey_there.Pinduoduo_September_2nd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入N和M
        String[] NMStr = sc.nextLine().split(" ");
        int N = Integer.parseInt(NMStr[0]);
        int M = Integer.parseInt(NMStr[1]);
        //输入N行M列的数组
        int[][] matrix = new int[N][M];
        int[][] matrixCopy = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] curLine = sc.nextLine().split(" ");
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(curLine[j]);
                matrixCopy[i][j] = Integer.parseInt(curLine[j]);
            }
        }
        int count = 0;
        int ans = 0;
        ArrayList<Integer> ansList = new ArrayList<>();
        HashSet<Integer> used = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 1 && !used.contains(i * M + j)) {
                    count += 1;
                    int temp = dfs(i, j, used, N, M, matrix);
                    ansList.add(temp);
                    ans = Math.max(ans, temp);
                }
            }
        }
        if (count <= 1) {
            System.out.println(ans);
        } else {
            ans = 0;
            used.clear();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (matrixCopy[i][j] == 0) {
                        used.add(i * M + j);
                        int temp = dfs(i + 1, j, used, N, M, matrixCopy) +
                                dfs(i - 1, j, used, N, M, matrixCopy) +
                                dfs(i, j + 1, used, N, M, matrixCopy) +
                                dfs(i, j - 1, used, N, M, matrixCopy);
                        ans = Math.max(ans, temp);
                    }
                }
            }
            int ansListSum = 0;
            for (int a : ansList) ansListSum += a;
            if (ans == ansListSum) {
                System.out.println(ans);
            } else {
                System.out.println(ans + 1);
            }
        }
    }

    private static int dfs(int i, int j, HashSet<Integer> used, int N, int M, int[][] matrix) {
        if (0 <= i && i < N && 0 <= j && j < M && !used.contains(i * M + j)) {
            if (matrix[i][j] == 0) return 0;
            else {
                used.add(i * M + j);
                int res = dfs(i + 1, j, used, N, M, matrix) +
                        dfs(i - 1, j, used, N, M, matrix) +
                        dfs(i, j + 1, used, N, M, matrix) +
                        dfs(i, j - 1, used, N, M, matrix) + 1;
                return res;
            }
        } else {
            return 0;
        }
    }

    private static int dfs_1(int i, int j, int N, int M, int[][] matrix) {
        if (0 <= i && i < N && 0 <= j && j < M) {
            if (matrix[i][j] == 0) {
                return 0;
            } else {
                matrix[i][j] = 0;
                int res = dfs_1(i + 1, j, N, M, matrix) +
                        dfs_1(i - 1, j, N, M, matrix) +
                        dfs_1(i, j + 1, N, M, matrix) +
                        dfs_1(i, j - 1, N, M, matrix) + 1;
                return res;
            }
        } else {
            return 0;
        }
    }

    private static int dfs_0(int i, int j, int N, int M, int[][] matrixCopy, HashSet<Integer> used) {
        if (0 <= i && i < N && 0 <= j && j < M && !used.contains(i * M + j)) {
            if (matrixCopy[i][j] == 0) {
                return 0;
            } else {
                used.add(i * M + j);
                int res = dfs_0(i + 1, j, N, M, matrixCopy, used) +
                        dfs_0(i - 1, j, N, M, matrixCopy, used) +
                        dfs_0(i, j + 1, N, M, matrixCopy, used) +
                        dfs_0(i, j - 1, N, M, matrixCopy, used) + 1;
                return res;
            }
        } else {
            return 0;
        }
    }
}
