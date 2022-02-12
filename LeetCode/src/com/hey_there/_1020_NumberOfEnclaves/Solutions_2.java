package com.hey_there._1020_NumberOfEnclaves;

import java.util.ArrayDeque;

public class Solutions_2 {
    //向上下左右四个方向移动时，横纵坐标的变化量
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numEnclaves(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        //标记在遍历过程中被访问过的格子,默认值false
        boolean[][] visited = new boolean[rows][cols];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        //将边界上所有的"陆地"格子加入队列中
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] == 1) {//第一列
                queue.offer(new int[]{i, 0});
                visited[i][0] = true;
            }
            if (grid[i][cols - 1] == 1) {//最后一列
                queue.offer(new int[]{i, cols - 1});
                visited[i][cols - 1] = true;
            }
        }
        for (int j = 1; j < cols - 1; j++) {
            if (grid[0][j] == 1) {//第一行
                queue.offer(new int[]{0, j});
                visited[0][j] = true;
            }
            if (grid[rows - 1][j] == 1) {//最后一行
                queue.offer(new int[]{rows - 1, j});
                visited[rows - 1][j] = true;
            }
        }
        //每次从queue中取出一个格子，bfs检查其周围格子是否为"陆地"
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int i = 0; i < 4; i++) {
                int newR = curPos[0] + dirs[i][0], newC = curPos[1] + dirs[i][1];
                //检查坐标是否越界、格子是否为"陆地"、是否已访问过
                if (newR < 0 || newR >= rows || newC < 0 || newC >= cols
                        || grid[newR][newC] == 0 || visited[newR][newC])
                    continue;
                visited[newR][newC] = true;
                queue.offer(new int[]{newR, newC});//将当前格子加入队列
            }
        }
        int enclaves = 0;//飞地的数量
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 1 && !visited[i][j])
                    enclaves++;
            }
        }
        return enclaves;
    }
}
