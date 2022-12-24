package com.hey_there._1754_LargestMergeOfTwoStrings;

public class Solution {
    public String largestMerge(String word1, String word2) {
        int idx1 = 0, idx2 = 0;
        StringBuilder merge = new StringBuilder();
        while (idx1 < word1.length() || idx2 < word2.length()) {
            if (idx1 < word1.length() &&
                    word1.substring(idx1).compareTo(word2.substring(idx2)) > 0) {
                merge.append(word1.charAt(idx1));
                idx1++;
            } else {
                merge.append(word2.charAt(idx2));
                idx2++;
            }
        }
        return merge.toString();
    }
}
