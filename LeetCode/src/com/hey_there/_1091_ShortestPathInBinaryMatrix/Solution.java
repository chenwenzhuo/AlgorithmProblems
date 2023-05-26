package com.hey_there._1091_ShortestPathInBinaryMatrix;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] != 0) return -1;//起点不通，返回-1
        int n = grid.length;
        int[][] dist = new int[n][n];//记录起点到每个点的最短距离，初始化为无穷大
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 1;//起点到自身的距离为1
        int[][] dirs = {//八个方向上的坐标增量
                {-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {1, -1}, {-1, 1}, {1, 1}
        };
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});//起点入队
        while (!queue.isEmpty()) {
            int[] cord = queue.poll();
            if (cord[0] == n - 1 && cord[1] == n - 1)
                return dist[n - 1][n - 1];//走到终点，返回路径长度
            for (int i = 0; i < 8; i++) {
                int nextX = cord[0] + dirs[i][0];
                int nextY = cord[1] + dirs[i][1];
                //下一步的坐标越界、不通或已走过，则跳过
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n)//越界
                    continue;
                if (grid[nextX][nextY] != 0 || dist[nextX][nextY] <= dist[cord[0]][cord[1]] + 1)
                    continue;//不通或已走过
                grid[cord[0]][cord[1]] = 1;//将当前坐标值改为1，表示已走过
                dist[nextX][nextY] = Math.min(dist[nextX][nextY], dist[cord[0]][cord[1]] + 1);
                queue.offer(new int[]{nextX, nextY});//下一步的坐标入队
            }
        }
        return -1;
    }
}
