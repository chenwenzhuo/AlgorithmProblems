package com.hey_there._258_AddDigits;

public class Solutions {
    public int addDigits_1(int num) {
        while (num >= 10) {
            int temp = 0;
            while (num > 0) {
                temp += num % 10;
                num /= 10;
            }
            num = temp;
        }
        return num;
    }

    public int addDigits_2(int num) {
        return (num - 1) % 9 + 1;
    }
}
