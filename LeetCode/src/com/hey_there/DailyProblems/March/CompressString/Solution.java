package com.hey_there.DailyProblems.March.CompressString;

public class Solution {
    public String compressString(String S) {
        StringBuilder compressedStrBuilder = new StringBuilder();
        int len = S.length();
        int count = 1;
        for (int i = 1; i <= len; i++) {
            if (i < len && S.charAt(i) == S.charAt(i - 1)) {
                count++;
                continue;
            }
            compressedStrBuilder.append(S.charAt(i - 1));
            compressedStrBuilder.append(count);
            count = 1;
        }
        return compressedStrBuilder.length() < len ?
                compressedStrBuilder.toString() : S;
    }
}
