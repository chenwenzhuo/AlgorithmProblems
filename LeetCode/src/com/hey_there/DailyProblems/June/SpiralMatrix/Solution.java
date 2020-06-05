package com.hey_there.DailyProblems.June.SpiralMatrix;

public class Solution {
    private int[] ans;
    private int[][] matrix;
    private int mRows, mColumns;

    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }

        this.matrix = matrix;
        this.mRows = matrix.length;
        this.mColumns = matrix[0].length;

        int total = mRows * mColumns;
        this.ans = new int[total];

        int[] nextCoord = {0, 0, 0};
        while (nextCoord[2] < total - 1) {
            nextCoord = goRightwards(nextCoord[0], nextCoord[1], nextCoord[2]);
            if (nextCoord[2] >= total - 1) {
                break;
            }
            nextCoord = goDownwards(nextCoord[0], nextCoord[1], nextCoord[2]);
            if (nextCoord[2] >= total - 1) {
                break;
            }
            nextCoord = goLeftwards(nextCoord[0], nextCoord[1], nextCoord[2]);
            if (nextCoord[2] >= total - 1) {
                break;
            }
            nextCoord = goUpwards(nextCoord[0], nextCoord[1], nextCoord[2]);
        }
        if (nextCoord[2] < total) {
            ans[nextCoord[2]] = matrix[nextCoord[0]][nextCoord[1]];
        }
        return ans;
    }

    private int[] goRightwards(int startRow, int startColumn, int ansIdx) {
        int endColumn = mColumns - 1 - startColumn;
        for (int c = startColumn; c < endColumn; c++) {
            ans[ansIdx] = matrix[startRow][c];
            ansIdx++;
        }
        return new int[]{startRow, endColumn, ansIdx};
    }

    private int[] goDownwards(int startRow, int startColumn, int ansIdx) {
        int endRow = mRows - 1 - startRow;
        for (int r = startRow; r < endRow; r++) {
            ans[ansIdx] = matrix[r][startColumn];
            ansIdx++;
        }
        return new int[]{endRow, startColumn, ansIdx};
    }

    private int[] goLeftwards(int startRow, int startColumn, int ansIdx) {
        int endColumn = mColumns - 1 - startColumn;
        for (int c = startColumn; c > endColumn; c--) {
            ans[ansIdx] = matrix[startRow][c];
            ansIdx++;
        }
        return new int[]{startRow, endColumn, ansIdx};
    }

    private int[] goUpwards(int startRow, int startColumn, int ansIdx) {
        int endRow = mRows - 1 - startRow;
        for (int r = startRow; r > endRow; r--) {
            ans[ansIdx] = matrix[r][startColumn];
            ansIdx++;
        }
        return new int[]{endRow + 1, startColumn + 1, ansIdx};
    }

    public static void main(String[] args) {
        //int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = {{1, 2}, {4, 5}, {7, 8}};
        Solution solution = new Solution();
        int[] ans = solution.spiralOrder(matrix);
        for (int a : ans) {
            System.out.print(a + "   ");
        }
        System.out.println();
    }
}
