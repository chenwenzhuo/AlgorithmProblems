package com.hey_there.String.Implementing_strStr;

public class Solution {
    public int strStr(String haystack, String needle) {
        int len_needle = needle.length();
        if (len_needle == 0) {
            return 0;
        }

        int len_haystack = haystack.length();
        int left = 0, right = len_needle;
        int ans = -1;
        while (right <= len_haystack) {
            String subStr = haystack.substring(left, right);
            if (needle.equals(subStr)) {
                ans = left;
                break;
            }
            left++;
            right++;
        }
        return ans;
    }
}
