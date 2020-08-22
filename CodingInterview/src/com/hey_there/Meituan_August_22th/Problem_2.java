package com.hey_there.Meituan_August_22th;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] n_m_str = sc.nextLine().split(" ");
        int n = Integer.parseInt(n_m_str[0]);
        int m = Integer.parseInt(n_m_str[1]);
        //读入跑腿价格v和重量w，并计算总价格
        int[][] vw = new int[n][4];
        for (int i = 0; i < n; i++) {
            vw[i][0] = i + 1;//第一列是订单编号
            //第二列、第三列是价格v和重量w
            String[] n_w_str = sc.nextLine().split(" ");
            vw[i][1] = Integer.parseInt(n_w_str[0]);
            vw[i][2] = Integer.parseInt(n_w_str[1]);
            vw[i][3] = vw[i][1] + vw[i][2] * 2;//计算总价格
        }
        Arrays.sort(vw, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                //总价值不同时按价值排序
                if (arr1[3] != arr2[3]) return arr2[3] - arr1[3];
                //总价值相同时，按编号的字典序排序
                String orderNum1 = String.valueOf(arr1[0]);
                String orderNum2 = String.valueOf(arr2[0]);
                return orderNum1.compareTo(orderNum2);
            }
        });
        Arrays.sort(vw, 0, m, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[0] - arr2[0];
            }
        });
        System.out.println("vw数组：");
        for (int i = 0; i < n; i++) {
            System.out.println(vw[i][0] + " " + vw[i][1] + " " + vw[i][2] + " " + vw[i][3]);
        }
        System.out.println("结果：");
        for (int i = 0; i < m; i++) {
            System.out.print(vw[i][0]);
            if (i < m - 1) System.out.print(" ");
        }
        System.out.println();
    }
}
