package com.heythere;

public class Main {

    public static void main(String[] args) {
        Solutions solutions = new Solutions();
        String str = "abcdcba";
        String str1 = "aaaa";
        System.out.println(solutions.longestPalindrome_CenterExpanding(str));
        System.out.println(solutions.longestPalindrome_CenterExpanding(str1));
    }
}
