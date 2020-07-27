package com.hey_there.InterviewProblem_20_StringRepresentingNumericValue;

public class Solution {
    public boolean isNumber(String s) {
        int sLen = s.length();
        //去除s中的前导空格和结尾空格
        int begin = 0, end = sLen;
        while (begin < sLen && s.charAt(begin) == ' ') begin++;
        while (end > 0 && s.charAt(end - 1) == ' ') end--;
        s = s.substring(begin, end);

        char[] sArr = s.toCharArray();
        int sIdx = 0;

        return false;
    }
}
