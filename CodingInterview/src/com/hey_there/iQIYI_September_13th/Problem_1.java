package com.hey_there.iQIYI_September_13th;

import java.util.HashSet;
import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        char[] chs = str.toCharArray();
        HashSet<Character> set = new HashSet<>();
        int slow = 0, fast = 0;
        int longest = 0;
        int lenStr = str.length();

        while (fast < lenStr) {
            if (!set.contains(chs[fast])) {
                set.add(chs[fast]);
                int tempLongest = fast - slow + 1;
                longest = Math.max(longest, tempLongest);
            } else {
                while (chs[slow] != chs[fast]) {
                    set.remove(chs[slow]);
                    slow++;
                }
                slow++;
            }
            fast++;
        }
        System.out.println(longest);
    }
}
