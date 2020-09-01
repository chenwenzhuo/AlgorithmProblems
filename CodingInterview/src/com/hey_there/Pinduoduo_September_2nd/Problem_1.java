package com.hey_there.Pinduoduo_September_2nd;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_1 {
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());//输入n
        int[][] matrix = new int[n][n];
        //将矩阵初始化为全-1
        for (int[] row : matrix) {
            Arrays.fill(row, -1);
        }
        //将两个对角线赋值为0
        for (int i = 0; i < n; i++) {
            matrix[i][i] = 0;
            matrix[i][n - 1 - i] = 0;
        }
        //当n为奇数时，将中间一行和中间一列赋值为0
        int mid = n / 2;
        if (n % 2 == 1) {
            for (int i = 0; i < n; i++) {
                matrix[mid][i] = 0;
                matrix[i][mid] = 0;
            }
        }
        //区域1
        for (int i = 0; i < mid; i++) {
            for (int j = mid + 1; j < n - 1 - i; j++) {
                if (matrix[i][j] == 0) break;
                matrix[i][j] = 1;
            }
        }
        //区域2
    }*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());//输入n
        int[][] matrix;
        if (n % 2 == 0) matrix = evenN(n);
        else matrix = oddN(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
                if (j < n - 1) System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static int[][] evenN(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                //若当前坐标(i,j)在对角线上，不进行赋值操作
                if (i == j || i + j == n - 1) continue;
                if (j < n / 2) {
                    matrix[i][j] = 2;
                    matrix[j][i] = 3;
                } else if (i < n / 2 && j >= n / 2 && j < n - 1 - i) {
                    matrix[i][j] = 1;
                    matrix[j][i] = 4;
                } else if (i < n / 2 && j > n - 1 - i) {
                    matrix[i][j] = 8;
                    matrix[j][i] = 5;
                } else if (i >= n / 2) {
                    matrix[i][j] = 7;
                    matrix[j][i] = 6;
                }
            }
        }
        return matrix;
    }

    private static int[][] oddN(int n) {
        int[][] matrix = new int[n][n];
        int mid = n / 2;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                //若当前坐标(i,j)在对角线上，不进行赋值操作
                if (i == j || i + j == n - 1) continue;
                //若当前坐标(i,j)在中间一行或中间一列，不进行赋值操作
                if (i == mid || j == mid) continue;
                if (j < mid) {
                    matrix[i][j] = 2;
                    matrix[j][i] = 3;
                } else if (i < mid && j > n / 2 && j < n - 1 - i) {
                    matrix[i][j] = 1;
                    matrix[j][i] = 4;
                } else if (i < mid && j > n - 1 - i) {
                    matrix[i][j] = 8;
                    matrix[j][i] = 5;
                } else if (i > mid) {
                    matrix[i][j] = 7;
                    matrix[j][i] = 6;
                }
            }
        }
        return matrix;
    }
}
