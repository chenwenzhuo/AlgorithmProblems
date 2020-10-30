package com.hey_there.IslandPerimeter;

public class Solution {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;//周长
        int gRows = grid.length, gCols = grid[0].length;
        for (int r = 0; r < gRows; r++) {
            for (int c = 0; c < gCols; c++) {
                if (grid[r][c] == 0) continue;//遇到”水域“格子，不进行处理
                //遇到“陆地”格子，检查其四条边
                /* 检查上边，当满足：
                 * 1.若当前格子是第一行的格子
                 * 2.当前格子正上方是“水域”格子
                 * 其中之一时，周长加一*/
                if (r == 0 || grid[r - 1][c] == 0) perimeter++;
                //检查下边
                if (r == gRows - 1 || grid[r + 1][c] == 0) perimeter++;
                //检查左边
                if (c == 0 || grid[r][c - 1] == 0) perimeter++;
                //检查右边
                if (c == gCols - 1 || grid[r][c + 1] == 0) perimeter++;
            }
        }
        return perimeter;
    }
}
