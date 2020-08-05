package com.hey_there.InterviewProblem_45_ArrangeArrayIntoSmallestNumber;

import java.util.ArrayList;

public class Solution {
    public String minNumber(int[] nums) {
        ArrayList<String> list = new ArrayList<>(nums.length);
        for (int n : nums) {
            list.add(String.valueOf(n));
        }
        list.sort((a, b) -> {
            String ab = a + b;
            String ba = b + a;
            return ab.compareTo(ba);
        });
        StringBuilder numBuilder = new StringBuilder();
        for (String n : list) {
            numBuilder.append(n);
        }
        return numBuilder.toString();
    }
}
