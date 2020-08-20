package com.hey_there.ShunFeng_August_20th;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_1 {
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读入m和n
        String[] n_m = sc.nextLine().split(" ");
        int n = Integer.parseInt(n_m[0]);//服务器数量
        int m = Integer.parseInt(n_m[1]);//客户数量
        //读入各个服务器的带宽
        String[] bandwidthsStr = sc.nextLine().split(" ");
        int[] bws = new int[n];
        int idx = 0;
        for (String bwStr : bandwidthsStr) {
            bws[idx++] = Integer.parseInt(bwStr);
        }
        //读入各个客户所需带宽及其预算
        int[][] bwNeeded_budget = new int[m][2];
        for (int i = 0; i < m; i++) {
            String[] lineArr = sc.nextLine().split(" ");
            bwNeeded_budget[i][0] = Integer.parseInt(lineArr[0]);
            bwNeeded_budget[i][1] = Integer.parseInt(lineArr[1]);
        }

        //对服务器带宽排序
        Arrays.sort(bws);
        //记录各个服务器租给了哪个客户
        int[] serverToCustomer = new int[n];
        Arrays.fill(serverToCustomer, -1);//初值-1表示未租出去

        int totalProfit = 0;
        for (int i = 0; i < m; i++) {//遍历所有客户
            int bwNeeded = bwNeeded_budget[i][0];
            int budget = bwNeeded_budget[i][1];
            for (int j = 0; j < n; j++) {//遍历所有服务器
                //当前服务器带宽不够，跳过
                if (bws[j] < bwNeeded) continue;

                //带宽足够时，检查是否已经租出
                if (serverToCustomer[j] >= 0) {
                    //已租出时，比较之前的
                } else {
                    //未租出时，租给当前客户
                    serverToCustomer[j] = i;//将当前服务器标记为“租出”
                    totalProfit += budget;
                    break;
                }
            }
        }
        System.out.println(totalProfit);

        System.out.println("serverToCustomer:");
        for (int i : serverToCustomer) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }*/
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读入m和n
        String[] n_m = sc.nextLine().split(" ");
        int n = Integer.parseInt(n_m[0]);//服务器数量
        int m = Integer.parseInt(n_m[1]);//客户数量
        //读入各个服务器的带宽
        String[] bandwidthsStr = sc.nextLine().split(" ");
        int[] bws = new int[n];
        int idx = 0;
        for (String bwStr : bandwidthsStr) {
            bws[idx++] = Integer.parseInt(bwStr);
        }
        //读入各个客户所需带宽及其预算
        int[][] bwNeeded_budget = new int[m][2];
        for (int i = 0; i < m; i++) {
            String[] lineArr = sc.nextLine().split(" ");
            bwNeeded_budget[i][0] = Integer.parseInt(lineArr[0]);
            bwNeeded_budget[i][1] = Integer.parseInt(lineArr[1]);
        }
        //对服务器带宽排序
        Arrays.sort(bws);
        int[] used = new int[m];
        int maxProfit = 0;
        for (int serverBw : bws) {
            int temp = 0;
            int index = -1;
            for (int i = 0; i < m; i++) {
                if (used[i] == 0 && serverBw >= bwNeeded_budget[i][0]) {
                    if (temp < bwNeeded_budget[i][1]) {
                        temp = bwNeeded_budget[i][1];
                        index = i;
                    }
                }
            }
            if (index != -1) {
                used[index] = 1;
                maxProfit += temp;
            }
        }
        System.out.println(maxProfit);
    }
}
