package com.hey_there.Array.SpiralMatrix_2;

public class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int filledGrids = 0;//matrix数组中已经填入数字的格子数量
        int x = 0, y = 0;//填充的格子的坐标
        int direcIdx = 0;//控制填充移动的方向
        while (filledGrids < n * n) {
            //填充当前格子
            matrix[x][y] = filledGrids + 1;
            filledGrids++;
            //检查此方向上下一个格子是否越界，不越界时检查下一个格子是否已经被填充
            if (x + directions[direcIdx][0] >= n || x + directions[direcIdx][0] < 0 ||
                    y + directions[direcIdx][1] >= n || y + directions[direcIdx][1] < 0 ||
                    matrix[x + directions[direcIdx][0]][y + directions[direcIdx][1]] != 0) {
                //下一个格子越界或已填充时，改变填充方向
                direcIdx = (direcIdx + 1) % 4;
            }
            //更新下一个要填充的格子的坐标
            x += directions[direcIdx][0];
            y += directions[direcIdx][1];
        }
        return matrix;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = solution.generateMatrix(5);
        for (int[] row : matrix) {
            for (int n : row) {
                System.out.print(n + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
