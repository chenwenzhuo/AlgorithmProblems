package com.hey_there.DailyProblems.MaxAreaOfIsland;

import java.util.Stack;

public class Solution {
    private Stack<Integer> dfsTrace = new Stack<>();
    private int rows, columns;

    public int maxAreaOfIsland(int[][] grid) {
        //矩阵的行数和列数
        this.rows = grid.length;
        this.columns = grid[0].length;

        int maxArea = 0;
        //对数组进行深度优先遍历
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (grid[r][c] == 0 || grid[r][c] == -1) {
                    continue;
                }

                //当发现一格值为1时，进行深度优先遍历
                int maxArea_temp = dfs(grid, r, c);
                if (maxArea_temp > maxArea) {
                    maxArea = maxArea_temp;
                }
            }
        }
        return maxArea;
    }

    private int dfs(int[][] grid, int row, int column) {
        if (row >= rows || row < 0 || column >= columns || column < 0//首先检查是否越界
                || grid[row][column] == 0 || grid[row][column] == -1) {//再检查当前位置是否为“陆地”、是否已遍历过
            return 0;
        }
        //将当前位置标记为-1
        grid[row][column] = -1;

        //向四个方向进行dfs
        int area_right = dfs(grid, row, column + 1);
        int area_left = dfs(grid, row, column - 1);
        int area_below = dfs(grid, row + 1, column);
        int area_above = dfs(grid, row - 1, column);

        return area_right + area_left + area_below + area_above + 1;
    }

    public static void main(String[] args) {
        /*int[][] grid = {
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}};*/
        int[][] grid = {
                {1, 0, 1},
                {1, 1, 1}};
        Solution solution = new Solution();
        System.out.println(solution.maxAreaOfIsland(grid));
    }
}
