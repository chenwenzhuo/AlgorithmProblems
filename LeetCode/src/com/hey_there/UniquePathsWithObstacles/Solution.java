package com.hey_there.UniquePathsWithObstacles;

public class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int gridWidth = obstacleGrid[0].length;
        int gridHeight = obstacleGrid.length;

        /*首先处理特殊情况*/

        //若起始格子为障碍物，直接返回0
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        //若网格只有一行或一列，检查其中是否存在障碍物
        //若有，直接返回0，没有直接返回1
        if (gridWidth == 1) {//若只有一列
            for (int[] rows : obstacleGrid) {
                if (rows[0] == 1) {
                    return 0;
                }
            }
            return 1;
        }
        if (gridHeight == 1) {//若只有一行
            for (int i = 0; i < gridWidth; i++) {
                if (obstacleGrid[0][i] == 1) {
                    return 0;
                }
            }
            return 1;
        }

        /*接下来处理普遍情况，即obstacleGrid有多行多列的情况*/

        //用一个数组存储从坐标(0,0)的格子到(i,j)格子的路径数
        int[][] pathsCount = new int[gridHeight][gridWidth];

        //从(0,0)到第一行和第一列的格子都只有一种走法
        //若obstacleGrid的第一行和第一列中有障碍物，障碍物格子及后面的格子都初始化为0
        int initVal = 1;
        for (int i = 0; i < gridWidth; i++) {
            if (obstacleGrid[0][i] == 1) {
                initVal = 0;
            }
            pathsCount[0][i] = initVal;
        }
        initVal = 1;
        for (int i = 1; i < gridHeight; i++) {
            if (obstacleGrid[i][0] == 1) {
                initVal = 0;
            }
            pathsCount[i][0] = initVal;
        }

        //计算(0,0)到其他格子的走法
        for (int i = 1; i < gridHeight; i++) {
            for (int j = 1; j < gridWidth; j++) {
                if (obstacleGrid[i][j] == 1) {
                    //若当前格子是障碍物，到此格子的路径数应该为0
                    pathsCount[i][j] = 0;
                    continue;
                }

                //除了第一行和第一列之外，其他格子的左边和上方都有格子
                //到当前格子的路径数等于到左边格子的路径数加到上方格子的路径数
                pathsCount[i][j] = pathsCount[i][j - 1] + pathsCount[i - 1][j];
            }
        }
        return pathsCount[gridHeight - 1][gridWidth - 1];
    }

    public static void main(String[] args) {
        //int[][] obstacleGrid = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] obstacleGrid = {{1}, {0}};
        Solution solution = new Solution();
        int paths = solution.uniquePathsWithObstacles(obstacleGrid);
        System.out.println(paths);
    }
}
