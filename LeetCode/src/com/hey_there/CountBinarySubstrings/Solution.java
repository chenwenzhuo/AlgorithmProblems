package com.hey_there.CountBinarySubstrings;

import java.util.*;

public class Solution {
    public int countBinarySubstrings_1(String s) {
        //map中存储连续的由相同字符（0或1）构成的子串的起止下标
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();

        char[] chs = s.toCharArray();
        int begin = -1;
        int idx = 0;
        while (idx < chs.length) {
            //begin为-1，表示开始找一个新的子串，设置begin的值，继续遍历
            if (begin == -1) {
                begin = idx;
                idx++;
                continue;
            }
            //当前字符与前一字符相同时
            if (chs[idx] == chs[idx - 1]) {
                //检查当前chs[idx]是否是s的最后一个字符
                //是最后一个字符时，直接将起止坐标存入map
                if (idx == chs.length - 1)
                    map.put(begin, idx);
                idx++;
                continue;
            }
            //当前字符与前一字符不同，则结束寻找当前子串，将起止坐标存入map
            map.put(begin, idx - 1);
            begin = -1;//重置begin的值为-1
            //检查当前chs[idx]是否是s的最后一个字符
            //是最后一个字符时，表示最后一个子串仅有一个字符
            if (idx == chs.length - 1) {
                map.put(idx, idx);
                break;
            }
        }
        if (map.size() <= 1) return 0;

        int count = 0;
        ArrayDeque<Map.Entry<Integer, Integer>> list = new ArrayDeque<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (list.size() < 2) {
                list.offer(entry);
                continue;
            }
            Map.Entry<Integer, Integer> pair1 = list.poll();
            Map.Entry<Integer, Integer> pair2 = list.getLast();
            int range1 = pair1.getValue() - pair1.getKey() + 1;
            int range2 = pair2.getValue() - pair2.getKey() + 1;
            count += Math.min(range1, range2);

            list.offer(entry);
        }
        Map.Entry<Integer, Integer> pair1 = list.poll();
        Map.Entry<Integer, Integer> pair2 = list.getLast();
        int range1 = pair1.getValue() - pair1.getKey() + 1;
        int range2 = pair2.getValue() - pair2.getKey() + 1;
        count += Math.min(range1, range2);
        return count;
    }

    public int countBinarySubstrings_2(String s) {
        if (s == null || s.length() <= 1) return 0;

        char[] chs = s.toCharArray();
        //list中存储连续的由相同字符（0或1）构成的子串的长度
        ArrayList<Integer> list = new ArrayList<>(chs.length);
        int substrLen = 1;
        for (int i = 1; i < chs.length; i++) {
            //当前字符与前一个相同时
            if (chs[i] == chs[i - 1]) {
                substrLen++;
                //检查当前chs[i]是否是s的最后一个字符，若是，则结束累加
                if (i == chs.length - 1) list.add(substrLen);
                continue;
            }
            //当前字符与前一个不同，结束此次累加
            list.add(substrLen);
            substrLen = 1;
            if (i == chs.length - 1) list.add(substrLen);
        }
        System.out.println("list: " + list);

        int count = 0;
        int listSize = list.size();
        int prev = list.get(0);
        for (int i = 1; i < listSize; i++) {
            int cur = list.get(i);
            count += Math.min(prev, cur);
            prev = cur;
        }
        return count;
    }

    public int countBinarySubstrings_3(String s) {
        if (s == null || s.length() <= 1) return 0;
        char[] chs = s.toCharArray();
        //数组每个元素表示连续的由相同字符（0或1）构成的子串的长度
        int[] lensOfSubstr = new int[chs.length];
        int countSubstr = 0;//记录连续的由相同字符（0或1）构成的子串的数量
        int tmpLen = 1;
        for (int i = 1; i < chs.length; i++) {
            //当前字符与前一个相同时
            if (chs[i] == chs[i - 1]) {
                tmpLen++;
                //检查当前chs[i]是否是s的最后一个字符，若是，则结束累加子串长度
                if (i == chs.length - 1) {
                    lensOfSubstr[countSubstr] = tmpLen;//记录子串长度
                    countSubstr++;//累加子串数量
                }
                continue;
            }
            //当前字符与前一个不同，结束此次累加
            lensOfSubstr[countSubstr] = tmpLen;//记录子串长度
            countSubstr++;//累加子串数量
            tmpLen = 1;
            if (i == chs.length - 1) {
                lensOfSubstr[countSubstr] = tmpLen;//记录子串长度
                countSubstr++;//累加子串数量
            }
        }

        int count = 0;
        for (int i = 1; i < countSubstr; i++) {
            int prev = lensOfSubstr[i - 1];
            int cur = lensOfSubstr[i];
            count += Math.min(prev, cur);
        }
        return count;
    }

    public static void main(String[] args) {
        //String s = "00110011";
        //String s = "10101";
        String s = "000111000";
        Solution solution = new Solution();
        int count = solution.countBinarySubstrings_2(s);
        System.out.println(count);
    }
}
