package com.hey_there._1759_CountNumberOfHomogenousSubstrings;

public class Solution {
    public int countHomogenous(String s) {
        long res = 0;
        int left = 0, right = 0;
        final long MOD = 1000000007;
        while (right < s.length()) {
            //移动right，直到right位置的字符与left不同
            while (right < s.length() && s.charAt(left) == s.charAt(right))
                right++;
            long curLen = right - left;//相同字符构成的连续子串的长度
            res = res + (curLen * (curLen + 1) / 2) % MOD;
            left = right;
        }
        return (int)res;
    }
}
