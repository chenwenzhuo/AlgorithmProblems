package com.hey_there.GameOfLife;

public class Solution {
    //将board数组复制一份，更新原数组
    public void gameOfLife_copyingBoard(int[][] board) {
        int boardRows = board.length, boardColumns = board[0].length;
        //复制原数组
        int[][] copyOfBoard = new int[boardRows][boardColumns];
        for (int i = 0; i < boardRows; i++) {
            System.arraycopy(board[i], 0, copyOfBoard[i], 0, boardColumns);
        }

        for (int r = 0; r < boardRows; r++) {
            for (int c = 0; c < boardColumns; c++) {
                int alive = aliveCellsAround(copyOfBoard, r, c);

                if (copyOfBoard[r][c] == 1) {//若当前位置是活细胞
                    if (alive < 2 || alive > 3) {
                        board[r][c] = 0;
                    }
                } else {//若当前位置是死细胞
                    if (alive == 3) {
                        board[r][c] = 1;
                    }
                }
            }
        }
    }

    private int aliveCellsAround(int[][] board, int row, int column) {
        int boardRows = board.length, boardColumns = board[0].length;
        int[] r = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] c = {-1, 0, 1, 1, 1, 0, -1, -1};
        int totalAlive = 0;
        int curRow, curColumn;
        for (int i = 0; i < 8; i++) {
            curRow = row + r[i];
            curColumn = column + c[i];
            //首先检查坐标curRow和curColumn是否有效，若有效再检查当前位置的值
            if (curRow >= 0 && curRow < boardRows//横坐标
                    && curColumn >= 0 && curColumn < boardColumns//纵坐标
                    && board[curRow][curColumn] == 1) {
                totalAlive++;
            }
        }
        return totalAlive;
    }

    //通过增加额外的状态，在board数组原地进行修改
    public void gameOfLife_withExtraStatus(int[][] board) {
        int boardRows = board.length, boardColumns = board[0].length;
        //遍历board，更改状态
        for (int r = 0; r < boardRows; r++) {
            for (int c = 0; c < boardColumns; c++) {
                //计算周围8个位置活细胞的数量
                int alive = aliveCellsAround_extraStatus(board, r, c);
                //根据活细胞的数量更改状态
                if (board[r][c] == 1) {//若当前位置是活细胞
                    if (alive < 2 || alive > 3) {
                        board[r][c] = -1;
                    }
                } else if (board[r][c] == 0) {//若当前位置是死细胞
                    if (alive == 3) {
                        board[r][c] = 2;
                    }
                }
            }
        }
        //再次遍历，去掉多余状态
        for (int r = 0; r < boardRows; r++) {
            for (int c = 0; c < boardColumns; c++) {
                if (board[r][c] == -1) {
                    board[r][c] = 0;
                } else if (board[r][c] == 2) {
                    board[r][c] = 1;
                }
            }
        }
    }

    private int aliveCellsAround_extraStatus(int[][] board, int row, int column) {
        int boardRows = board.length, boardColumns = board[0].length;
        int[] r = {-1, -1, -1, 0, 1, 1, 1, 0};
        int[] c = {-1, 0, 1, 1, 1, 0, -1, -1};
        int totalAlive = 0;
        int curRow, curColumn;
        for (int i = 0; i < 8; i++) {
            curRow = row + r[i];
            curColumn = column + c[i];
            //首先检查坐标curRow和curColumn是否有效
            if (curRow >= 0 && curRow < boardRows//横坐标
                    && curColumn >= 0 && curColumn < boardColumns) {//纵坐标
                //再检查当前位置是否表示活细胞
                if (board[curRow][curColumn] == 1 //当前位置值为1表示此细胞一直是活细胞
                        //-1表示此细胞本来是活细胞，更新状态后死去，但在更新其他细胞的状态时，应将其作为活细胞对待
                        || board[curRow][curColumn] == -1) {
                    totalAlive++;
                }
            }
        }
        return totalAlive;
    }

    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        Solution solution = new Solution();
        solution.gameOfLife_withExtraStatus(board);

        for (int[] row : board) {
            for (int n : row) {
                System.out.print(n + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
