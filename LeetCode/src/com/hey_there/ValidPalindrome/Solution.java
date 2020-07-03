package com.hey_there.ValidPalindrome;

public class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();//忽略大小写
        char[] chs_s = s.toCharArray();
        int left = 0, right = chs_s.length - 1;

        while (left <= right) {
            //当left和right指向的字符不是有效字符时，移动left和right而不进行比较
            //有效字符指大小写字母和数字
            if (!isValidCharacter(chs_s[left])) {
                left++;
                continue;
            }
            if (!isValidCharacter(chs_s[right])) {
                right--;
                continue;
            }
            //当left和right都指向有效字符时，比较二者是否相同
            if (chs_s[left] == chs_s[right]) {
                //二者相同时，移动left和right，继续比较
                left++;
                right--;
            } else {
                //二者不同时，直接返回false
                return false;
            }
        }
        return true;
    }

    private boolean isValidCharacter(char c) {
        return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z') || ('0' <= c && c <= '9');
    }
}
