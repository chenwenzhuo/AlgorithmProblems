package com.hey_there.WordBreak_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Solution {
    private String s;
    private int len_s;
    private List<String> ans;//答案集合
    //dp[i][j]表示s的[i,j]范围内的子串是否是单词
    private boolean[][] dp;

    public List<String> wordBreak_my(String s, List<String> wordDict) {
        this.s = s;
        this.len_s = s.length();
        this.ans = new ArrayList<>();
        this.dp = new boolean[len_s][len_s];
        HashSet<String> wordDictSet = new HashSet<>(wordDict);
        //初始化dp数组的值
        for (int i = 0; i < len_s; i++) {
            for (int j = i; j < len_s; j++) {
                dp[i][j] = wordDictSet.contains(s.substring(i, j + 1));
            }
        }
        StringBuilder tmpAns = new StringBuilder();
        backtrack(0, tmpAns);
        return ans;
    }

    private void backtrack(int beginIdx, StringBuilder tmpAns) {
        if (beginIdx == len_s) {
            ans.add(tmpAns.toString());
            return;
        }
        int endIdx = beginIdx;
        int initLen_tempAns = tmpAns.length();
        while (endIdx < len_s) {
            //仅当[beginIdx,endIdx]范围内的子串是单词时进行操作
            if (dp[beginIdx][endIdx]) {
                //取子串加入tmpAns中
                if (initLen_tempAns > 0) {
                    tmpAns.append(" ");//仅当tmpAns非空时需要加入空格
                }
                tmpAns.append(s.substring(beginIdx, endIdx + 1));
                //递归向下
                backtrack(endIdx + 1, tmpAns);
                tmpAns.delete(initLen_tempAns, tmpAns.length());//删除前方加入的子串
            }
            endIdx++;
        }
    }

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
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
        Solution solution = new Solution();
        List<String> ans = solution.wordBreak_my(s, wordDict);
        System.out.println(ans);
    }
}
