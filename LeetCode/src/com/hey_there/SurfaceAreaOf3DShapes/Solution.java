package com.hey_there.SurfaceAreaOf3DShapes;

public class Solution {
    public int surfaceArea(int[][] grid) {
        //二维数组的行数，列数
        int rows = grid.length, columns = grid[0].length;
        int area = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                int curHeight = grid[r][c];
                if (curHeight > 0) {
                    //每一格的顶面和底面一定是露出来的
                    area += 2;
                    //检查四周
                    //上
                    if (r - 1 < 0) {
                        area += curHeight;//若当前是第一行，直接加上curHeight
                    } else if (grid[r - 1][c] < curHeight) {
                        //不是第一行则当上一行比当前行矮时加上两行的高度差
                        area += (curHeight - grid[r - 1][c]);
                    }
                    //下
                    if (r + 1 >= rows) {
                        area += curHeight;
                    } else if (grid[r + 1][c] < curHeight) {
                        area += (curHeight - grid[r + 1][c]);
                    }
                    //左
                    if (c - 1 < 0) {
                        area += curHeight;
                    } else if (grid[r][c - 1] < curHeight) {
                        area += (curHeight - grid[r][c - 1]);
                    }
                    //右
                    if (c + 1 >= columns) {
                        area += curHeight;
                    } else if (grid[r][c + 1] < curHeight) {
                        area += (curHeight - grid[r][c + 1]);
                    }
                }
            }
        }
        return area;
    }
}
