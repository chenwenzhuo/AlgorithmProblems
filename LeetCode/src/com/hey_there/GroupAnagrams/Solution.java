package com.hey_there.GroupAnagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {//枚举数组中所有字符串
            char[] ch_s = s.toCharArray();//获取s的字符数组
            Arrays.sort(ch_s);//对字符数组排序
            String sorted_s = new String(ch_s);//使用排序的字符数组新建字符串

            if (map.containsKey(sorted_s)) {
                //map中有key值等于sorted_s，则将s加入对应的list集合
                map.get(sorted_s).add(s);
            } else {
                //map中没有key值等于sorted_s，
                //则新建list集合，将sorted_s和list的键值对加入map
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(sorted_s, list);
            }
        }
        //将map的value集合存入一个list集合
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        Solution solution = new Solution();
        List<List<String>> ans = solution.groupAnagrams(strs);
        System.out.println(ans);
    }
}
