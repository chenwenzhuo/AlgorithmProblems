package com.hey_there.DJI_August_16th;

import java.util.ArrayDeque;
import java.util.Scanner;

public class Problem_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //读入字符串形式的数
        String strNum = input.nextLine();
        //读入要移除的字符数量
        int K = Integer.parseInt(input.nextLine());

        ArrayDeque<Character> stack = new ArrayDeque<>();//用栈辅助操作
        //将字符串转换为char数组
        char[] numArr = strNum.toCharArray();
        for (char curDigit : numArr) {
            while (!stack.isEmpty() && K > 0 && stack.peekLast() > curDigit) {
                stack.removeLast();
                K--;
            }
            stack.addLast(curDigit);
        }
        for (int i = 0; i < K; i++) stack.removeLast();
        StringBuilder minRemaining = new StringBuilder();
        boolean beginWith0 = true;
        for (char curDigit : stack) {
            if (beginWith0 && curDigit == '0') continue;
            beginWith0 = false;
            minRemaining.append(curDigit);
        }
        if (minRemaining.length() == 0) System.out.println("0");
        System.out.println(minRemaining.toString());
    }
}
