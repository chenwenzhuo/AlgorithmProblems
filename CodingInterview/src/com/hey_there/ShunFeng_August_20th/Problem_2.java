package com.hey_there.ShunFeng_August_20th;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读入任务个数
        int n = Integer.parseInt(sc.nextLine());
        //数组存放开始时间、结束时间及收益
        int[][] taskDetails = new int[n][3];
        for (int i = 0; i < n; i++) {
            String[] curLine = sc.nextLine().split(" ");
            taskDetails[i][0] = Integer.parseInt(curLine[0]);
            taskDetails[i][1] = Integer.parseInt(curLine[1]);
            taskDetails[i][2] = Integer.parseInt(curLine[2]);
        }

        //将任务按照结束时间排序
        Arrays.sort(taskDetails, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[1] - arr2[1];
            }
        });

        int maxMoney = 0;
        int[][] dp = new int[n][2];
        for (int[] dpRow : dp)
            Arrays.fill(dpRow, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = 0; j < n; j++) {
                if (dp[j][0] <= taskDetails[i][0])
                    temp = Math.max(temp, dp[j][1]);
                else
                    break;
            }
            dp[i][0] = taskDetails[i][1];
            dp[i][1] = temp + taskDetails[i][2];
            maxMoney = Math.max(maxMoney, dp[i][1]);
        }
        System.out.println(maxMoney);
    }
}
