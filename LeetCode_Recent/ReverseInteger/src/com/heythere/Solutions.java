package com.heythere;

import java.util.Stack;

public class Solutions {
    public int reverseInteger(int num) {
        Stack<Integer> digitsOfNum_1 = new Stack<>();//存储数字各个位的栈

        //按个、十、百···的顺序将数字的各个位压入栈中
        int x = num;
        while (0 != x) {
            digitsOfNum_1.push(x % 10);
            x /= 10;
        }

        /*System.out.println("in");
        while (!digitsOfNum_1.empty()) {
            System.out.print(digitsOfNum_1.pop());
        }
        System.out.println();*/

        long reversedNum = 0;
        int index = 0;//乘方操作的指数
        while (!digitsOfNum_1.empty()) {
            int top = digitsOfNum_1.pop();
            reversedNum = top * ((long) Math.pow(10, index)) + reversedNum;
            index++;
        }
        if (reversedNum > Integer.MAX_VALUE || reversedNum < Integer.MIN_VALUE) {
            return 0;
        }

        return (int) reversedNum;
    }

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
