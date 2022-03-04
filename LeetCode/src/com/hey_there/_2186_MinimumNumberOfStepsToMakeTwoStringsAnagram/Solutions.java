package com.hey_there._2186_MinimumNumberOfStepsToMakeTwoStringsAnagram;

import java.util.HashMap;

public class Solutions {
    public int minSteps_1(String s, String t) {
        //用map记录s中字母及每个字母的出现次数
        HashMap<Character, Integer> sMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            sMap.put(curChar, sMap.getOrDefault(curChar, 0) + 1);
        }
        //用map记录t中独有字母的出现次数
        HashMap<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char curChar = t.charAt(i);
            if (sMap.containsKey(curChar))//找到共有字符，将sMap中记录的字符数减1
                sMap.put(curChar, sMap.get(curChar) - 1);
            else //非共有字符，放入tMap中
                tMap.put(curChar, tMap.getOrDefault(curChar, 0) + 1);
        }
        int steps = 0;
        for (int val : sMap.values()) {
            steps += Math.abs(val);
        }
        for (int val : tMap.values()) {
            steps += val;
        }
        return steps;
    }

    public int minSteps_2(String s, String t) {
        //s和t均只包含小写英文字母，用数组记录每个字母出现次数
        int[] letterCounts = new int[26];
        for (int i = 0; i < s.length(); i++) {//枚举s中每个字母，记录出现次数
            int idx = (int) s.charAt(i) - 'a';
            letterCounts[idx]++;
        }
        /* 枚举t中字母，将出现次数减1
         * 对于共有字母，出现次数抵消，但不影响s独有字母的次数
         * 对于t独有字母，出现次数为负*/
        for (int i = 0; i < t.length(); i++) {
            int idx = (int) t.charAt(i) - 'a';
            letterCounts[idx]--;
        }
        int steps = 0;
        for (int n : letterCounts) {
            steps += Math.abs(n);//将数组中每个值的绝对值累加
        }
        return steps;
    }
}
