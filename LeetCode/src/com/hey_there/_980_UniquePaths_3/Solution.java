package com.hey_there._980_UniquePaths_3;

public class Solution {
    private int totalPaths = 0;//路径计数器
    private int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};//四个移动方向
    private int pathLen = 0;//记录走过的路径长度
    private boolean[][] used;//标记单元格是否已在路径中
    //网格相关信息
    private int[][] grid;
    private int gRows, gCols, spaceCnt = 0;
    private int[] start = new int[2], end = new int[2];

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        gRows = grid.length;
        gCols = grid[0].length;
        used = new boolean[gRows][gCols];
        //统计空格数量，保存起止点位置
        for (int i = 0; i < gRows; i++) {
            for (int j = 0; j < gCols; j++) {
                if (grid[i][j] == 0)
                    spaceCnt++;
                else if (grid[i][j] == 1) {
                    start[0] = i;
                    start[1] = j;
                } else if (grid[i][j] == 2) {
                    end[0] = i;
                    end[1] = j;
                }
            }
        }
        pathLen = 1;//起点加入路径中
        backtrack(start[0], start[1]);
        return totalPaths;
    }

    private void backtrack(int row, int col) {
        //到达终点时，检查路径长度是否满足要求
        if (row == end[0] && col == end[1]) {
            if (pathLen == spaceCnt + 2)
                totalPaths++;//到达终点且经过了所有空格
            //未经过所有空格，直接返回
            return;
        }
        //尝试向四个方向走一步
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dirs[i][0], nextCol = col + dirs[i][1];
            //下一步的单元格越界、已访问、是起点、是障碍，则跳过
            if (nextRow < 0 || nextRow >= gRows || nextCol < 0 || nextCol >= gCols || used[nextRow][nextCol]
                    || grid[nextRow][nextCol] == 1 || grid[nextRow][nextCol] == -1) {
                continue;
            }
            pathLen++;//下一步的格子加入路径
            used[nextRow][nextCol] = true;//标记为已访问
            backtrack(nextRow, nextCol);
            //恢复场景
            pathLen--;
            used[nextRow][nextCol] = false;
        }
    }
}
