package com.hey_there.ReachableGridsOfRobot;

import java.util.Arrays;

public class Solution {
    public int movingCount(int m, int n, int k) {
        int reachableGrids = 1;//初值为1，因为至少可以到达坐标为[0,0]的格子
        int[][] digitSum = new int[m][n];
        //将所有位置初始化为-1，表示还未计算此位置的digitSum
        for (int row = 0; row < m; row++) {
            Arrays.fill(digitSum[row], -1);
        }
        digitSum[0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //当前格子的digitSum>k，则不能通过此格子到达其右边和下面的格子
                if (digitSum[i][j] > k) {
                    continue;
                }
                //若当前格子的digitSum不超过k且非负，则此格子可达
                //计算其右边和下方格子的digitSum
                if (digitSum[i][j] >= 0) {
                    //右边
                    if (j + 1 < n/*防止越界*/ &&
                            digitSum[i][j + 1] < 0/*防止重复计算*/) {
                        if ((j + 1) % 10 == 0) {
                            digitSum[i][j + 1] = digitSum[i][j] - 8;
                        } else {
                            digitSum[i][j + 1] = digitSum[i][j] + 1;
                        }
                        //digitSum[i][j + 1] = calcDigitsSum(i) + calcDigitsSum(j + 1);
                        if (digitSum[i][j + 1] <= k) {
                            reachableGrids++;
                        }
                    }
                    //下面
                    if (i + 1 < m &&
                            digitSum[i + 1][j] < 0) {
                        if ((i + 1) % 10 == 0) {
                            digitSum[i + 1][j] = digitSum[i][j] - 8;
                        } else {
                            digitSum[i + 1][j] = digitSum[i][j] + 1;
                        }
                        //digitSum[i + 1][j] = calcDigitsSum(i + 1) + calcDigitsSum(j);
                        if (digitSum[i + 1][j] <= k) {
                            reachableGrids++;
                        }
                    }
                }
            }
        }
        return reachableGrids;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.movingCount(38, 15, 9));
    }
}
