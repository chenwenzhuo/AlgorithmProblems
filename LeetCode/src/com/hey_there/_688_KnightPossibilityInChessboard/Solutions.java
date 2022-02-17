package com.hey_there._688_KnightPossibilityInChessboard;

public class Solutions {
    public double knightProbability(int n, int k, int row, int column) {
        //棋子可移动的8个方向上，横纵坐标的变化量
        int[][] dirs = {
                {2, -1}, {2, 1}, {-2, -1}, {-2, 1},
                {-1, -2}, {1, -2}, {-1, 2}, {1, 2}};
        //设dp[step][i][j]表示从坐标[i][j]出发，走step步后还留在棋盘上的概率
        double[][][] dp = new double[k + 1][n][n];
        //初始化基本情况，即step为0时，dp[0][i][j]一定都为1
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                dp[0][i][j] = 1.0;
        //开始计算dp[step][i][j]
        for (int step = 1; step <= k; step++)
            for (int r = 0; r < n; r++)
                for (int c = 0; c < n; c++)
                    for (int[] dir : dirs) {
                        int nextR = r + dir[0], nextC = c + dir[1];
                        //下一步走出棋盘，不计算概率
                        if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= n)
                            continue;
                        /* 下一步不会走出棋盘则累加概率，
                         * 走到任意一个[nextR][nextC]坐标的概率都为1/8，故除以8*/
                        dp[step][c][r] += (dp[step - 1][nextR][nextC] / 8);
                    }
        return dp[k][row][column];//返回所需概率
    }
}
