package com.hey_there.NetEasy_August_8th;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arrLen = Integer.parseInt(in.nextLine());//数组长度
        System.out.println(arrLen);
        //先以字符串的形式读入数组元素
        String str = in.nextLine();
        String[] strArr = str.split(" ");
        System.out.println(str);
        //将字符串数组转为整数数组
        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }

        long countPrimes = 0;
        //用一个list存储已找到的素数
        ArrayList<Integer> primes = new ArrayList<>();
        int listIdx = 0;

        for (int i = 0; i < arrLen; i++) {
            if (arr[i] == 1) continue;
            countPrimes += arr[i] / 2;
        }
        System.out.println(countPrimes);
    }

    //计算大于n的最小素数
    private static int calcNextPrime(int n) {
        int nextPrime = -1;//初值赋为“无效”值
        n++;
        while (true) {
            if (isPrime(n)) {
                nextPrime = n;
                break;
            }
        }
        return nextPrime;
    }

    //判断参数n是否为素数
    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        int sqrt_n = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt_n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
