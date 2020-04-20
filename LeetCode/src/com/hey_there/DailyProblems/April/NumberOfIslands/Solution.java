package com.hey_there.DailyProblems.April.NumberOfIslands;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int gridRows = grid.length, gridColumns = grid[0].length;
        Deque<Integer> stack = new ArrayDeque<>();//栈辅助遍历
        //行和列在上下左右四个方向上的增量
        int[] r = {-1, 1, 0, 0};
        int[] c = {0, 0, -1, 1};

        int islands = 0;
        for (int i = 0; i < gridRows; i++) {
            for (int j = 0; j < gridColumns; j++) {
                //遇到'0'表示水，遇到'2'表示此陆地已访问过
                if (grid[i][j] == '0' || grid[i][j] == '2') {
                    continue;
                }
                //遇到'1'表示找到未访问过的陆地
                islands++;//岛屿数量加1
                grid[i][j] = '2';//标记为已访问
                stack.push(i * gridColumns + j);//将当前位置在grid中的顺序编号入栈
                //深度优先搜索
                while (!stack.isEmpty()) {
                    int curPos = stack.pop();
                    int curPosRow = curPos / gridColumns;
                    int curPosColumn = curPos % gridColumns;
                    //检查当前位置的上下左右方向是否为陆地
                    for (int k = 0; k < 4; k++) {
                        int checkingRow = curPosRow + r[k];
                        int checkingColumn = curPosColumn + c[k];
                        //先检查坐标是否有效，再判断是否是未访问的陆地
                        if (0 <= checkingRow && checkingRow < gridRows &&
                                0 <= checkingColumn && checkingColumn < gridColumns &&
                                grid[checkingRow][checkingColumn] == '1') {
                            //是陆地则将其标记为已访问，并入栈
                            grid[checkingRow][checkingColumn] = '2';
                            stack.push(checkingRow * gridColumns + checkingColumn);
                        }
                    }
                }
            }
        }
        return islands;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        Solution solution = new Solution();
        int islands = solution.numIslands(grid);
        System.out.println(islands);
    }
}
