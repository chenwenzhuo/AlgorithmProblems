package com.hey_there.WordBreak_2;

import java.util.*;

public class Solution1 {
    private HashMap<Integer, List<String>> map = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordDictSet = new HashSet<>(wordDict);
        return word_Break(s, wordDictSet, 0);
    }

    public List<String> word_Break(String s, Set<String> wordDictSet, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDictSet.contains(s.substring(start, end))) {
                List<String> list = word_Break(s, wordDictSet, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        map.put(start, res);
        return res;
    }

    public static void main(String[] args) {
        /*String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";*/
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = new ArrayList<String>() {{
            add("a");
            add("aa");
            add("aaa");
            add("aaaa");
            add("aaaaa");
            add("aaaaaa");
            add("aaaaaaa");
            add("aaaaaaaa");
            add("aaaaaaaaa");
            add("aaaaaaaaaa");
        }};
        Solution1 solution = new Solution1();
        List<String> ans = solution.wordBreak(s, wordDict);
        System.out.println(ans);
    }
}
