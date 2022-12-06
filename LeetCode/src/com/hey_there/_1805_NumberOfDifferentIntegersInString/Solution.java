package com.hey_there._1805_NumberOfDifferentIntegersInString;

import java.util.HashSet;

public class Solution {
    public int numDifferentIntegers_1(String word) {
        int lenWord = word.length();
        HashSet<String> set = new HashSet<>();//保存不同的数字，并去重
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < lenWord; i++) {
            char w = word.charAt(i);
            if (Character.isDigit(w)) {//对于数字，直接加入builder
                builder.append(w);
                continue;
            }
            //对于字母
            if (builder.length() == 0)
                continue;//builder无内容时，直接跳过
            //builder有内容，则字母表示一个数字串的结束
            int idx = checkZero(builder);//检查非0字符的下标
            if (idx < 0)//idx为负，builder中全是0
                set.add("0");
            else//idx为正，取子串加入set
                set.add(builder.substring(idx));
            builder.delete(0, builder.length());
        }
        if (builder.length() > 0) {
            int idx = checkZero(builder);//检查非0字符的下标
            if (idx < 0)//idx为负，builder中全是0
                set.add("0");
            else//idx为正，取子串加入set
                set.add(builder.substring(idx));
        }
        return set.size();
    }

    //检查字符串是否全是0，全0时返回-1，否则返回第一个非0字符的下标
    private int checkZero(StringBuilder builder) {
        int i = 0, length = builder.length();
        while (i < length && builder.charAt(i) == '0')
            i++;
        return i == length ? -1 : i;
    }

    public int numDifferentIntegers_2(String word) {
        HashSet<String> set = new HashSet<>();//保存不同的数字串，并去重
        int lenWord = word.length();
        int left = 0, right;
        while (true) {
            //寻找下一个数字字符
            while (left < lenWord && Character.isLetter(word.charAt(left)))
                left++;
            if (left >= lenWord)//遍历word完成，跳出循环
                break;
            right = left + 1;//将right指向left的下一个位置
            //将right向后移动，直到当前数字子串结束
            while (right < lenWord && Character.isDigit(word.charAt(right)))
                right++;
            //将left向后移动，直到left指向的位置数字字符不为0，以去掉前导0
            while (left < right && word.charAt(left) == '0')
                left++;
            if (left == right)//二者相等，则刚刚的数字子串为全0
                set.add("0");
            else
                set.add(word.substring(left, right));
            left = right + 1;
        }
        return set.size();
    }
}
