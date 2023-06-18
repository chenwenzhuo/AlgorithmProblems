package com.hey_there._1254_NumberOfClosedIslands;

import java.util.ArrayDeque;

public class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        //四个方向上的坐标增量
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int ans = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != 0)
                    continue;//当前坐标不是未访问过的土地，跳过
                ArrayDeque<int[]> queue = new ArrayDeque<>();
                queue.offer(new int[]{i, j});
                grid[i][j] = -1;//值设为-1，表示当前坐标已访问过
                boolean closed = true;
                while (!queue.isEmpty()) {
                    //获取坐标，检查当前坐标是否在地图边缘
                    int[] cord = queue.poll();
                    int x = cord[0], y = cord[1];
                    //当前坐标在地图边缘，则当前岛屿不封闭
                    if (x == 0 || x == rows - 1 || y == 0 || y == cols - 1)
                        closed = false;

                    //当前坐标不在地图边缘，检查周围四个坐标
                    for (int k = 0; k < 4; k++) {
                        int nextX = x + dirs[k][0];
                        int nextY = y + dirs[k][1];
                        //周围坐标是未访问的土地，则加入队列
                        if (0 <= nextX && nextX < rows && 0 <= nextY && nextY < cols
                                && grid[nextX][nextY] == 0) {
                            queue.offer(new int[]{nextX, nextY});
                            grid[nextX][nextY] = -1;
                        }
                    }
                }
                if (closed)
                    ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };
        Solution solution = new Solution();
        int ans = solution.closedIsland(grid);
        System.out.println(ans);
    }
}
