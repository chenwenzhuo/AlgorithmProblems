package com.hey_there.BurstBalloons;

public class Solution {
    public int maxCoins(int[] nums) {
        int len_nums = nums.length;
        if (len_nums == 0) {
            return 0;
        }
        //将原数组复制到一个长为len_nums+2的数组中
        int len_extended = len_nums + 2;
        int[] extendedNums = new int[len_extended];
        extendedNums[0] = extendedNums[len_extended - 1] = 1;
        System.arraycopy(nums, 0, extendedNums, 1, len_nums);

        //dp[i][j]表示戳完开区间(i,j)中的所有气球，能得到的最大分数
        int[][] dp = new int[len_extended][len_extended];
        //沿对角线斜着遍历dp数组的右上半部分
        //inc表示每一趟斜线遍历纵坐标相对于横坐标的增量
        //inc从2开始，因为当inc=0和inc=1时，
        //(i,i)和(i,i+1)区间内没有气球可戳，dp[i][i]和dp[i][i+1]都一定为0
        for (int inc = 2; inc < len_extended; inc++) {
            for (int row = 0; row < len_extended - 2 && row + inc < len_extended; row++) {
                int column = row + inc;
                for (int k = row + 1; k < column; k++) {
                    dp[row][column] = Math.max(dp[row][column],
                            dp[row][k] + dp[k][column] + extendedNums[row] * extendedNums[k] * extendedNums[column]);
                }
            }
        }
        return dp[0][len_extended - 1];
    }
}
