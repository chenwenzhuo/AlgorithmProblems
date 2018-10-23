package com.heythere;

public class Main {

    public static void main(String[] args) {
        Solutions solutions = new Solutions();
        String str = "abcdefgfedcba";
        String str1 = "aaaa";
        System.out.println(solutions.longestPalindrome(str));
        System.out.println(solutions.longestPalindrome(str1));
    }
}
