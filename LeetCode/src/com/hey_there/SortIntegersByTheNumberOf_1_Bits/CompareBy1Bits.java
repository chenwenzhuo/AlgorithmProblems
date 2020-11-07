package com.hey_there.SortIntegersByTheNumberOf_1_Bits;

import java.util.Comparator;

public class CompareBy1Bits implements Comparator<Integer> {
    @Override
    public int compare(Integer n1, Integer n2) {
        int n1_1Bits = count1Bits(n1);
        int n2_1Bits = count1Bits(n2);
        return n1_1Bits - n2_1Bits;
    }

    private int count1Bits(int num) {
        int counter = 0;
        while (num > 0) {
            if (num % 2 == 1) counter++;
            num /= 2;
        }
        return counter;
    }
}
