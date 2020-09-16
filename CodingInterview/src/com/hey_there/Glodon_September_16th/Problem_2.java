package com.hey_there.Glodon_September_16th;

import java.math.BigInteger;
import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        long[] nums = new long[4];
        nums[0] = Long.parseLong(line[0]);
        nums[1] = Long.parseLong(line[1]);
        nums[2] = Long.parseLong(line[2]);
        nums[3] = Long.parseLong(line[3]);
        sc.close();
        line = null;

        long sumNums = nums[0] + nums[1] + nums[2] + nums[3];
        long left = Math.min(nums[0], Math.min(nums[1], Math.min(nums[2], nums[3])));
        long right = sumNums / 4;
        while (left < right) {
            long mid = left + (right - left) / 2 + 1;
            long less = 0;
            long greater = 0;
            for (long n : nums) {
                if (n < mid) less += mid - n;
                else if (n > mid) greater += n - mid;
            }
            if (less * 2 > greater) right = mid - 1;
            else left = mid;
        }
        System.out.println(right * 4);
    }
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        long[] nums = new long[4];
        nums[0] = Long.parseLong(line[0]);
        nums[1] = Long.parseLong(line[1]);
        nums[2] = Long.parseLong(line[2]);
        nums[3] = Long.parseLong(line[3]);
        sc.close();
        line = null;

        long sumNums = nums[0] + nums[1] + nums[2] + nums[3];
        long res = -1;
        for (long i = sumNums / 4; i >= 0; i--) {
            long add_num = 0;
            long minus_num = 0;
            for (long n : nums) {
                if (n < i) add_num += i - n;
                else if (n > i) minus_num += n - i;
            }
            if (minus_num == add_num * 2) {
                res = i * 4;
                break;
            }
        }
        System.out.println(res);
    }*/
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        BigInteger[] nums = new BigInteger[4];
        nums[0] = new BigInteger(line[0]);
        nums[1] = new BigInteger(line[1]);
        nums[2] = new BigInteger(line[2]);
        nums[3] = new BigInteger(line[3]);
        sc.close();
        line = null;

        BigInteger sumNums = nums[0].add(nums[1]).add(nums[2]).add(nums[3]);
        BigInteger res = BigInteger.valueOf(-1);
        BigInteger start = sumNums.divide(BigInteger.valueOf(4));

        while (start.compareTo(BigInteger.valueOf(0)) >= 0) {
            BigInteger add_num = BigInteger.valueOf(0);
            BigInteger minus_num = BigInteger.valueOf(0);
            for (BigInteger n : nums) {
                if (n.compareTo(start) < 0) {
                    add_num = add_num.add(start.subtract(n));
                } else if (n.compareTo(start) > 0) {
                    minus_num = minus_num.add(n.subtract(start));
                }
            }
            if (minus_num.compareTo(add_num.multiply(BigInteger.valueOf(2))) == 0) {
                res = start.multiply(BigInteger.valueOf(4));
                break;
            }

            start = start.subtract(BigInteger.valueOf(1));
        }
        System.out.println(res);
    }*/
}
