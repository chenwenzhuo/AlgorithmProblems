package com.hey_there.InterviewProblem_4_SearchIn2DArray;

public class Solution_Recursive {
    private int[][] matrix;
    private int mRows, mCols;
    private int target;

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        this.matrix = matrix;
        this.mRows = matrix.length;
        this.mCols = matrix[0].length;
        this.target = target;
        return recursiveSearch(0, mRows - 1, 0, mCols - 1);
    }

    private boolean recursiveSearch(int row_low, int row_high, int col_low, int col_high) {
        //搜索范围产生数组越界时，直接返回false
        if (row_low >= mRows || col_high < 0) {
            return false;
        }
        //当待搜索范围仅有一个数时，直接比较其是否与目标值相等
        if (row_low == row_high && col_low == col_high) {
            return matrix[row_low][col_low] == target;
        }
        //比较target与搜索范围的右上角的数
        if (target == matrix[row_low][col_high]) {
            return true;//相等时返回true
        }
        //target大于右上角的数时，搜索范围减少一行
        if (target > matrix[row_low][col_high]) {
            return recursiveSearch(row_low + 1, row_high, col_low, col_high);
        } else {//target小于右上角的数时，搜索范围减少一列
            return recursiveSearch(row_low, row_high, col_low, col_high - 1);
        }
    }
}
