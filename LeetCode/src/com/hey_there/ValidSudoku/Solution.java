package com.hey_there.ValidSudoku;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashMap<Integer, HashSet<Character>> rowSets = new HashMap<>();
        HashMap<Integer, HashSet<Character>> columnSets = new HashMap<>();
        HashMap<Integer, HashSet<Character>> squareSets = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            rowSets.put(i, new HashSet<>());
            columnSets.put(i, new HashSet<>());
            squareSets.put(i, new HashSet<>());
        }

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char curCh = board[r][c];
                if (curCh == '.') {
                    continue;//若当前格为空白则继续检查下一格
                }
                //检查同一行是否有重复的数字
                HashSet<Character> curRowSet = rowSets.get(r);
                if (curRowSet.contains(curCh)) {
                    //当前数字在同一行中出现多次，直接返回false
                    return false;
                } else {
                    //当前数字在同一行中未出现过，则将其加入当前行的字符集合
                    curRowSet.add(curCh);
                }
                //检查同一列
                HashSet<Character> curColumnSet = columnSets.get(c);
                if (curColumnSet.contains(curCh)) {
                    return false;
                } else {
                    curColumnSet.add(curCh);
                }
                //检查同一个3*3方块
                //首先计算方块的坐标
                int rowIdx_square = r / 3;
                int columnIdx_square = c / 3;
                HashSet<Character> curSquareSet = squareSets.get(rowIdx_square * 3 + columnIdx_square);
                if (curSquareSet.contains(curCh)) {
                    return false;
                } else {
                    curSquareSet.add(curCh);
                }
            }
        }
        return true;
    }
}
