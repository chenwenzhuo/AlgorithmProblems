package com.hey_there.Didi_August_21th;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 读入n
        int n = Integer.parseInt(sc.nextLine());
        // 生成n^2个斐波那契数
        ArrayList<Long> fibNums = new ArrayList<>(n * n);
        fibNums.add(1L);
        fibNums.add(1L);
        long prev1 = 1, prev2 = 1;
        for (int i = 3; i <= n * n; i++) {
            long next = prev1 + prev2;// 计算下一个斐波那契数
            fibNums.add(next);// 加入集合
            prev1 = prev2;
            prev2 = next;
        }
        // 创建一个n行n列的数组
        long[][] arr = new long[n][n];
        int arrX = 0, arrY = -1;
        int fibNumsIdx = n * n - 1;// 集合fibNums的下标
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};// 四个方向上坐标的变化量
        int dirIdx = 0;

        while (fibNumsIdx >= 0) {
            long curFibNum = fibNums.get(fibNumsIdx);// 待填入的
            fibNumsIdx--;
            // 计算当前要填充的arr数组坐标
            arrX += directions[dirIdx][0];
            arrY += directions[dirIdx][1];
            // 填充arr数组
            arr[arrX][arrY] = curFibNum;

            // 判断在当前方向上是否走到尽头
            int nextX = arrX + directions[dirIdx][0];
            int nextY = arrY + directions[dirIdx][1];
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || arr[nextX][nextY] > 0) {
                // 若当前方向走到尽头，则改变dirIdx的值，以改变填充方向
                dirIdx = (dirIdx + 1) % 4;
            }
        }
        // 输出
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j]);
                if (j < n - 1)
                    System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}