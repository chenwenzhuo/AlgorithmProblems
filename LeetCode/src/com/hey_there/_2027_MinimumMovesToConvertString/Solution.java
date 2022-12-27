package com.hey_there._2027_MinimumMovesToConvertString;

public class Solution {
    public int minimumMoves(String s) {
        int moves = 0, idx = 0;
        while (idx < s.length()) {
            char c = s.charAt(idx);
            if (c == 'O') {
                idx++;
                continue;
            }
            //c为字符'X'时：
            moves++;
            idx += 3;
        }
        return moves;
    }
}
