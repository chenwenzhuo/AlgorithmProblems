package com.hey_there.IsomorphicStrings;

import java.util.HashMap;

public class Solution {
    public boolean isIsomorphic_1(String s, String t) {
        //同构字符串必须满足字符间可以一一对应，s->t和t->s两个方向都不能出现一对多的情况
        //用两个map分别存储两个方向的映射
        HashMap<Character, Character> s_t_map = new HashMap<>();
        HashMap<Character, Character> t_s_map = new HashMap<>();
        int strLen = s.length();
        char[] sChArr = s.toCharArray();
        char[] tChArr = t.toCharArray();
        for (int i = 0; i < strLen; i++) {
            /* 检查s的当前字符是否出现过，
             * 若出现过则检查之前存储的映射和当前t中的字符是否相同
             * 未出现过则存储两字符间的映射*/
            if (s_t_map.containsKey(sChArr[i])) {
                char replacement = s_t_map.get(sChArr[i]);
                if (replacement != tChArr[i]) {
                    return false;
                }
            } else {
                s_t_map.put(sChArr[i], tChArr[i]);
            }
            /* 检查t的当前字符是否出现过，
             * 若出现过则检查之前存储的映射和当前s中的字符是否相同
             * 未出现过则存储两字符间的映射*/
            if (t_s_map.containsKey(tChArr[i])) {
                char replacement = t_s_map.get(tChArr[i]);
                if (replacement != sChArr[i]) {
                    return false;
                }
            } else {
                t_s_map.put(tChArr[i], sChArr[i]);
            }
        }
        return true;
    }
}
