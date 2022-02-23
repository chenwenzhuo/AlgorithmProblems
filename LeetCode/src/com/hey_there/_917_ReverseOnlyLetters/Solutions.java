package com.hey_there._917_ReverseOnlyLetters;

public class Solutions {
    public String reverseOnlyLetters(String s) {
        char[] chs = s.toCharArray();
        int low = 0, high = s.length() - 1;
        while (low < high) {
            //将low右移，寻找字母
            while (low < chs.length && !Character.isLetter(chs[low]))
                low++;
            //将high左移，寻找字母
            while (high >= 0 && !Character.isLetter(chs[high]))
                high--;
            if (low >= high) break;//若low越过了high，直接结束
            //low<high时进行交换
            char temp = chs[low];
            chs[low] = chs[high];
            chs[high] = temp;
            //交换之后low和high至少再移动一位
            low++;
            high--;
        }
        return String.valueOf(chs);
    }
}
