package com.hey_there.Gaotuketang_September_5th;

import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int[] nums = new int[line.length];
        for (int i = 0; i < line.length; i++) {
            nums[i] = Integer.parseInt(line[i]);
        }
        int target = Integer.parseInt(sc.nextLine());

        if (target <= nums[0]) {
            System.out.println(0);
            return;
        }
        if (target > nums[nums.length - 1]) {
            System.out.println(nums.length);
            return;
        }

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                System.out.println(mid);
                return;
            }
        }
        if (nums[left] >= target) {
            System.out.println(left);
        } else if (nums[left] < target) {
            System.out.println(left + 1);
        }
    }
}
