package com.hey_there._942_DIStringMatch;

public class Solution {
    public int[] diStringMatch(String s) {
        int len = s.length();
        int low = 0, high = len;
        int[] res = new int[len + 1];
        for (int i = 0; i < len; i++)
            res[i] = s.charAt(i) == 'I' ? low++ : high--;
        res[len] = low;
        return res;
    }
}
