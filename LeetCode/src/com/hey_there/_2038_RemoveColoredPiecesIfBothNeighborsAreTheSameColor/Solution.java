package com.hey_there._2038_RemoveColoredPiecesIfBothNeighborsAreTheSameColor;

public class Solution {
    public boolean winnerOfGame_1(String colors) {
        char[] chColors = colors.toCharArray();
        int len = colors.length();
        int[] ops = {0, 0};//数组两元素分别记录删除'A'的次数和删除'B'的次数
        char curCh;//当前字符
        int idx = 0;
        while (idx < len) {
            curCh = chColors[idx];
            int curLen = 0;
            while (idx < len && chColors[idx] == curCh) {
                curLen++;
                idx++;
            }
            if (curLen > 2) {
                if (curCh == 'A') ops[0] += (curLen - 2);
                else ops[1] += (curLen - 2);
            }
        }
        return ops[0] > ops[1];
    }

    public boolean winnerOfGame_2(String colors) {
        int[] ops = {0, 0};//数组两元素分别记录删除'A'的次数和删除'B'的次数
        char curCh = 'C';//正在寻找的字符，可为'A'和'B'外的任意初值
        int curLen = 0;//正在寻找的字符连续出现的次数
        for (int i = 0; i < colors.length(); i++) {
            char c = colors.charAt(i);//取当前字符
            if (c == curCh) {
                curLen++;
                if (curLen >= 3) ops[curCh - 'A']++;
            } else {
                curCh = c;
                curLen = 1;
            }
        }
        return ops[0] > ops[1];
    }

    public static void main(String[] args) {
        String colors = "AAAABBBB";
        Solution solution = new Solution();
        boolean res = solution.winnerOfGame_1(colors);
        System.out.println(res);
    }
}
