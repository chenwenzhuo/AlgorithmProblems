package com.hey_there.DailyProblems.April.ReverseWordsInAString;

public class Solution {
    public String reverseWords(String s) {
        int lenS = s.length();
        if (lenS == 0) {
            return "";
        }
        //转换成数组方便遍历
        char[] chS = s.toCharArray();
        StringBuilder ansBuilder = new StringBuilder();
        StringBuilder tempBuilder = new StringBuilder();

        //第一个非空白字符的下标
        int index_firstNonSpaceChar = 0;
        //计算index_firstNonSpaceChar的正确值
        while (index_firstNonSpaceChar < lenS && chS[index_firstNonSpaceChar] == ' ') {
            index_firstNonSpaceChar++;
        }
        //最后一个非空白字符的下标
        int index_lastNonSpaceChar = lenS - 1;
        //计算index_lastNonSpaceChar的正确值
        while (index_lastNonSpaceChar >= 0 && chS[index_lastNonSpaceChar] == ' ') {
            index_lastNonSpaceChar--;
        }

        //从最后一个非空白字符开始倒序遍历
        for (int i = index_lastNonSpaceChar; i >= index_firstNonSpaceChar; i--) {
            //遇到空格，说明一个单词已经全部添加到tempBuilder中
            if (chS[i] == ' ' && tempBuilder.length() > 0) {
                //将tempBuilder反转后添加到ansBuilder
                ansBuilder.append(' ').append(tempBuilder.reverse().toString());
                //清空tempBuilder
                tempBuilder.delete(0, tempBuilder.length());
                continue;
            } else if (chS[i] == ' ' && tempBuilder.length() == 0) {
                continue;
            }
            //当前字符非空格则添加到tempBuilder
            tempBuilder.append(chS[i]);
        }
        //退出循环后，s中最前面的单词可能还未添加到ansBuilder
        /*if (tempBuilder.length() > 0) {
            ansBuilder.append(' ').append(tempBuilder.reverse().toString());
        }*/
        ansBuilder.append(' ').append(tempBuilder.reverse().toString());
        //循环中首次将单词添加到ansBuilder前多加了一个空白字符，将其删除
        ansBuilder.delete(0, 1);
        return ansBuilder.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("." + solution.reverseWords("good") + ".");
    }
}
