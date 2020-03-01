package com.hey_there.Array.RotateImage;

public class Solution {
    //1.将矩阵复制到一个新矩阵，然后遍历新矩阵将旋转后的矩阵存储到原矩阵
    //时间复杂度O(n^2)，空间复杂度O(n^2)
    public void rotate_my(int[][] matrix) {
        //获取矩阵的尺寸(由于是方阵，只需获取一个值)
        int scale = matrix.length;
        //初始化一个同大小的新矩阵
        int[][] matrixCopy = new int[scale][scale];
        //复制原矩阵到新矩阵
        for (int i = 0; i < scale; i++) {
            System.arraycopy(matrix[i], 0, matrixCopy[i], 0, scale);
        }

        int copyCoordinateX = 0, copyCoordinateY = scale - 1;
        for (int i = 0; i < scale; i++) {
            for (int j = 0; j < scale; j++) {
                matrix[copyCoordinateX][copyCoordinateY] = matrixCopy[i][j];
                copyCoordinateX++;
            }
            copyCoordinateY--;
            copyCoordinateX = 0;
        }
    }

    //2.先将矩阵转置，再将每一行反转
    //时间复杂度O(n^2)，空间复杂度O(1)
    public void rotate_transposeAndReverseRow(int[][] matrix) {
        int scale = matrix.length;
        //转置矩阵
        for (int i = 0; i < scale; i++) {
            for (int j = i; j < scale; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //逐行反转
        for (int[] row : matrix) {
            int index = 0;
            while (index < scale - 1 - index) {
                int temp = row[index];
                row[index] = row[scale - 1 - index];
                row[scale - 1 - index] = temp;

                index++;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {2, 3, 4, 5, 6},
                {3, 4, 5, 6, 7},
                {4, 5, 6, 7, 8},
                {5, 6, 7, 8, 9}};
        Solution solution = new Solution();
        solution.rotate_my(matrix);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
