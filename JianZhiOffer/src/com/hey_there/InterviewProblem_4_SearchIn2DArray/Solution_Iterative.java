package com.hey_there.InterviewProblem_4_SearchIn2DArray;

public class Solution_Iterative {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int mRows = matrix.length;
        int mCols = matrix[0].length;
        int row_low = 0;
        int col_high = mCols - 1;
        while (row_low < mRows && col_high >= 0) {
            if (target > matrix[row_low][col_high]) {
                row_low++;
            } else if (target < matrix[row_low][col_high]) {
                col_high--;
            } else {
                return true;
            }
        }
        return false;
    }
}
