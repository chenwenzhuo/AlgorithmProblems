package com.hey_there.PalindromePartitioning;

import java.util.*;

public class Solution_1 {
    private List<List<String>> ans;
    private String s;
    private int lenS;
    private boolean[][] dp;

    public List<List<String>> partition(String s) {
        this.ans = new ArrayList<>();
        this.s = s;
        this.lenS = s.length();
        this.dp = new boolean[lenS][lenS];

        //对所有子串进行检查，判断其是否是回文
        char[] chS = s.toCharArray();
        for (int right = 0; right < lenS; right++) {
            for (int left = 0; left <= right; left++) {
                if (chS[left] == chS[right] && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }

        backtrack(new LinkedList<>(), 0);
        return ans;
    }

    private void backtrack(LinkedList<String> substrings, int startPos) {
        if (startPos >= lenS) {
            ans.add(new ArrayList<>(substrings));
            return;
        }

        for (int i = startPos + 1; i <= lenS; i++) {
            String cur = s.substring(startPos, i);
            if (dp[startPos][i - 1]) {
                substrings.add(cur);
                backtrack(substrings, i);
                substrings.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        String s = "abbab";
        Solution_1 solution1 = new Solution_1();
        List<List<String>> ans = solution1.partition(s);
        System.out.println(ans);
    }
}
