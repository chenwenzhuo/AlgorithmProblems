package com.hey_there.WordSearch;

import java.util.ArrayDeque;

public class Solution {
    private char[][] board;
    private char[] chs_word;
    private int bRows;
    private int bCols;
    private int len_word;
    //栈用来存储搜索路径
    private ArrayDeque<Integer> stack;
    //右、下、左、上四个搜索方向的坐标增量
    private int[][] directions;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.chs_word = word.toCharArray();
        this.bRows = board.length;
        this.bCols = board[0].length;
        this.len_word = word.length();
        this.stack = new ArrayDeque<>();
        this.directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        for (int r = 0; r < bRows; r++) {
            for (int c = 0; c < bCols; c++) {
                boolean success = backtrackSearch(r, c, 0);
                if (success) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param startX  在board数组中开始搜索的横坐标
     * @param startY  在board数组中开始搜索的横坐标
     * @param wordIdx word中下一个要搜索的字符的下标
     * @return 从当前位置开始搜索word的剩下部分是否成功
     */
    private boolean backtrackSearch(int startX, int startY, int wordIdx) {
        //若开始坐标处的字符与wordIdx所指的字符不同，则搜索失败
        if (board[startX][startY] != chs_word[wordIdx]) {
            return false;
        }
        /*开始坐标处的字符与wordIdx所指的字符相同时*/
        //检查当前wordIdx是否指向word的最后一个字符
        if (wordIdx == len_word - 1) {
            return true;
        }
        //将坐标(startX,startY)在board中的顺序位置入栈
        stack.push(startX * bCols + startY);
        //搜索四个方向
        for (int i = 0; i < 4; i++) {
            //计算当前方向上的下一个坐标
            int nextX = startX + directions[i][0];
            int nextY = startY + directions[i][1];
            //下一个坐标越界或已在栈中则跳过
            if (nextX < 0 || nextX >= bRows ||
                    nextY < 0 || nextY >= bCols ||
                    stack.contains(nextX * bCols + nextY)) {
                continue;
            }
            //下一个坐标不在栈中，向下搜索
            boolean success = backtrackSearch(nextX, nextY, wordIdx + 1);
            //一旦某方向上搜索成功，直接返回true
            if (success) {
                return true;
            }
        }
        //四个方向上都未搜索成功，返回false
        stack.pop();//将坐标(startX,startY)在board中的顺序位置出栈
        return false;
    }

    public static void main(String[] args) {
        /*char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCB";*/
        /*char[][] board = {
                {'A', 'B', 'C', 'D', 'E'},
                {'T', 'S', 'R', 'Q', 'F'},
                {'M', 'N', 'O', 'P', 'G'},
                {'L', 'K', 'J', 'I', 'H'}};
        String word = "ABCDEFGHIJKLMNOPQRST";*/
        char[][] board = {
                {'b', 'a', 'a', 'b', 'a', 'b'},
                {'a', 'b', 'a', 'a', 'a', 'a'},
                {'a', 'b', 'a', 'a', 'a', 'b'},
                {'a', 'b', 'a', 'b', 'b', 'a'},
                {'a', 'a', 'b', 'b', 'a', 'b'},
                {'a', 'a', 'b', 'b', 'b', 'a'},
                {'a', 'a', 'b', 'a', 'a', 'b'}};
        String word = "aabbbbabbaababaaaabababbaaba";
        Solution solution = new Solution();
        boolean success = solution.exist(board, word);
        System.out.println(success);
    }
}
