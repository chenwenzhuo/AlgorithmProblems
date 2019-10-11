package com.hey_there.DynamicProgramming.MinPathSum;

public class Solution {
    public int minPathSum(int[][] grid) {
        //获取矩阵的宽、高
        int gridWidth = grid[0].length;
        int gridHeight = grid.length;

        //用一个矩阵来存储(0,0)格子到(i,j)格子的最短路径长度
        int[][] pathSums = new int[gridHeight][gridWidth];
        pathSums[0][0] = grid[0][0];//初始化第一格

        //初始化第一行第一列
        for (int i = 1; i < gridWidth; i++) {
            pathSums[0][i] = pathSums[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < gridHeight; i++) {
            pathSums[i][0] = pathSums[i - 1][0] + grid[i][0];
        }

        //只能从上方或左方到达当前格子，所以比较上方和左方格子的最短路径值，可得到到达当前格子的最短路径值
        for (int i = 1; i < gridWidth; i++) {
            for (int j = 1; j < gridHeight; j++) {
                pathSums[j][i] = Math.min(pathSums[j][i - 1], pathSums[j - 1][i]) + grid[j][i];
            }
        }
        return pathSums[gridHeight - 1][gridWidth - 1];
    }

    public static void main(String[] args) {
        //int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        int[][] grid = {{1, 2, 5}, {3, 2, 1}};

        Solution solution = new Solution();
        int min = solution.minPathSum(grid);
        System.out.println(min);
    }
}
