package com.hey_there.Traverse2DArrayDiagonally;

public class Solution {
    public static void main(String[] args) {
        //初始化一个正方形二维数组并输出
        int rows = 5, columns = 5;
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = i * columns + j + 1;
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println("\n\n");
        //沿对角线访问
        for (int diff = 0; diff < columns; diff++) {
            for (int r = 0; r < rows && r + diff < columns; r++) {
                System.out.print(matrix[r][r + diff] + "   ");
            }
        }
        System.out.println();
    }
}
