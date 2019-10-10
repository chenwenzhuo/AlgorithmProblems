package com.hey_there.DynamicProgramming.LongestValidParentheses;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int longestValidParentheses(String s) {
        //s中可能有多个有效括号串，将其最后字符下标作为key，括号串长度作为value存入validParenthesesStrings
        HashMap<Integer, Integer> validParenthesesStrings = new HashMap<>();

        //最大有效括号长度
        int maxLength = 0;//可能不存在有效括号，故初始化为0

        int index_nextValidPair;//下一对有效括号的左括号的下标
        int validParenthesesStart;//有效括号串的起始字符
        int validParenthesesEnd = -1;//有效括号串的结束字符，初始情况下还未发现有效括号串，将其初始化为-1

        while (true) {
            //从前一个有效括号串的下一个字符开始寻找下一对有效括号对
            index_nextValidPair = nextValidPairIndex(validParenthesesEnd + 1, s);
            if (index_nextValidPair == -1) {
                //若index_nextValidPair值为-1，说明s中没有后续有效括号对，退出循环
                break;
            }

            //若找到一对有效括号对
            /*以此有效括号对为基础进行扩张，
             * 若字符串 str 为有效括号串，则扩张 str 的方法有两种：
             * 1.str()
             * 2.(str)*/
            //更新有效括号串的起始终止字符下标值
            validParenthesesStart = index_nextValidPair;//有效括号串的起始字符
            validParenthesesEnd = index_nextValidPair + 1;//有效括号串的结束字符

            while (validParenthesesStart >= 0 && validParenthesesEnd < s.length()) {//当括号串的范围不超过s时，继续扩张
                //检查后续字符
                if (validParenthesesEnd + 3 <= s.length() &&//首先检查是否会越界
                        s.substring(validParenthesesEnd + 1, validParenthesesEnd + 3).equals("()")) {
                    //若后续两个字符是括号对，则当前串可向后扩张，更新validParenthesesEnd的值
                    validParenthesesEnd += 2;
                    continue;
                }

                if (validParenthesesStart - 1 >= 0 && validParenthesesEnd + 1 < s.length() &&//检查是否越界
                        s.charAt(validParenthesesStart - 1) == '(' && s.charAt(validParenthesesEnd + 1) == ')') {
                    //若前一个字符和后一个字符构成括号对包围当前串，则当前串可向左右两个方向扩张
                    //更新validParenthesesStart和validParenthesesEnd的值
                    validParenthesesStart -= 1;
                    validParenthesesEnd += 1;
                    continue;
                }

                //若当前串无法继续扩张
                //首先检查当前串是否能与前面的有效串拼接
                if (validParenthesesStrings.containsKey(validParenthesesStart - 1)) {
                    //若能拼接，将当前串与前方串进行拼接，当前串向左扩张，更新validParenthesesStart
                    validParenthesesStart -= validParenthesesStrings.get(validParenthesesStart - 1);

                    //完成拼接后，继续尝试扩张
                    continue;
                }

                //若无法扩张也无法拼接
                int currentParenthesesLength = validParenthesesEnd - validParenthesesStart + 1;//计算当前串的长度
                maxLength = Math.max(maxLength, currentParenthesesLength);//更新最长串的长度值
                //将当前串存入map
                validParenthesesStrings.put(validParenthesesEnd, currentParenthesesLength);
                break;
            }
        }

        return maxLength;
    }

    private int nextValidPairIndex(int start, String parentheses) {
        for (int i = start; i < parentheses.length() - 1; i++) {
            if (parentheses.charAt(i) == '(' && parentheses.charAt(i + 1) == ')') {
                return i;//若找到括号对，返回左括号的下标
            }
        }
        return -1;//若start之后没有括号对，返回-1
    }

    public static void main(String[] args) {
        String s = ")(((((()())()()))()(()))(";
        System.out.println("Parentheses string length:" + s.length());

        Solution solution = new Solution();
        System.out.println("Length of longest valid parentheses:  " + solution.longestValidParentheses(s));
    }
}
