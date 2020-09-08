package com.hey_there.Xiaomi_September_8th;

import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] passwords = sc.nextLine().split(" ");

        for (String pwd : passwords) {
            if (pwd.length() < 8 || pwd.length() > 120) {
                System.out.println(1);
                continue;
            }

            char[] chsPwd = pwd.toCharArray();
            boolean containsNum = false;
            boolean containsChar = false;
            boolean containsUpperCase = false;
            boolean containsLowerCase = false;
            for (char ch : chsPwd) {
                if ('0' <= ch && ch <= '9' && !containsNum) containsNum = true;
                else if ('A' <= ch && ch <= 'Z' && !containsUpperCase) containsUpperCase = true;
                else if ('a' <= ch && ch <= 'z' && !containsLowerCase) containsLowerCase = true;
                else containsChar = true;
            }
            if (containsNum && containsChar && containsUpperCase && containsLowerCase) {
                System.out.println(0);
            } else {
                System.out.println(2);
            }
        }
    }
}
