package com.hey_there.InterviewProblem_50_TheFirstUniqueCharacter;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Solution {
    //使用LinkedHashMap实现
    public char firstUniqChar_1(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        int length = s.length();
        //将字符及其出现次数存储在map中
        for (int i = 0; i < length; i++) {
            char curCh = s.charAt(i);
            int numOccurrence = map.getOrDefault(curCh, 0);
            map.put(curCh, numOccurrence + 1);
        }
        char ans = ' ';//s为空串或仅包含小写字母，故令ans初值为一“无效”字符
        for (Character keyCh : map.keySet()) {
            if (map.get(keyCh) == 1) {
                ans = keyCh;
                break;
            }
        }
        return ans;
    }

    //使用HashMap实现
    public char firstUniqChar_2(String s) {
        HashMap<Character, Boolean> map = new HashMap<>();
        char[] chs = s.toCharArray();
        for (char c : chs)
            map.put(c, !map.containsKey(c));
        for (char c : chs) {
            if (map.get(c)) return c;
        }
        return ' ';
    }
}
