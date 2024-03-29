package com.hey_there.NQueens_2;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int total;

    public int totalNQueens(int n) {
        if (n == 0 || n == 2) {
            return 0;//n为0或2时无解
        }
        if (n == 1) {
            //n为1时可直接得出解
            return 1;
        }

        total = 0;
        List<String> tmp = new ArrayList<>();
        backtrack(n, tmp);
        return total;
    }

    private void backtrack(int n, List<String> tmp) {
        if (tmp.size() == n) {
            total++;
            return;
        }

        for (int i = 0; i < n; i++) {
            String row = genStr(n, i);
            //在genStr已经保证了同一行没有两个Q
            //检查contains，确保同一列没有两个Q
            //checkDiagonal保证同一斜线上没有两个Q
            if (tmp.contains(row) || checkDiagonal(tmp, i)) {
                continue;
            }
            tmp.add(row);
            backtrack(n, tmp);
            tmp.remove(row);
        }
    }

    //生成一个长度为n的字符串，其中第pos位为“Q”，其余位为“.”
    private String genStr(int n, int pos) {
        StringBuilder builder = new StringBuilder(n);
        int index = 0;
        while (index < n) {
            if (index == pos) {
                builder.append("Q");
            } else {
                builder.append(".");
            }
            index++;
        }
        return builder.toString();
    }

    //检查同一斜线上是否有两个Q
    private boolean checkDiagonal(List<String> tmp, int queenPos) {
        int size = tmp.size();
        for (int i = 0; i < size; i++) {
            //从后往前遍历tmp
            String curStr = tmp.get(size - 1 - i);
            if (queenPos - 1 - i >= 0 && curStr.charAt(queenPos - 1 - i) == 'Q') {
                return true;//检查左上方斜线中是否有Q
            }
            if (queenPos + 1 + i < curStr.length() && curStr.charAt(queenPos + 1 + i) == 'Q') {
                return true;//检查右上方斜线中是否有Q
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int totalResults = solution.totalNQueens(8);
        System.out.println("num of results: " + totalResults);
    }
}
