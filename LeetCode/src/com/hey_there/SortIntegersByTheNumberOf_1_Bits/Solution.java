package com.hey_there.SortIntegersByTheNumberOf_1_Bits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class Solution {
    public int[] sortByBits_1(int[] arr) {
        Arrays.sort(arr);
        /* map的：
         * key表示数字中 1bit 的数量
         * value表示有 key 个 1bit 的所有数字*/
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int most1Bits = 0;
        for (int num : arr) {//遍历数组arr中所有数字
            //计算当前数字中 1bit 的数量
            int count1Bits = 0;
            int n = num;
            while (n > 0) {
                if (n % 2 == 1) count1Bits++;
                n /= 2;
            }
            most1Bits = Math.max(most1Bits, count1Bits);

            //检查map中有没有值为count1Bits的key
            if (map.containsKey(count1Bits)) {
                map.get(count1Bits).add(num);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(num);
                map.put(count1Bits, list);
            }
        }

        int[] res = new int[arr.length];
        int resIdx = 0;
        for (int i = 0; i <= most1Bits; i++) {
            if (!map.containsKey(i)) continue;
            ArrayList<Integer> list = map.get(i);
            for (int n : list) res[resIdx++] = n;
        }
        return res;
    }

    public int[] sortByBits_2(int[] arr) {
        int[] bit = new int[10001];
        Arrays.fill(bit, -1);

        Integer[] arrInteger = new Integer[arr.length];
        int idx = 0;
        for (int n : arr) {
            arrInteger[idx++] = n;
        }
        Arrays.sort(arrInteger, new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                int n1_1Bits = bit[n1] >= 0 ? bit[n1] : count1Bits(n1);
                int n2_1Bits = bit[n2] >= 0 ? bit[n2] : count1Bits(n2);
                bit[n1] = n1_1Bits;
                bit[n2] = n2_1Bits;
                if (n1_1Bits != n2_1Bits) return n1_1Bits - n2_1Bits;
                else return n1 - n2;
            }
        });
        idx = 0;
        for (int n : arrInteger) {
            arr[idx++] = n;
        }
        return arr;
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
