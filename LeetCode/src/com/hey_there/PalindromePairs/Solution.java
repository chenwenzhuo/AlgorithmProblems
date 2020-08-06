package com.hey_there.PalindromePairs;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    //在words数组长度较大时会超时
    public List<List<Integer>> palindromePairs(String[] words) {
        int numWords = words.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numWords; i++) {
            StringBuilder builder_1 = new StringBuilder();
            builder_1.append(words[i]);
            StringBuilder builder_2 = new StringBuilder();
            builder_2.append(words[i]);
            for (int j = i + 1; j < numWords; j++) {
                //以两种方式拼接words[i]和words[j]，检查其是否回文
                builder_1.append(words[j]);//words[i] + words[j]
                String original = builder_1.toString();//拼接后的原字符串
                String reversed = builder_1.reverse().toString();//反转后的字符串
                if (original.equals(reversed)) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(i);
                    pair.add(j);
                    res.add(pair);
                }
                builder_2.insert(0, words[j]);//words[j] + words[i]
                original = builder_2.toString();
                reversed = builder_2.reverse().toString();
                if (original.equals(reversed)) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(j);
                    pair.add(i);
                    res.add(pair);
                }
                //将两个builder复位（删除插入的words[j]）
                builder_1.reverse().delete(words[i].length(), builder_1.length());
                builder_2.reverse().delete(0, words[j].length());
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};

        Solution solution = new Solution();
        List<List<Integer>> pairs = solution.palindromePairs(words);
        System.out.println(pairs);
    }
}
