package com.hey_there._01_05_OneWayLCCI;

public class Solution {
    public boolean oneEditAway(String first, String second) {
        int len_1 = first.length(), len_2 = second.length();
        //两字符串长度差异超过1，直接返回false
        if (Math.abs(len_1 - len_2) > 1) return false;
        //两字符串相同，直接返回true
        if (len_1 == len_2 && first.equals(second)) return true;
        else if (len_1 == len_2) {//两字符串长度相等，但不相同
            boolean findDiff = false;
            //遍历字符串
            for (int i = 0; i < len_1; i++) {
                if (first.charAt(i) != second.charAt(i)) {//找到不同字符
                    if (!findDiff) findDiff = true;//第一次找到不同，将标志位置为true
                    else return false;//第二次找到不同，返回false
                }
            }
            return true;//遍历完成，返回true
        }

        String longer, shorter;
        if (len_1 > len_2) {
            longer = first;
            shorter = second;
        } else {
            longer = second;
            shorter = first;
            len_1 = longer.length();
            len_2 = shorter.length();
        }
        int idx1 = 0, idx2 = 0;
        while (idx1 < len_1 && idx2 < len_2) {
            char ch_1 = longer.charAt(idx1);
            char ch_2 = shorter.charAt(idx2);
            idx1++;//较长字符串longer的下标一定会后移
            //仅当两字符串当前字符相同时，较短字符串shorter的下标后移
            if (ch_1 == ch_2) idx2++;
            //若两下标位置相差超过1，返回false
            if (Math.abs(idx1 - idx2) > 1) return false;
        }
        //遍历完成，两下标相差不超过
        return Math.abs(idx1 - idx2) <= 1;
    }

    public static void main(String[] args) {
        String first = "teacher";
        String second = "beacher";
        Solution solution = new Solution();
        boolean res = solution.oneEditAway(first, second);
        System.out.println(res);
    }
}
