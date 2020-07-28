package com.hey_there.InterviewProblem_30_ValidateStackSequences;

import java.util.ArrayDeque;

public class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int lenSeq = pushed.length;
        int idxPush = 0, idxPop = 0;
        while (idxPush < lenSeq && idxPop < lenSeq) {
            //一直入栈，直到pushed数组全部入栈，或遇到下一个待出栈元素
            while (idxPush < lenSeq &&
                    pushed[idxPush] != popped[idxPop]) {
                stack.push(pushed[idxPush]);
                idxPush++;
            }
            /* 检查pushed数组是否已经全部入栈，若否，将当前idxPush指向的元素入栈，
             * 此时idxPush满足：pushed[idxPush] == popped[idxPop]*/
            if (idxPush < lenSeq) {
                stack.push(pushed[idxPush]);
                idxPush++;//下标指向下一个待入栈元素
            }
            //当栈顶与待出栈元素相等时，一直出栈
            while (!stack.isEmpty() && idxPop < lenSeq &&
                    stack.peek() == popped[idxPop]) {
                stack.pop();
                idxPop++;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        /*int[] pushed = {1, 2, 3, 4, 5};
        int[] popped = {4, 5, 3, 2, 1};*/
        int[] pushed = {8, 9, 2, 3, 7, 0, 5, 4, 6, 1};
        int[] popped = {6, 8, 2, 1, 3, 9, 0, 7, 4, 5};

        Solution solution = new Solution();
        boolean res = solution.validateStackSequences(pushed, popped);
        System.out.println(res);
    }
}
