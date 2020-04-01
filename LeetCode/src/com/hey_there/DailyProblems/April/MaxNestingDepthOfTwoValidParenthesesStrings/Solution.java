package com.hey_there.DailyProblems.April.MaxNestingDepthOfTwoValidParenthesesStrings;

public class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        //将字符串转换为数组，记录其长度
        char[] seqArray = seq.toCharArray();
        int len = seqArray.length;

        int stack = -1;//用一个变量模拟栈
        int[] depths = new int[len];//depths[i]表示i位置的“(”或“)”的嵌套深度
        //遍历字符数组，获取各个depths[i]的值
        for (int i = 0; i < len; i++) {
            if (seqArray[i] == '(') {
                stack++;
                depths[i] = stack + 1;
            } else if (seqArray[i] == ')') {
                depths[i] = stack + 1;
                stack--;
            }
        }
        int[] ans = new int[len];
        //将深度为奇数的括号划给A，为偶数的划给B
        for (int i = 0; i < len; i++) {
            if (depths[i] % 2 == 1) {
                ans[i] = 0;
            } else {
                ans[i] = 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String seq = "(()())";
        Solution solution = new Solution();
        int[] ans = solution.maxDepthAfterSplit(seq);

        for (int n : ans) {
            System.out.print(n + "  ");
        }
        System.out.println();
    }
}
