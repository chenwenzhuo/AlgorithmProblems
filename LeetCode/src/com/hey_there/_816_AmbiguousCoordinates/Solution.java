package com.hey_there._816_AmbiguousCoordinates;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> ambiguousCoordinates(String s) {
        List<String> cordList = new ArrayList<>();
        String nums = s.substring(1, s.length() - 1);//取数字部分（去掉首尾的括号）
        for (int i = 1; i < nums.length(); i++) {
            String num1 = nums.substring(0, i);
            String num2 = nums.substring(i);
            List<String> validNums1 = getValidNums(num1);
            List<String> validNums2 = getValidNums(num2);
            for (String n1 : validNums1) {
                for (String n2 : validNums2) {
                    cordList.add("(" + n1 + ", " + n2 + ")");
                }
            }
        }
        return cordList;
    }

    private List<String> getValidNums(String n) {
        List<String> validNums = new ArrayList<>();
        //检查字符串本身，是否是有效数字
        if (isValidNum(n))
            validNums.add(n);
        //向字符串中添加小数点
        for (int i = 1; i < n.length(); i++) {
            String cur = n.substring(0, i) + "." + n.substring(i);
            if (isValidNum(cur))
                validNums.add(cur);
        }
        return validNums;
    }

    private boolean isValidNum(String n) {
        if (n.length() == 1) return true;//一位数一定有效

        if (!n.contains(".")) {//不含小数点
            //整数不以0开头
            return n.charAt(0) != '0';
        }
        //包含小数点
        if (n.lastIndexOf("0") == n.length() - 1)
            return false;//浮点数不以0结尾

        int idxDot = n.indexOf(".");//小数点在字符串中的下标
        if (n.charAt(0) == '0' && idxDot > 1)
            return false;//浮点数以0开头，小数点必须在下标为1的位置
        return true;
    }
}
