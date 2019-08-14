package com.hey_there;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public String multiply(String num1, String num2) {
        //处理乘数含有0的情况
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') {
            return "0";
        }

        //两数的长度
        int len_num1 = num1.length();
        int len_num2 = num2.length();
        //交换num1和num2所引用的对象，让num2始终指向较短的一个
        if (len_num1 < len_num2) {
            String temp = num1;
            num1 = num2;
            num2 = temp;

            //同时交换len_num1和len_num2的值
            len_num1 = num1.length();
            len_num2 = num2.length();
        }

        List<String> tempResults = new ArrayList<>();
        for (int i = 1; i <= len_num2; i++) {
            StringBuilder tempResult = new StringBuilder();

            //当前循环，num2参与运算的位
            int num2_digit = Integer.parseInt(num2.charAt(len_num2 - i) + "");
            //当前位为0时简化计算
            if (num2_digit == 0) {
                tempResults.add("0");
                continue;
            }

            int carry = 0;
            for (int j = 1; j <= len_num1; j++) {
                //当前循环，num1参与运算的位
                int num1_digit = Integer.parseInt(num1.charAt(len_num1 - j) + "");

                int product = num1_digit * num2_digit + carry;
                carry = product / 10;
                tempResult.insert(0, product % 10);
            }
            //在tempResult后加0
            int appendedZeros = 0;
            while (appendedZeros < i - 1) {
                tempResult.append("0");
                appendedZeros++;
            }
            tempResults.add(tempResult.toString());
        }
        System.out.println(tempResults);

        return "";
    }


    private String addStrings(String num1, String num2) {


        return "";
    }
}
