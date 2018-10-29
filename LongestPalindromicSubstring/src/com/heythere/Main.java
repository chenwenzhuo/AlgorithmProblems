package com.heythere;

public class Main {

    public static void main(String[] args) {
        Solutions solutions = new Solutions();
        String str = "abcdcba";
        String str1 = "cbbd";
        //System.out.println("最长回文子串：" + solutions.longestPalindrome_DynamicProgramming(str));
        System.out.println(solutions.longestPalindrome_DynamicProgramming(str1));
    }
}
