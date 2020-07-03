package com.hey_there.SetMatrixZeroes;

public class Solution {
    /* 将第一行和第一列作为标志位，
     * matrix[0][i]和matrix[j][0]为0表示此列（行）需要被置0。
     * 先将除首行首列外的元素进行置0，再单独对首行首列置0。*/
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        //首列的各个元素表示其所在行的元素是否要置0，
        //故需要一个单独的标志位记录首列自己是否需要被置0
        boolean firstCol = false;
        for (int i = 0; i < rows; i++) {
            //任意一行的行首为0，则第一列需要被置为全0
            if (matrix[i][0] == 0) {
                firstCol = true;
            }
            for (int j = 1; j < columns; j++) {
                //对于一个为0的位置，将其同一行的第一个和同一列的第一个置为0
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        //检查首行首列元素值，将坐标[1,1]及其右下方的元素置0
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //单独处理第一行和第一列
        if (matrix[0][0] == 0) {//检查首行是否需要置0
            for (int i = 0; i < columns; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstCol) {//检查首列是否需要置0
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
