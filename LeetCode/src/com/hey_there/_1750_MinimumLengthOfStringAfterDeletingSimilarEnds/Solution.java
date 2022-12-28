package com.hey_there._1750_MinimumLengthOfStringAfterDeletingSimilarEnds;

public class Solution {
    public int minimumLength(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            //left和right指向的字符不同，无法移除
            if (s.charAt(left) != s.charAt(right))
                break;
            char c = s.charAt(left);
            //尝试移动left和right，直到指向的字符改变
            while (left <= right && s.charAt(left) == c)
                left++;
            while (left <= right && s.charAt(right) == c)
                right--;
        }
        return right - left + 1;
    }

    public static void main(String[] args) {
        String s = "cccccccccccccccccccccccccccccccccccccccccccccccccccccccccbbbbbbbbbbbbbbbbbbbaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaabbbbbbbbbbbbbbbbcccccccbcaabccbacaabcbaccaacccaabbcaabbbbcaccbbcbbbbbabcacbccbaaaccaa" +
                "aabcacbccbbcaabccacbccbbcaaaccaaccbbcaabaabbcaccaabbcbaacbaccbaaabccbaabcacbabcabbccbacaabbcaacaaa" +
                "caaacbabbcabccbbacaabacabcacacbcacaabbabcbaaaccccacbbabcbccbaaccbbbabbbbaabcccaabaacccccccbbabcbcbc" +
                "bcbcbbbbccbbaaccaaaaccacabbaccbbabccaacbcbccaabcacacacaaabbbaaccccaccaabcabbabacacbbaacbcabbbcacccca" +
                "cccbcaccccccccbbcccbbaabbcbcaabcccbabcbcbccacaccbcaacbaaaaaababbaaccbcccaccccababcccacbccbbacabcbbbcc" +
                "bcababbaaaacaabccbaaacbacbcaababbcbacccacaccbabcabbccaccacbccaaccabbabcbbccaabccaacabbaabccacbabcaaba" +
                "bccbcaacababbabcacccaaabcaabcbbbbabcbccbbbbbbbbbbaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbb" +
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb" +
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbcccccccccc";
        Solution solution = new Solution();
        int res = solution.minimumLength(s);
        System.out.println(res);
    }
}
