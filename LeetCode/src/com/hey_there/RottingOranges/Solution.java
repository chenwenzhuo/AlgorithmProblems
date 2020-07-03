package com.hey_there.RottingOranges;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Solution {
    public int orangesRotting(int[][] grid) {
        int freshOranges = 0;//新鲜橘子的数量
        //用队列存储腐烂橘子的坐标
        Queue<Integer> rottenOrangesCoordinates = new ArrayDeque<>();
        //存储一个橘子编号和腐烂时间之间的映射
        Map<Integer, Integer> orangeRottingTime = new HashMap<>();

        //遍历数组，找出新鲜橘子数量和腐烂橘子坐标
        int rows = grid.length;
        int columns = grid[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (grid[r][c] == 1) {
                    //计算好橘子数量
                    freshOranges++;
                } else if (grid[r][c] == 2) {
                    //若当前坐标是坏橘子，则将其编号入队，并将腐烂时间都存为0
                    int code = r * columns + c;
                    rottenOrangesCoordinates.offer(code);
                    orangeRottingTime.put(code, 0);
                }
            }
        }

        //若没有好橘子，直接返回0
        if (freshOranges == 0) {
            return 0;
        }

        int minutes = 0;
        while (!rottenOrangesCoordinates.isEmpty()) {
            int oldCode = rottenOrangesCoordinates.poll();//获取一个坏橘子的编号
            int r = oldCode / columns, c = oldCode % columns;//计算其坐标

            //检查上下左右的橘子，若是好橘子则将其变为坏的
            if (r - 1 >= 0 && grid[r - 1][c] == 1) {//上
                grid[r - 1][c] = 2;
                freshOranges--;

                //新产生的坏橘子的编号
                int newCode = (r - 1) * columns + c;
                rottenOrangesCoordinates.offer(newCode);
                orangeRottingTime.put(newCode, orangeRottingTime.get(oldCode) + 1);
                minutes = orangeRottingTime.get(newCode);
            }
            if (r + 1 < rows && grid[r + 1][c] == 1) {//下
                grid[r + 1][c] = 2;
                freshOranges--;

                int newCode = (r + 1) * columns + c;
                rottenOrangesCoordinates.offer(newCode);
                orangeRottingTime.put(newCode, orangeRottingTime.get(oldCode) + 1);
                minutes = orangeRottingTime.get(newCode);
            }
            if (c - 1 >= 0 && grid[r][c - 1] == 1) {//左
                grid[r][c - 1] = 2;
                freshOranges--;

                int newCode = r * columns + (c - 1);
                rottenOrangesCoordinates.offer(newCode);
                orangeRottingTime.put(newCode, orangeRottingTime.get(oldCode) + 1);
                minutes = orangeRottingTime.get(newCode);
            }
            if (c + 1 < columns && grid[r][c + 1] == 1) {//右
                grid[r][c + 1] = 2;
                freshOranges--;

                int newCode = r * columns + (c + 1);
                rottenOrangesCoordinates.offer(newCode);
                orangeRottingTime.put(newCode, orangeRottingTime.get(oldCode) + 1);
                minutes = orangeRottingTime.get(newCode);
            }
        }


        return freshOranges == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        //int[][] grid = {{1}, {2}, {2}};
        Solution solution = new Solution();
        System.out.println(solution.orangesRotting(grid));
    }
}
