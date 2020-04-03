package com.hey_there.DynamicProgramming.WordBreak;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private String string;
    private List<String> dict;

    public boolean wordBreak(String s, List<String> wordDict) {
        this.string = s;
        this.dict = wordDict;
        return canBreak(0, s.length() - 1);
    }

    private boolean canBreak(int start, int end) {
        if (start > end) {
            return false;
        }
        if (start == end) {
            return dict.contains(string.substring(start, start + 1));
        }
        //获取当前范围内的子串
        String wholeSubStr = string.substring(start, end + 1);
        if (dict.contains(wholeSubStr)) {
            return true;//若当前整个子串是一个单词，则无需分割，返回true
        }
        //当前范围内的子串不是一个单词则需进行分割
        for (int i = 1; i < end - start + 1; i++) {
            //获取一个子串，包含从start位置开始的i个字符
            String curSubStr = string.substring(start, start + i);
            //若当前子串是一个单词，递归判断剩余部分是否能分成单词
            if (dict.contains(curSubStr)) {
                boolean canTailBreak = canBreak(start + i, end);
                if (canTailBreak) {
                    return true;
                }
            }
        }
        //若在循环中未能找到一种方式分割成单词，则当前范围内的子串不能分割成单词
        return false;
    }

    public static void main(String[] args) {
        /*String str = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");*/

        /*String str = "catsandog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");*/

        /*String str = "ab";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("a");
        wordDict.add("b");*/

        String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("a");
        wordDict.add("aa");
        wordDict.add("aaa");
        wordDict.add("aaaa");
        wordDict.add("aaaaa");
        wordDict.add("aaaaaa");
        wordDict.add("aaaaaaa");
        wordDict.add("aaaaaaaa");
        wordDict.add("aaaaaaaaa");
        wordDict.add("aaaaaaaaaa");
        Solution solution = new Solution();

        long startTime = System.currentTimeMillis();
        System.out.println(solution.wordBreak(str, wordDict));
        long endTime = System.currentTimeMillis();
        System.out.println("running time: " + (startTime - endTime) / 1000);
    }
}
