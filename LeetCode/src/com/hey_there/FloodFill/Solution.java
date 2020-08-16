package com.hey_there.FloodFill;

import java.util.ArrayDeque;

public class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int iRows = image.length, iCols = image[0].length;
        int[][] direc = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int initVal = image[sr][sc];
        //新旧值相等，则不需要操作
        if (initVal == newColor) return image;
        ArrayDeque<Integer> queue = new ArrayDeque<>();//队列辅助bfs
        queue.offer(sr * iCols + sc);//起点入队
        while (!queue.isEmpty()) {
            //从队列中取出一个点,计算其坐标
            int curPos = queue.poll();
            int curRow = curPos / iCols;
            int curCol = curPos % iCols;
            //变色
            image[curRow][curCol] = newColor;
            //检查周围四个位置，若等于initVal则将其入队
            for (int i = 0; i < 4; i++) {
                int checkRow = curRow + direc[i][0];
                int checkCol = curCol + direc[i][1];
                if (0 <= checkRow && checkRow < iRows
                        && 0 <= checkCol && checkCol < iCols
                        && image[checkRow][checkCol] == initVal) {
                    queue.offer(checkRow * iCols + checkCol);
                }
            }
        }
        return image;
    }
}
