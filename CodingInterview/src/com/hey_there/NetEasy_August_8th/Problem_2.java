package com.hey_there.NetEasy_August_8th;

import java.util.HashSet;
import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String inputLine_1 = in.nextLine();
        String inputLine_2 = in.nextLine();
        String[] inputLine_1_arr = inputLine_1.split(" ");
        String[] inputLine_2_arr = inputLine_2.split(" ");

        int n = Integer.parseInt(inputLine_1_arr[0]);
        int m = Integer.parseInt(inputLine_1_arr[1]);

        int[] inputNums = new int[m];
        HashSet<Integer> set = new HashSet<>();//将输入的m个数存入HashSet
        for (int i = 0; i < m; i++) {
            inputNums[i] = Integer.parseInt(inputLine_2_arr[i]);
            set.add(inputNums[i]);
        }

        int[] otherNums = new int[n - m];
        int counter = 1;
        for (int i = 0; i < otherNums.length; i++) {
            while (set.contains(counter) && counter <= n) counter++;
            otherNums[i] = counter;
            counter++;
        }

        StringBuilder builder = new StringBuilder();
        int idx1 = 0;//inputNums数组的下标
        int idx2 = 0;//otherNums数组的下标
        while (idx1 < inputNums.length && idx2 < otherNums.length) {
            if (compareTwoNum(inputNums[idx1], otherNums[idx2])) {
                builder.append(inputNums[idx1]);
                idx1++;
            } else {
                builder.append(otherNums[idx2]);
                idx2++;
            }
            builder.append(" ");
        }
        while (idx1 < inputNums.length) {
            builder.append(inputNums[idx1]);
            if (idx1 < inputNums.length - 1) {
                builder.append(" ");
            }
            idx1++;
        }
        while (idx2 < otherNums.length) {
            builder.append(otherNums[idx2]);
            if (idx2 < otherNums.length - 1) {
                builder.append(" ");
            }
            idx2++;
        }
        System.out.println(builder.toString());
    }

    //比较两个数的字典序，返回true表示n1在前，false表示n2在前
    private static boolean compareTwoNum(int n1, int n2) {
        //计算n1和n2各自是几位数
        int n1Digits = ((int) Math.log10(n1)) + 1;
        int n2Digits = ((int) Math.log10(n2)) + 1;
        //用数组存储n1和n2的各个数位上的数字
        int[] n1Arr = new int[n1Digits];
        int[] n2Arr = new int[n2Digits];
        for (int i = n1Digits - 1; i >= 0; i--) {
            n1Arr[i] = n1 % 10;
            n1 /= 10;
        }
        for (int i = n2Digits - 1; i >= 0; i--) {
            n2Arr[i] = n2 % 10;
            n2 /= 10;
        }
        //比较字典序
        int idx = 0;
        while (idx < n1Digits && idx < n2Digits) {
            if (n1Arr[idx] < n2Arr[idx]) return true;
            else if (n1Arr[idx] > n2Arr[idx]) return false;
            idx++;
        }
        //比较完其中一个数的所有位都没得出结果
        //则比较哪个数长度更长
        return n1Digits < n2Digits;
    }
}
