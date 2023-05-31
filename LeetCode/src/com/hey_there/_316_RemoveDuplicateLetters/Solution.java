package com.hey_there._316_RemoveDuplicateLetters;

import java.util.ArrayDeque;

public class Solution {
    public String removeDuplicateLetters(String s) {
        char[] ch_s = s.toCharArray();
        //s中仅包含小写字母，用长为26的数组可表示所有可能字符的状态
        boolean[] inStack = new boolean[26];//栈中是否包含某字符
        int[] chCnt = new int[26];//各字符在s中出现的次数
        for (char c : ch_s)//统计出现次数
            chCnt[c - 'a']++;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : ch_s) {
            if (inStack[c - 'a']) {
                chCnt[c - 'a']--;//出现次数减1，表示当前字符在后面还会出现的次数减少一次
                continue;
            }
            //由于需要保证返回值字典序最小，则开头的字母要尽可能小
            //当栈顶字符比当前字符大，且栈顶字符后面还会出现，则将栈顶弹出
            while (!stack.isEmpty() && stack.peek() > c && chCnt[stack.peek() - 'a'] > 0) {
                char top = stack.pop();
                inStack[top - 'a'] = false;
            }
            stack.push(c);//将当前字符作为新的栈顶
            inStack[c - 'a'] = true;//标记为已在栈中
            chCnt[c - 'a']--;//出现次数减1，表示当前字符在后面还会出现的次数减少一次
        }
        //将栈中字符拼接为字符串
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty())
            builder.append(stack.pop());
        return builder.reverse().toString();//字符出栈后顺序被反转，需要再进行一次反转
    }
}
