package com.hey_there.ValidParentheses;

import java.util.Stack;

public class Solutions {
    private String leftBrackets = "([{";
    private String rightBrackets = ")]}";

    public boolean isValid(String s) {
        int strLen = s.length();//获取字符串长度
        //字符串长度为0则有效
        if (0 == strLen) {
            return true;
        }
        //长度为奇数，一定无效
        if (1 == strLen % 2) {
            return false;
        }
        //若字符串以右括号开头或以左括号结尾，一定无效
        if (rightBrackets.contains(s.charAt(0) + "") ||
                leftBrackets.contains(s.charAt(strLen - 1) + "")) {
            return false;
        }

        //将字符串中字符按序压入栈中
        Stack<Character> stackBrackets = new Stack<>();
        int charIndex = 0;

        while (charIndex < strLen) {
            char charAtIndex = s.charAt(charIndex);//获得charIndex位置的字符
            //若当前字符是左括号，直接压入栈中,然后处理下一个字符
            if (leftBrackets.contains(charAtIndex + "")) {
                stackBrackets.push(s.charAt(charIndex));
                charIndex++;
                continue;
            }

            //执行到这里，则当前字符为右括号
            //若当前右括号与栈顶的左括号匹配成一对，则将左括号出栈
            //否则不匹配，返回false
            if (isMatching(stackBrackets, charAtIndex)) {
                stackBrackets.pop();
            } else {
                return false;
            }

            charIndex++;
        }
        return true;
    }

    private boolean isMatching(Stack<Character> stack, char rightBracket) {
        char charAtStackTop = stack.peek();

        int indexInLeftStr = leftBrackets.indexOf(charAtStackTop);//栈顶左括号在左括号字符串中的位置
        int indexInRightStr = rightBrackets.indexOf(rightBracket);//参数右括号在右括号字符串中的位置

        //两值相等则匹配，不等则不匹配
        return indexInLeftStr == indexInRightStr;
    }
}
