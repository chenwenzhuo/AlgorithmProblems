package com.hey_there.ReverseWordsInAString_3;

public class Solution {
    public String reverseWords(String s) {
        int lenS = s.length();
        char[] chsArr = s.toCharArray();

        int arrIdx = 0;
        while (arrIdx < lenS) {
            //找到下一个空格的数组下标，或数组尾
            int spaceOrEndIdx = arrIdx;
            while (spaceOrEndIdx < lenS && chsArr[spaceOrEndIdx] != ' ') {
                spaceOrEndIdx++;
            }
            //交换字符
            int left = arrIdx, right = spaceOrEndIdx - 1;
            char temp;
            while (left < right) {
                temp = chsArr[left];
                chsArr[left] = chsArr[right];
                chsArr[right] = temp;
                left++;
                right--;
            }
            arrIdx = spaceOrEndIdx + 1;
        }
        return new String(chsArr);
    }
}
