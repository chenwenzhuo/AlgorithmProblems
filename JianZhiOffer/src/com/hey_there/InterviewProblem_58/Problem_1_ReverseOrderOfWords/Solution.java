package com.hey_there.InterviewProblem_58.Problem_1_ReverseOrderOfWords;

public class Solution {
    public String reverseWords(String s) {
        s = s.trim();//去除首尾空格
        //将s以空格作为分隔符拆分，并将数组元素逆序加入StringBuilder中
        String[] words = s.split(" ");
        int numWords = words.length;
        StringBuilder builder = new StringBuilder();
        for (int i = numWords - 1; i > 0; i--) {
            if (!words[i].equals(""))
                builder.append(words[i]).append(" ");
        }
        builder.append(words[0]);
        return builder.toString();
    }

    public static void main(String[] args) {
        String s = "  hello world!  ";

        Solution solution = new Solution();
        String reversed = solution.reverseWords(s);
        System.out.println("*" + reversed + "*");
    }
}
