package com.hey_there.AskQuestions;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        line = sc.nextLine().split(" ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(line[i]);
        }

        int[][] asks = new int[m][3];
        for (int i = 0; i < m; i++) {
            line = sc.nextLine().split(" ");
            asks[i][0] = Integer.parseInt(line[0]);
            asks[i][1] = Integer.parseInt(line[1]);
            asks[i][2] = Integer.parseInt(line[2]);
        }

        for (int i = 0; i < m; i++) {
            long res;
            if (asks[i][0] == 1) res = askOdd(nums, asks[i][1] - 1, asks[i][2] - 1);
            else res = askEven(nums, asks[i][1] - 1, asks[i][2] - 1);
            System.out.println(res);
        }
    }

    public static long askOdd(int[] nums, int left, int right) {
        int oddsBetween = 0;
        //统计[left,right]闭区间内有多少奇数
        for (int i = left; i <= right; i++) {
            if (nums[i] % 2 == 1) oddsBetween++;
        }

        long sum = 0;
        for (int i = 1; i <= oddsBetween; i++) {
            sum += combination(oddsBetween, i);
            sum %= 1000000007;
        }
        return sum;
    }

    public static long askEven(int[] nums, int left, int right) {
        int oddsBetween = 0;
        //统计[left,right]闭区间内有多少奇数
        for (int i = left; i <= right; i++) {
            if (nums[i] % 2 == 1) oddsBetween++;
        }
        //[left,right]闭区间内偶数的数量
        int evenBetween = (right - left + 1) - oddsBetween;

        long sum = 0;
        for (int i = 1; i <= evenBetween; i++) {
            long evenComb = combination(evenBetween, i);
            for (int j = 0; j <= oddsBetween; j++) {
                long oddComb = combination(oddsBetween, j);
                sum += evenComb * oddComb;
                sum %= 1000000007;
            }
        }
        return sum;
    }

    //求组合数
    public static long combination(long n, long k) {
        long a = 1, b = 1;
        if (k > n / 2) {
            k = n - k;
        }
        for (int i = 1; i <= k; i++) {
            a *= (n + 1 - i);
            b *= i;
        }
        return a / b;
    }
}
