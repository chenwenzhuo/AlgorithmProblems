package com.hey_there._186_ReverseWordsInString_2;

public class Solution {
    public void reverseWords(char[] s) {
        int n = s.length;
        //两次翻转，第一次对整个s数组翻转，第二次对s中单词翻转
        int low = 0, high = n - 1;
        while (low < high) {
            char t = s[low];
            s[low] = s[high];
            s[high] = t;
            low++;
            high--;
        }
        low = 0;//重置指针的值
        while (low < n) {
            //寻找空格，或数组尽头
            int space = low;
            while (space < n && s[space] != ' ')
                space++;
            high = space - 1;
            //翻转单词
            while (low < high) {
                char t = s[low];
                s[low] = s[high];
                s[high] = t;
                low++;
                high--;
            }
            low = space + 1;//下一个单词的起点
        }
    }
}
