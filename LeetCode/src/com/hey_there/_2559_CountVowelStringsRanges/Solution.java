package com.hey_there._2559_CountVowelStringsRanges;

import java.util.HashSet;

public class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int cnt_words = words.length;//单词数量
        int cnt_queries = queries.length;//查询数量
        //标志位，表示words[i]是否是元音字符串，1为是，0为否
        int[] wordsFlags = new int[cnt_words];
        HashSet<Character> set = new HashSet<>() {{
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
        }};
        for (int i = 0; i < cnt_words; i++) {//逐个检查字符串是否是元音字符串
            //取首尾字符
            char ch_st = words[i].charAt(0);
            char ch_ed = words[i].charAt(words[i].length() - 1);
            if (set.contains(ch_st) && set.contains(ch_ed))
                wordsFlags[i] = 1;
        }
        //计算wordsFlags的前缀和
        int[] prefixSum = new int[cnt_words];
        prefixSum[0] = wordsFlags[0];
        for (int i = 1; i < cnt_words; i++)
            prefixSum[i] = prefixSum[i - 1] + wordsFlags[i];

        int[] ans = new int[cnt_queries];
        for (int i = 0; i < cnt_queries; i++) {
            int start = queries[i][0], end = queries[i][1];
            //区间(start,end]中元音字符串数量
            int n = prefixSum[end] - prefixSum[start];
            if (wordsFlags[start] == 1)
                n++;//检查words[start]是否是元音字符串
            ans[i] = n;
        }
        return ans;
    }
}
