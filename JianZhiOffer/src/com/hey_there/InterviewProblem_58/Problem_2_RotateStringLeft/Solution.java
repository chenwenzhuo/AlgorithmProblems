package com.hey_there.InterviewProblem_58.Problem_2_RotateStringLeft;

public class Solution {
    public String reverseLeftWords(String s, int n) {
        String substr1 = s.substring(0, n);
        String substr2 = s.substring(n);
        return substr2 + substr1;
    }
}
