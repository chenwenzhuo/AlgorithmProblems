package com.hey_there._212_WordSearch_2;

import java.util.*;

public class Solution {
    private List<String> res = new ArrayList<>();
    private HashSet<String> wordsSet = new HashSet<>();
    private int m, n;
    private boolean[][] used;
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        this.m = board.length;
        this.n = board[0].length;
        this.used = new boolean[this.m][this.n];
        //将所有单词加入set集合
        wordsSet.addAll(Arrays.asList(words));

        StringBuilder builder = new StringBuilder();
        //单词最长不超过10，从每个坐标开始，回溯搜索，递归深度最长为10
        searchBoard:
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (wordsSet.size() == 0)//没有待搜索的单词，退出循环
                    break searchBoard;
                //否则以当前坐标为起点，搜索单词w
                builder.append(board[i][j]);
                used[i][j] = true;
                backtrack(board, i, j, builder);
                builder.setLength(0);
                used[i][j] = false;
            }
        return res;
    }

    private void backtrack(char[][] board, int row, int col, StringBuilder builder) {
        //wordsSet中包含当前builder的内容，则将其加入结果集合
        if (wordsSet.contains(builder.toString())) {
            res.add(builder.toString());
            wordsSet.remove(builder.toString());
        }
        if (builder.length() == 10)//递归深度不超过10
            return;
        //从当前坐标开始，检查周围四个方向能否走下去
        for (int i = 0; i < 4; i++) {
            if (wordsSet.size() == 0)
                return;//没有待搜索的单词，直接返回
            int nextRow = row + dirs[i][0];
            int nextCol = col + dirs[i][1];
            //下一个坐标越界、已使用过，则跳过
            if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || used[nextRow][nextCol])
                continue;
            builder.append(board[nextRow][nextCol]);
            used[nextRow][nextCol] = true;
            backtrack(board, nextRow, nextCol, builder);
            builder.setLength(builder.length() - 1);
            used[nextRow][nextCol] = false;
        }
    }

    public static void main(String[] args) {
        /*char[][] board = {{'o', 'a', 'b', 'n'}, {'o', 't', 'a', 'e'}, {'a', 'h', 'k', 'r'}, {'a', 'f', 'l', 'v'}};
        String[] words = {"oa", "oaa"};*/
        char[][] board = {
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'}};
        String[] words = {"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"};
        List<String> res = new Solution().findWords(board, words);
        System.out.println(res);
    }
}
