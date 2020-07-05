package com.hey_there.EvaluateReversePolishNotation;

import java.util.ArrayDeque;

public class Solution {
    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (String t : tokens) {
            if (t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")) {
                int num_1 = stack.pop();
                int num_2 = stack.pop();
                int tempRes;
                switch (t) {
                    case "+":
                        tempRes = num_2 + num_1;
                        break;
                    case "-":
                        tempRes = num_2 - num_1;
                        break;
                    case "*":
                        tempRes = num_2 * num_1;
                        break;
                    default:
                        tempRes = num_2 / num_1;
                        break;
                }
                stack.push(tempRes);
                continue;
            }
            stack.push(Integer.parseInt(t));
        }
        return stack.pop();
    }
}
