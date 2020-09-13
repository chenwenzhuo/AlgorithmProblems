package com.hey_there.iQIYI_September_13th;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Problem_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strArr = sc.nextLine().split(" ");
        sc.close();

        int[] nums = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            nums[i] = Integer.parseInt(strArr[i]);
        }
        strArr = null;

        Arrays.sort(nums);
        int length = nums.length;
        LinkedList<LinkedList<Integer>> res = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) break;

            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1, right = length - 1;
            while (left < right) {
                int threeSum = nums[i] + nums[left] + nums[right];
                if (threeSum == 0) {
                    LinkedList<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    res.add(list);

                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (threeSum < 0) left++;
                else right--;
            }
        }
        for (LinkedList<Integer> list : res) {
            for (int n : list) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
