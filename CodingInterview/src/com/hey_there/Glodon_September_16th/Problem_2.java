package com.hey_there.Glodon_September_16th;

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
}
