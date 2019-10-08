package com.hey_there.DynamicProgramming.LongestValidParentheses;

public class Solution {
    public int longestValidParentheses(String s) {
        //最大有效括号长度
        int maxLength = 0;//可能不存在有效括号，故初始化为0

        int index_nextValidPair;//下一对有效括号的左括号的下标

        //获得第一对有效括号的左括号下标
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
                index_nextValidPair = i;
                break;
            }
        }

        /*以第一对有效括号为基础进行扩张，
        * 若字符串 s 为有效括号串，则扩张 s 的方法有两种：
        * 1.s()
        * 2.(s)*/

        return maxLength;
    }
}
