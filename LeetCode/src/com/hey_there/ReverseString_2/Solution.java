package com.hey_there.ReverseString_2;

public class Solution {
    public String reverseStr_1(String s, int k) {
        int lenS = s.length();
        char[] chsArr = s.toCharArray();
        StringBuilder builder = new StringBuilder();

        int idx = 0;
        while (idx < lenS) {
            int reverseEndIdx = Math.min(idx + k - 1, lenS - 1);
            while (reverseEndIdx >= idx) {
                builder.append(chsArr[reverseEndIdx]);
                reverseEndIdx--;
            }

            int noReverseIdx = idx + k;
            while (noReverseIdx < idx + k * 2 && noReverseIdx < lenS) {
                builder.append(chsArr[noReverseIdx]);
                noReverseIdx++;
            }
            idx += k * 2;
        }
        return builder.toString();
    }

    public String reverseStr_2(String s, int k) {
        int lenS = s.length();
        char[] chsArr = s.toCharArray();
        int arrIdx = 0;
        while (arrIdx < lenS) {
            int left = arrIdx, right = Math.min(arrIdx + k - 1, lenS - 1);
            char temp;
            while (left < right) {
                temp = chsArr[left];
                chsArr[left] = chsArr[right];
                chsArr[right] = temp;
                left++;
                right--;
            }
            arrIdx += k * 2;
        }
        return new String(chsArr);
    }
}
