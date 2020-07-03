package com.hey_there.DecodeString;

import java.util.ArrayDeque;

public class Solution {
    public String decodeString(String s) {
        char[] chs = s.toCharArray();
        int len = s.length();

        ArrayDeque<String> stack = new ArrayDeque<>();//存放字符的堆栈
        StringBuilder ansBuilder = new StringBuilder();
        StringBuilder repeatBuilder = new StringBuilder();
        StringBuilder encodedBuilder = new StringBuilder();
        int idx = 0;
        while (idx < len) {
            //遇到数字，解析出数字字符串，进栈
            while ('0' <= chs[idx] && chs[idx] <= '9') {
                repeatBuilder.append(chs[idx]);
                idx++;
            }
            if (repeatBuilder.length() > 0) {
                stack.push(repeatBuilder.toString());
                repeatBuilder.delete(0, repeatBuilder.length());//清空
            }

            //遇到左括号直接进栈
            if (chs[idx] == '[') {
                stack.push(String.valueOf('['));
                idx++;
            }
            //遇到字母，解析出字符串
            while (idx < len &&
                    (('A' <= chs[idx] && chs[idx] <= 'Z') ||
                            ('a' <= chs[idx] && chs[idx] <= 'z'))) {
                encodedBuilder.append(chs[idx]);
                idx++;
            }
            if (encodedBuilder.length() > 0) {
                //栈非空则进栈，此部分字符串需要重复
                if (!stack.isEmpty()) {
                    stack.push(encodedBuilder.toString());
                } else {
                    //栈为空则直接加入ansBuilder
                    ansBuilder.append(encodedBuilder.toString());
                }
                encodedBuilder.delete(0, encodedBuilder.length());//清空
            }

            //遇到右括号，开始出栈
            if (idx < len && chs[idx] == ']') {
                //获取被编码的字符串
                while (!stack.peek().equals("[")) {
                    encodedBuilder.insert(0, stack.pop());
                }
                String encoded = encodedBuilder.toString();
                encodedBuilder.delete(0, encodedBuilder.length());
                stack.pop();//将左括号出栈
                int repeat = Integer.parseInt(stack.pop());//重复次数
                //根据栈是否为空决定将解码的字符串放入ansBuilder或encodedBuilder
                if (stack.isEmpty()) {
                    for (int i = 0; i < repeat; i++) {
                        ansBuilder.append(encoded);
                    }
                } else {
                    for (int i = 0; i < repeat; i++) {
                        encodedBuilder.append(encoded);
                    }
                    stack.push(encodedBuilder.toString());
                    encodedBuilder.delete(0, encodedBuilder.length());
                }
                idx++;
            }
        }
        return ansBuilder.toString();
    }

    public static void main(String[] args) {
        //String s = "3[a]2[bc]";
        //String s = "3[a2[c]]";
        //String s = "2[abc]3[cd]ef";
        //String s = "2[abc]ef3[cd]";
        //String s = "2[3[ab]cd]";//abababcdabababcd
        //String s = "3[a2[c]]";
        String s = "2[2[bc]3[xy]]xd4[rt]";//bcbcxyxyxybcbcxyxyxyxdrtrtrtrt
        Solution solution = new Solution();
        String ans = solution.decodeString(s);
        System.out.println(ans);
        System.out.println("bcbcxyxyxybcbcxyxyxyxdrtrtrtrt");
    }
}
