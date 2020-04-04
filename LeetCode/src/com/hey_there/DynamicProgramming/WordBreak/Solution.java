package com.hey_there.DynamicProgramming.WordBreak;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private String string;
    private List<String> dict;

    public boolean wordBreak_recursive(String s, List<String> wordDict) {
        this.string = s;
        this.dict = wordDict;
        return wordBreak_recursive(0, s.length() - 1);
    }

    private boolean wordBreak_recursive(int start, int end) {
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
                boolean canTailBreak = wordBreak_recursive(start + i, end);
                if (canTailBreak) {
                    return true;
                }
            }
        }
        //若在循环中未能找到一种方式分割成单词，则当前范围内的子串不能分割成单词
        return false;
    }

    public boolean wordBreak_dp_my(String s, List<String> wordDict) {
        //使用HashSet代替list
        Set<String> wordsSet = new HashSet<>(wordDict);
        int lenS = s.length();

        //dp[i][j]表示s中i到j区间内的子串能否拆分为单词
        boolean[][] dp = new boolean[lenS][lenS];
        //初始化基础条件,即s的所有长度为1的子串是否在wordsSet中
        for (int i = 0; i < lenS; i++) {
            dp[i][i] = wordsSet.contains(s.substring(i, i + 1));
        }
        //枚举所有长度的子串
        for (int lenSubStr = 2; lenSubStr <= lenS; lenSubStr++) {
            //枚举子串的起始下标
            int beginIndexMax = lenS - lenSubStr;//使子串不超过s的范围的最大beginIndex
            for (int beginIndex = 0; beginIndex <= beginIndexMax; beginIndex++) {
                String subStr = s.substring(beginIndex, beginIndex + lenSubStr);
                //先查看当前子串本身是否是单词，当前子串是单词则无需继续拆分
                dp[beginIndex][beginIndex + lenSubStr - 1] = wordsSet.contains(subStr);
                if (dp[beginIndex][beginIndex + lenSubStr - 1]) {
                    continue;
                }

                //枚举子串中所有划分位置
                int dividePosMax = beginIndex + lenSubStr - 2;//使分割后两侧子串均非空的最大dividePos
                for (int dividePos = beginIndex; dividePos <= dividePosMax; dividePos++) {
                    dp[beginIndex][beginIndex + lenSubStr - 1] =
                            dp[beginIndex][dividePos] && dp[dividePos + 1][beginIndex + lenSubStr - 1];
                    //找到一个位置能将当前子串划分为单词则直接退出
                    if (dp[beginIndex][beginIndex + lenSubStr - 1]) {
                        break;
                    }
                }
            }
        }
        return dp[0][lenS - 1];
    }

    public boolean wordBreak_dp(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String str = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");

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

        /*String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
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
        wordDict.add("aaaaaaaaaa");*/
        Solution solution = new Solution();

        long startTime = System.currentTimeMillis();
        System.out.println(solution.wordBreak_dp_my(str, wordDict));
        long endTime = System.currentTimeMillis();
        System.out.println("running time: " + (endTime - startTime));
    }
}
