package com.hey_there._883_ProjectionAreaOf3DShapes;

public class Solution {
    public int projectionArea(int[][] grid) {
        int rows = grid.length;
        //在xy、yz、zx平面上的投影面积
        int xy = 0, yz = 0, zx = 0;
        //遍历数组计算每行、每列最大值和xy平面上的投影面积
        for (int i = 0; i < rows; i++) {
            //当前行、列的最大值
            int rowMax = 0, colMax = 0;
            for (int j = 0; j < rows; j++) {
                rowMax = Math.max(rowMax, grid[i][j]);
                colMax = Math.max(colMax, grid[j][i]);
                if (grid[i][j] != 0) xy++;
            }
            yz += rowMax;
            zx += colMax;
        }
        return xy + yz + zx;
    }
}
