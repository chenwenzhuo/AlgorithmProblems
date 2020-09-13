package com.hey_there.iQIYI_September_13th;

import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strArr = sc.nextLine().split(" ");
        sc.close();

        int[] nums = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            nums[i] = Integer.parseInt(strArr[i]);
        }
        strArr = null;

        int count=0;
        Integer res=null;
        for (int n : nums) {
            if (count==0)res=n;
            count += (n == res ? 1 : -1);
        }
        System.out.println(res);
    }
}
