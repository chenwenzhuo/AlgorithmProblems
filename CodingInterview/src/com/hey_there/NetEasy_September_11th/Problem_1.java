package com.hey_there.NetEasy_September_11th;

import java.util.Arrays;
import java.util.Scanner;

public class Problem_1 {
    //456 145
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入数字位数
        int numDigits = Integer.parseInt(sc.nextLine());
        //输入两个数字
        String num1_str = sc.nextLine();
        String num2_str = sc.nextLine();
        sc.close();

        //将字符串转为字符数组
        char[] chs_num1 = num1_str.toCharArray();
        char[] chs_num2 = num2_str.toCharArray();
        Arrays.sort(chs_num2);//对数字2的字符数组进行排序

        int idx1 = 0, idx2;
        //used[i]表示chs_num2[i]是否已经被使用
        boolean[] used = new boolean[numDigits];
        Arrays.fill(used, false);
        StringBuilder builder = new StringBuilder();

        //每次从num2的字符中，找到一个大于等于num1当前字符的字符，加入builder中
        while (idx1 < numDigits) {
            idx2 = 0;//每次都从头找
            int builderInitLen = builder.length();

            while (idx2 < numDigits) {
                //若num2的当前字符已经用过，或num2当前字符小于num1的当前字符，继续检查下一个
                if (used[idx2] || chs_num1[idx1] > chs_num2[idx2]) {
                    idx2++;
                    continue;
                }
                //num2的当前字符大于等于num1的当前字符：
                builder.append(chs_num2[idx2]);
                used[idx2] = true;
                break;
            }
            //将num2的所有字符遍历一遍，builder长度没有变化，说明无法组合成比num1更大的数字
            if (builder.length() == builderInitLen) {
                System.out.println(-1);
                return;
            }
            idx1++;
        }

        String rearranged = builder.toString();
        if (rearranged.equals(num1_str)) {
            System.out.println(-1);
        } else {
            System.out.println(rearranged);
        }
    }

    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入数字位数
        int numDigits = Integer.parseInt(sc.nextLine());
        //输入两个数字
        String num1_str = sc.nextLine();
        String num2_str = sc.nextLine();
        sc.close();

        //将字符串转为字符数组
        char[] chs_num1 = num1_str.toCharArray();
        char[] chs_num2 = num2_str.toCharArray();
        Arrays.sort(chs_num2);//对数字2的字符数组进行排序

        int idx1 = 0, idx2;
        //used[i]表示chs_num2[i]是否已经被使用
        boolean[] used = new boolean[numDigits];
        Arrays.fill(used, false);
        StringBuilder builder = new StringBuilder();

        String res = backtrack(chs_num1, chs_num2, numDigits, 0, 0, used, builder);
        if (res.length() < numDigits) System.out.println(-1);
        else System.out.println(res);
    }

    //456  145
    public static String backtrack(char[] chs_num1, char[] chs_num2,
                                   int lenNum, int idx1, int idx2,
                                   boolean[] used, StringBuilder builder) {
        if (builder.length() == lenNum) {
            return builder.toString();
        }

        while (idx2 < lenNum) {
            if (used[idx2] || chs_num1[idx1] > chs_num2[idx2]) {
                idx2++;
                continue;
            }

            builder.append(chs_num2[idx2]);
            used[idx2] = true;
            String res = backtrack(chs_num1, chs_num2, lenNum, idx1 + 1, 0, used, builder);
            if (res.length() == lenNum) return res;

            builder.delete(builder.length() - 1, builder.length());
            used[idx2] = false;
        }
        return builder.toString();
    }*/
}
