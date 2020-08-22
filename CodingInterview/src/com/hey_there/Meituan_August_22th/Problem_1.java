package com.hey_there.Meituan_August_22th;

import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入字符串数量T
        int T = Integer.parseInt(sc.nextLine());
        int processed = 0;//已处理的字符串数量
        while (processed < T) {
            //输入一个用户名字符串
            String uname = sc.nextLine();
            char[] unameChs = uname.toCharArray();
            //检查首字符是否是字母
            if (!('A' <= unameChs[0] && unameChs[0] <= 'Z') && !('a' <= unameChs[0] && unameChs[0] <= 'z')) {
                System.out.println("Wrong");
                continue;
            }
            int countLetter = 0, countNum = 0;//字母和数字的计数器
            boolean isValid = true;
            for (char c : unameChs) {//逐个检查用户名中的字符
                if (('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')) {
                    countLetter++;
                } else if ('0' <= c && c <= '9') {
                    countNum++;
                } else {
                    isValid = false;//改变标记值
                    break;
                }
            }
            if (!isValid) {
                System.out.println("Wrong");
            } else {
                if (countLetter == 0 || countNum == 0) {
                    System.out.println("Wrong");
                } else {
                    System.out.println("Accept");
                }
            }
            processed++;
        }
    }
}
