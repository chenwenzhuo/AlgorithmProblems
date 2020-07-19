package com.hey_there.InterviewProblem_5_ReplaceSpaces;

public class Solution {
    public String replaceSpace(String s) {
        StringBuilder builder = new StringBuilder();
        int sLen = s.length();
        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                builder.append("%20");
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
