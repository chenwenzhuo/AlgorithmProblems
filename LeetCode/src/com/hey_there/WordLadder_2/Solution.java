package com.hey_there.WordLadder_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<String> wordList;
    private List<List<String>> ans = new ArrayList<>();
    private int shortest;
    private boolean[] searched;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //若wordList不包含endWord，直接返回空列表
        if (!wordList.contains(endWord)) {
            return new ArrayList<>();
        }
        int listSize = wordList.size();
        this.shortest = wordList.size() + 1;
        this.wordList = wordList;
        this.searched = new boolean[listSize];
        Arrays.fill(searched, false);
        //检查beginWord是否在wordList中
        for (int i = 0; i < listSize; i++) {
            if (wordList.get(i).equals(beginWord)) {
                searched[i] = true;
            }
        }
        List<String> temp_ans = new ArrayList<>();
        temp_ans.add(beginWord);
        backtrack(temp_ans, endWord);
        return ans;
    }

    private void backtrack(List<String> temp_ans, String endWord) {
        if (editDistanceIsOne(temp_ans.get(temp_ans.size() - 1), endWord)) {
            int temp_size = temp_ans.size();
            if (temp_size + 1 == shortest) {
                List<String> oneAns = new ArrayList<>(temp_ans);
                oneAns.add(endWord);
                ans.add(oneAns);
            } else if (temp_size + 1 < shortest) {
                ans.clear();
                List<String> oneAns = new ArrayList<>(temp_ans);
                oneAns.add(endWord);
                ans.add(oneAns);
                shortest = temp_size + 1;
            }
            return;
        }

        for (int i = 0; i < wordList.size(); i++) {
            if (searched[i]) {
                continue;
            }
            String curWord = wordList.get(i);
            if (editDistanceIsOne(temp_ans.get(temp_ans.size() - 1), curWord)) {
                temp_ans.add(curWord);
                searched[i] = true;
                backtrack(temp_ans, endWord);
                temp_ans.remove(curWord);
                searched[i] = false;
            }
        }
    }

    private boolean editDistanceIsOne(String word1, String word2) {
        char[] ch_word1 = word1.toCharArray();
        char[] ch_word2 = word2.toCharArray();
        int editDistance = 0;
        for (int i = 0; i < ch_word1.length; i++) {
            if (ch_word1[i] != ch_word2[i]) {
                if (editDistance == 0) {
                    editDistance++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");

        Solution solution = new Solution();
        List<List<String>> ans = solution.findLadders(beginWord, endWord, wordList);
        System.out.println(ans);
    }
}
