package com.hey_there.ValidParentheses;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Solution_2 {
    public boolean isValid(String s) {
        if (s == null || s.length() % 2 == 1) return false;
        if (s.equals("")) return true;

        //map存储左右括号的映射
        HashMap<Character, Character> map = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};
        char[] chs = s.toCharArray();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : chs) {
            //对于左括号，直接入栈
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            }
            //对于右括号，若栈顶符号能与其构成一对括号，则将栈顶出栈
            //否则不能构成有效括号
            if (stack.isEmpty() || map.get(stack.peek()) != c) return false;
            stack.pop();
        }
        return stack.isEmpty();
    }
}
