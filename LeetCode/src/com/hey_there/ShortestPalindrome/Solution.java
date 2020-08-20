package com.hey_there.ShortestPalindrome;

public class Solution {
    public String shortestPalindrome(String s) {
        //将s反转
        String rev = new StringBuilder().append(s).reverse().toString();
        int len_s = s.length();
        for (int i = 0; i < len_s; i++) {
            //取s的前缀和rev的后缀，比较二者是否相等
            String prefix_s = s.substring(0, len_s - i);
            String suffix_rev = rev.substring(i);
            //二者相等时，prefix_s是s的最长回文前缀
            if (prefix_s.equals(suffix_rev)) {
                return rev.substring(0, i) + s;
            }
        }
        return "";
    }
}
