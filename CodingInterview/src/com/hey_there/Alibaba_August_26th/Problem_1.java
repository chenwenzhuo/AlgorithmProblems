package com.hey_there.Alibaba_August_26th;

import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入T
        int T = Integer.parseInt(sc.nextLine());
        int processed = 0;
        while (processed < T) {
            //输入n及单词A，B
            String[] curLine = sc.nextLine().split(" ");
            int n = Integer.parseInt(curLine[0]);
            String A = curLine[1];
            String B = curLine[2];

            char[] chsA = A.toCharArray();
            char[] chsB = B.toCharArray();
            //检查A是否大于B
            boolean ABiggerThanB = A.equals(B);
            for (int i = 0; i < n; i++) {
                if (chsA[i] > chsB[i]) {
                    ABiggerThanB = true;
                    break;
                } else if (chsA[i] < chsB[i]) {
                    break;
                }
            }
            if (ABiggerThanB) {//A大于等于B，输出0
                System.out.println("0");
                processed++;
                continue;
            }
            //A小于B时，做26进制减法
            int[] resArr = new int[n];
            for (int i = n - 1; i >= 0; i--) {
                if (chsB[i] >= chsA[i]) {//B的当前位较大时可直接减
                    resArr[i] = chsB[i] - chsA[i];
                } else {//A的当前位较大时需要向上借位
                    resArr[i] = chsB[i] - chsA[i] + 26;
                    //高位减1
                    chsB[i - 1] = (char) (chsB[i - 1] - 1);
                }
            }
            //从resArr中减1
            for (int i = n - 1; i >= 0; i--) {
                if (resArr[i] >= 1) {
                    resArr[i] -= 1;
                    break;
                } else {
                    resArr[i] = 25;
                }
            }
            //计算数量
            long exp = 1;
            long res = 0;
            for (int i = n - 1; i >= 0; i--) {
                res += resArr[i] * exp;
                exp *= 26;
            }
            System.out.println(res);

            processed++;
        }
    }
}
