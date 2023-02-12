package com.hey_there._1138_AlphabetBoardPath;

public class Solution {
    private StringBuilder res = new StringBuilder();

    public String alphabetBoardPath(String target) {
        int curRow = 0, curCol = 0;//当前所在坐标
        for (int i = 0; i < target.length(); i++) {
            char nextCh = target.charAt(i);//下一个要添加的字母
            //计算nextCh在字母板上的坐标
            int nextRow = (nextCh - 'a') / 5;
            int nextCol = (nextCh - 'a') % 5;
            //若当前所在字母板的坐标是字母z，则先纵向移动，否则先横向移动
            move(curRow, curCol, nextRow, nextCol, curRow != 5 || curCol != 0);
            //更新当前坐标
            curRow = nextRow;
            curCol = nextCol;
        }
        return res.toString();
    }

    private void move(int curRow, int curCol, int nextRow, int nextCol, boolean horizFirst) {
        char dir = '-';//移动的方向
        int dist;//移动的距离
        if (horizFirst) {//先横向移动
            //横向的移动
            dist = nextCol - curCol;
            if (dist > 0)
                dir = 'R';
            else if (dist < 0)
                dir = 'L';
            res.append(String.valueOf(dir).repeat(Math.abs(dist)));
            //纵向的移动
            dist = nextRow - curRow;
            if (dist > 0)
                dir = 'D';
            else if (dist < 0)
                dir = 'U';
            res.append(String.valueOf(dir).repeat(Math.abs(dist)));
        } else {//先纵向移动
            //纵向的移动
            dist = nextRow - curRow;
            if (dist > 0)
                dir = 'D';
            else if (dist < 0)
                dir = 'U';
            res.append(String.valueOf(dir).repeat(Math.abs(dist)));
            //横向的移动
            dist = nextCol - curCol;
            if (dist > 0)
                dir = 'R';
            else if (dist < 0)
                dir = 'L';
            res.append(String.valueOf(dir).repeat(Math.abs(dist)));
        }
        res.append('!');//移动到指定位置，放置字母
    }
}
