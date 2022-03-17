package com.hey_there._720_LongestWordInDictionary;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/* 题目描述不够清楚
 * 从官方题解倒推，符合要求的单词必须从单个字母逐步变长，
 * 而不能由一个较长的单词添加字母得到。
 * 示例 ["a", "banana", "app", "appl", "ap", "apply", "apple"] 返回"apple"，
 * 因为"a"，"ap"，"app"，"appl"，"apple"都存在于数组中且"apple"字典序比"apply"靠前。
 * 示例 ["a", "banana", "bananas", "app", "appl", "ap", "apply", "apple"] 也返回"apple"，
 * 因为虽然最长的"bananas"可由"banana"添加字母得到，但"banan"不存在于数组中，故"bananas"和"banana"都不是合法字符串。*/
public class Solution {
    public String longestWord(String[] words) {
        /* 将字符串数组排序，长度不同时较短者在前，长度相同时字典序靠后的在前。
         * 使得始终能找到最长的合法字符串，有多个长度相同但合法字符串时，
         * 字典序靠前的最后被找到，longest_word保留的是字典序最靠前的字符串。*/
        Arrays.sort(words, (s1, s2) -> {
            if (s1.length() != s2.length())
                return s1.length() - s2.length();
            return s2.compareTo(s1);
        });
        HashSet<String> wSet = new HashSet<>();
        wSet.add("");//要从单个字母开始，故集合中必须添加空字符串
        String longest_word = "";
        for (String word : words) {
            if (wSet.contains(word.substring(0, word.length() - 1))) {
                wSet.add(word);
                longest_word = word;
            }
        }
        return longest_word;
    }

    public static void main(String[] args) {
        String[] words = {"a", "bananas", "banana", "app", "appl", "ap", "apply", "apple"};
        Solution solution = new Solution();
        String res = solution.longestWord(words);
        System.out.println(res);
    }
}
