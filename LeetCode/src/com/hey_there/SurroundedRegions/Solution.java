package com.hey_there.SurroundedRegions;

import java.util.ArrayDeque;

public class Solution {
    private char[][] board;
    private int bRows;
    private int bColumns;
    private ArrayDeque<Integer> stack;
    private int[][] inc;

    public Solution() {
        this.stack = new ArrayDeque<>();//栈辅助dfs
        //dfs时坐标在四个方向上的横纵坐标增量
        this.inc = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        this.board = board;
        this.bRows = board.length;
        this.bColumns = board[0].length;
        //搜索数组的边界，对字符‘O'进行dfs
        //第一行和最后一行
        for (int c = 0; c < bColumns; c++) {
            if (board[0][c] == 'O') {
                //找到'O'则dfs
                dfs(0, c);
            }
            if (board[bRows - 1][c] == 'O') {
                dfs(bRows - 1, c);
            }
        }
        //第一列和最后一列
        for (int r = 0; r < bRows; r++) {
            if (board[r][0] == 'O') {
                dfs(r, 0);
            }
            if (board[r][bColumns - 1] == 'O') {
                dfs(r, bColumns - 1);
            }
        }
        //完成dfs后，遍历数组，将剩余的'O'改为'X'，将'#'改回'O'
        for (int i = 0; i < bRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(int xO, int yO) {
        stack.push(xO * bColumns + yO);//将字符'O'在数组中的顺序位置入栈
        board[xO][yO] = '#';//用'#'标记与边界连通的'O'
        while (!stack.isEmpty()) {
            int pos_O = stack.peek();//顺序位置
            //计算横纵坐标
            int curRow = pos_O / bColumns;
            int curColumn = pos_O % bColumns;
            //检查四个方向上是否有'O'
            for (int i = 0; i < 4; i++) {
                int checkRow = curRow + inc[i][0];
                int checkColumn = curColumn + inc[i][1];
                //当checkRow和checkColumn是有效坐标且此位置上是字母'O'
                if (0 <= checkRow && checkRow < bRows &&
                        0 <= checkColumn && checkColumn < bColumns &&
                        board[checkRow][checkColumn] == 'O') {
                    stack.push(checkRow * bColumns + checkColumn);//顺序位置入栈
                    board[checkRow][checkColumn] = '#';//标记为'#'
                    break;
                }
            }
            if (pos_O == stack.peek()) {
                //若旧栈顶与新栈顶相等，说明在坐标(curRow,curColumn)周围没有直接相连的'O'
                //可以将栈顶弹出
                stack.pop();
            }
        }
    }
}
