package com.hey_there.DailyProblems.March.ShortEncodingOfWords;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int minimumLengthEncoding(String[] words) {
        Set<String> strSet = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            int lenWord = word.length();
            //由于是取后缀，所以start从1开始（start为0时取的是整个单词）
            for (int start = 1; start < lenWord; start++) {
                strSet.remove(word.substring(start));
            }
        }

        int lenCompressedStr = 0;
        for (String str : strSet) {
            //加上字符串的长度和一个#号的长度
            lenCompressedStr += (str.length() + 1);
        }
        return lenCompressedStr;
    }

    public static void main(String[] args) {
        String[] words = {"time", "atime", "btime"};
        Solution solution = new Solution();
        System.out.println(solution.minimumLengthEncoding(words));
    }
}
