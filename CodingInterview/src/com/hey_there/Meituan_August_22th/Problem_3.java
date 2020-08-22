package com.hey_there.Meituan_August_22th;

import java.util.Scanner;

public class Problem_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读入货物数量n
        int n = Integer.parseInt(sc.nextLine());
        //读入每件货物的重量和取出货物的顺序
        String[] wStr = sc.nextLine().split(" ");
        String[] fStr = sc.nextLine().split(" ");
        int[] weights = new int[n];
        int[] fetch = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(wStr[i]);
            fetch[i] = Integer.parseInt(fStr[i]);
        }
        //计算weights数组的前缀和
        int[] wPreSum = new int[n];
        wPreSum[0] = weights[0];
        for (int i = 1; i < n; i++) {
            wPreSum[i] = wPreSum[i - 1] + weights[i];
        }
        //遍历fetch数组
        for (int i = 0; i < n; i++) {
            //此次要取出的货物在weights数组中的下标
            int fIdx = fetch[i] - 1;
            //取出货物，将前缀和数组的当前位置标为0
            wPreSum[fIdx] = 0;

            //右边的位置减去当前货物的重量
            for (int j = fIdx + 1; j < n; j++) {
                if (wPreSum[j] == 0) break;
                wPreSum[j] -= weights[fIdx];
            }
            int maxWight = fIdx - 1 >= 0 ? wPreSum[fIdx - 1] : 0;
        }
    }
}
