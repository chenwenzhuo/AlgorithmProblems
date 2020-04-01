package com.hey_there.DailyProblems.March.AvailableCapturesOfRook;

public class Solution {
    public int numRookCaptures(char[][] board) {
        int rookRow = -1, rookColumn = -1;//车所在位置的横纵坐标
        //寻找车的位置
        for (int i = 0; i < 8; i++) {//题目描述中规定board为8行8列
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 'R') {
                    rookRow = i;
                    rookColumn = j;
                    break;
                }
            }
            if (rookRow >= 0) {
                break;//rookRow非负表示位置已找到，退出循环
            }
        }

        //从车的位置，向上下左右寻找黑色的卒
        int available = 0;
        int[] r = {-1, 0, 1, 0};//上右下左方向上行的增量
        int[] c = {0, 1, 0, -1};//上右下左方向上列的增量
        for (int i = 0; i < 4; i++) {
            int tempRow = rookRow, tempColumn = rookColumn;
            while (board[tempRow][tempColumn] != 'B') {
                if (board[tempRow][tempColumn] == 'p') {
                    available++;
                    break;
                }
                tempRow += r[i];
                tempColumn += c[i];
                //更新tempRow和tempColumn的值后检查是否越界
                if (tempRow < 0 || tempRow >= 8 || tempColumn < 0 || tempColumn >= 8) {
                    break;//若越界则退出内层循环
                }
            }
        }
        return available;
    }
}
