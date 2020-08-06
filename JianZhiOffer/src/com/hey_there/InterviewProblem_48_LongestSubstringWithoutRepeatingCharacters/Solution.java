package com.hey_there.InterviewProblem_48_LongestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) return 0;
        char[] chs = s.toCharArray();
        //map中存储各个字符及字符在s中的下标
        HashMap<Character, Integer> map = new HashMap<>();
        int longest = 0;
        int smallestIdx = 0;//当前无重复字符的子串的第一个字符在s中的下标
        for (int i = 0; i < chs.length; i++) {
            if (!map.containsKey(chs[i])) {//遇到不重复字符，直接存入
                map.put(chs[i], i);
                continue;
            }
            //遇到重复字符，尝试更新最长子串值
            longest = Math.max(longest, map.keySet().size());
            int lastIdx = map.get(chs[i]);//当前字符chs[i]上一次出现时的下标
            //将map中位于lastIdx之前的字符全部删除
            for (int j = lastIdx - 1; j >= 0 && j >= smallestIdx; j--) {
                map.remove(chs[j]);
            }
            smallestIdx = lastIdx + 1;//更新smallestIdx的值
            map.put(chs[i], i);//更新chs[i]的下标
        }
        return Math.max(longest, map.keySet().size());
    }

    public static void main(String[] args) {
        String s = "cdasefdqw";
        //String s = "abba";
        Solution solution = new Solution();
        int longest = solution.lengthOfLongestSubstring(s);
        System.out.println(longest);
    }
}
