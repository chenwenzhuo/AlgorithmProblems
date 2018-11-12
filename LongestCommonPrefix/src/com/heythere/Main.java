package com.heythere;

public class Main {

    public static void main(String[] args) {
        Solutions solutions = new Solutions();
        String[] str_1 = {"good", "god", "go"};
        String[] str_2 = {"here", "there"};

        System.out.println(solutions.longestCommonPrefix(str_1));
        System.out.println(solutions.longestCommonPrefix(str_2));
    }
}
