package com.hey_there.DynamicProgramming.MaximalRectangle;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        //行数和列数
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int columns = matrix[0].length;
        if (columns == 0) {
            return 0;
        }

        int curWidth, curHeight;
        int curArea, maxArea = 0;
        //widths[i][j]表示以点(i,j)为右下角的矩形的最大宽度
        int[][] widths = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                if (matrix[row][column] == '0') {
                    //若matrix中当前坐标的格子为'0'，widths中当前坐标的格子应为0
                    widths[row][column] = 0;
                } else {
                    /*若matrix中当前坐标的格子为'1'，
                     * widths中，当前坐标的格子若在第一列，则其应为1，
                     * 若不在第一列，则为左边列的值加1*/
                    widths[row][column] = (column == 0) ? 1 : (widths[row][column - 1] + 1);
                }

                curWidth = widths[row][column];
                curHeight = 0;
                int curRow = row;
                while (curRow >= 0 && widths[curRow][column] != 0) {
                    curWidth = Math.min(curWidth, widths[curRow][column]);
                    curHeight++;
                    curArea = curWidth * curHeight;
                    maxArea = Math.max(maxArea, curArea);
                    curRow--;
                }
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        /*char[][] matrix = {{'0', '1', '1', '0', '1'}, {'1', '1', '0', '1', '0'}, {'0', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '1'}, {'0', '0', '0', '0', '0'}};*/
        char[][] matrix = {{'1'}};
        System.out.println("rows: " + matrix.length + ",columns: " + matrix[0].length);
        Solution solution = new Solution();
        System.out.println(solution.maximalRectangle(matrix));
    }
}
