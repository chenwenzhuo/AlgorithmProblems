package com.hey_there.PalindromePartitioning;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution_2 {
    private List<List<String>> ans;
    private String s;
    private int lenS;

    public List<List<String>> partition(String s) {
        this.ans = new ArrayList<>();
        this.s = s;
        this.lenS = s.length();

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
            if (isPalindrome(cur)) {
                substrings.add(cur);
                backtrack(substrings, i);
                substrings.removeLast();
            }
        }
    }

    //判断参数字符串是否是回文串
    private boolean isPalindrome(String s) {
        char[] chS = s.toCharArray();
        int left = 0, right = chS.length - 1;
        while (left < right) {
            if (chS[left] != chS[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
