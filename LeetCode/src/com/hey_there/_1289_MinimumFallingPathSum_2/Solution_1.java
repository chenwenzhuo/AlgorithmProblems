package com.hey_there._1289_MinimumFallingPathSum_2;

//暴力递归回溯，复杂度O(n^n)，超时
public class Solution_1 {
    private int minSum = Integer.MAX_VALUE;
    private int gridRows, gridCols;

    public int minFallingPathSum(int[][] grid) {
        this.gridRows = grid.length;
        this.gridCols = grid[0].length;
        backtrack(grid, 0, -1, 0);
        return minSum;
    }

    private void backtrack(int[][] grid, int curRow, int prevCol, int sum) {
        //当前行下标等于grid长度，说明所有行的数字都已加上，更新minSum
        if (curRow == gridRows) {
            minSum = Math.min(minSum, sum);
            return;
        }
        //对于当前行，检查所有可能得数字
        for (int j = 0; j < gridCols; j++) {
            //跳过前一行所选数字同列的数字
            if (j == prevCol) continue;
            sum += grid[curRow][j];
            backtrack(grid, curRow + 1, j, sum);
            sum -= grid[curRow][j];
        }
    }
}
