package com.hey_there._161_OneEditDistance;

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        //若s比t长，交换引用
        if (s.length() > t.length()) {
            String temp = s;
            s = t;
            t = temp;
        }
        int len_s = s.length(), len_t = t.length();
        if (len_t - len_s > 1)//长度相差超过1，返回false
            return false;
        int i_s = 0, i_t = 0;//遍历s、t的下标
        int editDist = 0;//编辑距离
        while (i_s < len_s && i_t < len_t) {
            char ch_s = s.charAt(i_s);
            char ch_t = t.charAt(i_t);
            if (ch_s == ch_t) {
                //当前字符相同，同时移动两个下标，继续检查
                i_s++;
                i_t++;
            } else {
                //当前字符不同，编辑距离距离加1
                editDist++;
                //若长度相等，同时移动两个下标，否则只移动i_t
                if (len_s == len_t)
                    i_s++;
                i_t++;
            }
            if (editDist > 1)
                break;
        }
        return editDist == 1 || (editDist == 0 && len_t - len_s == 1);
    }
}
