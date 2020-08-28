package com.hey_there.RobotReturnToOrigin;

public class Solution {
    public boolean judgeCircle(String moves) {
        char[] chsMoves = moves.toCharArray();
        int[] xy = new int[2];//数组两个元素分别记录横坐标和纵坐标
        for (char move : chsMoves) {
            switch (move) {
                case 'R':
                    xy[0]++;
                    break;
                case 'L':
                    xy[0]--;
                    break;
                case 'U':
                    xy[1]++;
                    break;
                case 'D':
                    xy[1]--;
                    break;
            }
        }
        return xy[0] == 0 && xy[1] == 0;
    }
}
