package com.hey_there.Largest1BorderedSquare;

class Solution {
    public int largest1BorderedSquare(int[][] grid) {
        int maxEdgeLen = 0;//正方形的最大边长
        //dp_up[i][j]表示从上到下方向上，以[i,j]为终点的最长连续1的长度
        int[][] dp_up = new int[grid.length][grid[0].length];
        //dp_down[i][j]表示从下到上方向上，以以[i,j]为终点的最长连续1的长度
        int[][] dp_down = new int[grid.length][grid[0].length];
        //dp_right[i][j]表示从右到左方向上，以以[i,j]为终点的最长连续1的长度
        int[][] dp_right = new int[grid.length][grid[0].length];
        //dp_left[i][j]表示从左到右方向上，以以[i,j]为终点的最长连续1的长度
        int[][] dp_left = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0)
                    dp_up[i][j] = grid[i][j];
                else
                    dp_up[i][j] = grid[i][j] == 1 ? dp_up[i - 1][j] + 1 : 0;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (j == 0)
                    dp_left[i][j] = grid[i][j];
                else
                    dp_left[i][j] = grid[i][j] == 1 ? dp_left[i][j - 1] + 1 : 0;
            }
        }
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (i == grid.length - 1)
                    dp_down[i][j] = grid[i][j];
                else
                    dp_down[i][j] = grid[i][j] == 1 ? dp_down[i + 1][j] + 1 : 0;
            }
        }
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if (j == grid[0].length - 1)
                    dp_right[i][j] = grid[i][j];
                else
                    dp_right[i][j] = grid[i][j] == 1 ? dp_right[i][j + 1] + 1 : 0;
            }
        }

        //遍历grid数组，每次以grid[i][j]作为正方形的右上角，从边长1开始扩展，直到最大可能的边长
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int len = 1;
                while (i + len - 1 < grid.length && j + len - 1 < grid[0].length) {//防止产生数组越界
                    /* if的判断里没有对grid[i][j]是否为1进行判断，
                     * 因为此判断已经隐含在了dp_down[i][j] >= len和dp_right[i][j] >= len中，
                     * 若grid[i][j] == 0，则dp_down[i][j]和dp_right[i][j]都为0，无法满足大于等于len*/
                    if (grid[i + len - 1][j + len - 1] == 1 &&//右下角必须为1
                            dp_up[i + len - 1][j + len - 1] >= len &&//正方形的右侧竖边长度要大于等于当前len
                            dp_left[i + len - 1][j + len - 1] >= len &&//正方形的下方横边长度要大于等于当前len
                            dp_down[i][j] >= len && //正方形的左侧竖边长度要大于等于当前len
                            dp_right[i][j] >= len)//正方形的上方横边长度要大于等于当前len
                        maxEdgeLen = Math.max(maxEdgeLen, len);

                    len++;
                }
            }
        }
        return maxEdgeLen * maxEdgeLen;
    }
}