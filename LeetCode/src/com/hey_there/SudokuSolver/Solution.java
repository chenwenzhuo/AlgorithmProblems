package com.hey_there.SudokuSolver;

public class Solution {
    private char[][] board;
    private boolean sudokuSolved;

    public void solveSudoku(char[][] board) {
        this.board = board;
        this.sudokuSolved = false;
        backtrack(0, 0);
    }

    private void backtrack(int row, int column) {
        if (row >= 9 || column >= 9) {
            //超过board的范围，则board中所有位置一定都完成了填充
            sudokuSolved = true;//将标志位设为已完成
            return;
        }
        //计算下一个位置的坐标
        int nextRow, nextColumn;
        if (column == 8) {
            nextRow = row + 1;
            nextColumn = 0;
        } else {
            nextRow = row;
            nextColumn = column + 1;
        }

        //若当前位置已填充，检查下一个位置
        if (board[row][column] != '.') {
            backtrack(nextRow, nextColumn);
        } else {
            //若当前位置未填充，则尝试填充
            for (int i = 1; i <= 9; i++) {
                //检查当前i值能否填入当前位置
                if (!isValid(row, column, i)) {
                    continue;//不能填入则尝试下一个值
                }
                //能填入则填入
                board[row][column] = (char) ('0' + i);
                if (row == 8 && column == 8) {
                    sudokuSolved = true;//若当前填的是最后一个位置，改变标志位的值
                }
                backtrack(nextRow, nextColumn);//检查下一个位置
                //仅当数独未解决时进行回溯
                if (!sudokuSolved) {
                    board[row][column] = '.';
                }
            }
        }
    }

    private boolean isValid(int r, int c, int n) {
        //检查同一行
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == '0' + n) {
                return false;
            }
        }
        //检查同一列
        for (int i = 0; i < 9; i++) {
            if (board[i][c] == '0' + n) {
                return false;
            }
        }
        //检查3*3的子方块
        //将9*9的大方块board看作由9个
        // 3*3的子方块组成，
        //计算子方块的坐标
        int subCubeRow = r / 3, subCubeColumn = c / 3;
        //计算子方块起始格的坐标
        int startGridRow = subCubeRow * 3, startGridColumn = subCubeColumn * 3;
        //检查子方块
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startGridRow + i][startGridColumn + j] == '0' + n) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        Solution solution = new Solution();
        solution.solveSudoku(board);
        for (char[] row : board) {
            for (char r : row) {
                System.out.print(r + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
