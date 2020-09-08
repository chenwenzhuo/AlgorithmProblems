package com.hey_there.Xiaomi_September_8th;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Problem_2 {
    private static char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}};
    private static char[] chsWord;
    private static int bRows, bCols;
    private static int lenWord;
    private static ArrayDeque<Integer> stack;
    private static int[][] direc = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        sc.close();

        chsWord = word.toCharArray();
        bRows = 3;
        bCols = 4;
        lenWord = word.length();
        stack = new ArrayDeque<>();

        for (int r = 0; r < bRows; r++) {
            for (int c = 0; c < bCols; c++) {
                boolean success = backtrackSearch(r, c, 0);
                if (success) {
                    System.out.println("true");
                    return;
                }
            }
        }
        System.out.println("false");
    }

    public static boolean backtrackSearch(int startX, int startY, int wordIdx) {
        //若开始坐标处的字符与wordIdx所指的字符不同，则搜索失败
        if (board[startX][startY] != chsWord[wordIdx]) return false;

        //开始坐标处的字符与wordIdx所指的字符相同时，
        //检查当前wordIdx所指的是否是word的最后一个字符,
        //是最后一个字符则返回true，搜索成功
        if (wordIdx == lenWord - 1) return true;

        //wordIdx不是指向最后一个字符：
        //将坐标(startX,startY)入栈
        stack.push(startX * bCols + startY);
        //搜索四个方向
        for (int i = 0; i < 4; i++) {
            int nextX = startX + direc[i][0];
            int nextY = startY + direc[i][1];

            if (nextX < 0 || nextX >= bRows
                    || nextY < 0 || nextY >= bCols
                    || stack.contains(nextX * bCols + nextY)) {
                continue;
            }
            boolean success = backtrackSearch(nextX, nextY, wordIdx + 1);
            if (success) return true;
        }
        stack.pop();
        return false;
    }
}
