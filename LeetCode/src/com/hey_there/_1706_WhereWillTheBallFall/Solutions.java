package com.hey_there._1706_WhereWillTheBallFall;

public class Solutions {
    public int[] findBall(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[] res = new int[cols];
        for (int i = 0; i < cols; i++) {
            int x = 0, y = i;//球初始状态处于的行和列
            while (true) {
                int nextY = y + grid[x][y];
                //若列越界或相邻的格子的格子值不同，则球被卡住，结束循环
                if (nextY < 0 || nextY >= cols ||
                        grid[x][y] != grid[x][nextY]) {
                    res[i] = -1;
                    break;
                }
                //相邻格子值相同时，球向下掉
                int nextX = x + 1;
                if (nextX >= rows) {//行越界，球掉出去
                    res[i] = nextY;
                    break;
                }
                x = nextX;
                y = nextY;
            }
        }
        return res;
    }
}
