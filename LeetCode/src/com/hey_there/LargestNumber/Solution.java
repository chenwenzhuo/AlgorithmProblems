package com.hey_there.LargestNumber;

import java.util.ArrayList;
import java.util.Comparator;

public class Solution {
    public String largestNumber(int[] nums) {
        ArrayList<String> list = new ArrayList<>(nums.length);
        for (int n : nums) {
            list.add(String.valueOf(n));
        }
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String ab = a + b;
                String ba = b + a;
                return ab.compareTo(ba);
            }
        });
        System.out.println(list);

        StringBuilder builder = new StringBuilder();
        for (int i = nums.length - 1; i >= 0; i--) {
            builder.append(list.get(i));
        }
        //去除前导'0'字符
        int lenOfNum = builder.length();
        int idx = 0;
        while (idx < lenOfNum - 1 && builder.charAt(idx) == '0') {
            idx++;
        }
        builder.delete(0, idx);
        return builder.toString();
    }

    //检查数组chs中从下标begin开始的所有字符，是否全是'0'
    private boolean checkZero(char[] chs, int begin) {
        int idx = begin;
        while (idx < chs.length) {
            if (chs[idx] != '0') {
                return false;
            }
            idx++;
        }
        return true;
    }

    public static void main(String[] args) {
        //int[] nums = {3, 30, 34, 5, 9};
        int[] nums = {121, 12};
        //int[] nums = {0, 0, 0, 0, 0};
        Solution solution = new Solution();
        String largestNum = solution.largestNumber(nums);
        System.out.println(largestNum);
        /*List<String> list = new ArrayList<>();
        list.add("12121");
        list.add("12112");
        Collections.sort(list);
        System.out.println(list);*/
    }
}
