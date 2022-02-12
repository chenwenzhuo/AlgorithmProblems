package com.hey_there._1020_NumberOfEnclaves;

//深度优先遍历
public class Solutions_1 {
    //向上下左右四个方向移动时，横纵坐标的变化量
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int rows, cols;//grid的行数、列数
    private boolean[][] visited;//标记在遍历过程中被访问过的格子

    public int numEnclaves(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        visited = new boolean[rows][cols];//初始化数组，默认值自动为false
        //从边界上的格子开始进行dfs遍历
        for (int i = 0; i < rows; i++) {
            //第一列
            if (grid[i][0] == 1) dfs(grid, i, 0);
            //最后一列
            if (grid[i][cols - 1] == 1) dfs(grid, i, cols - 1);
        }
        for (int j = 1; j < cols - 1; j++) {
            //第一行
            if (grid[0][j] == 1) dfs(grid, 0, j);
            //最后一行
            if (grid[rows - 1][j] == 1) dfs(grid, rows - 1, j);
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

    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || grid[r][c] == 0 || visited[r][c])
            return;
        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            dfs(grid, r + dirs[i][0], c + dirs[i][1]);
        }
    }
}
