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
        return addStrings(tempResults);
    }

    private String addStrings(List<String> numInString) {
        String sum = "0";//和数字符串
        int len_sum = sum.length();//和数字符串的长度

        int sizeOfList = numInString.size();
        for (int i = 0; i < sizeOfList; i++) {
            String currentStr = numInString.get(i);//获取当前参与运算的数字字符串
            //若当前加数为0，跳过计算
            if (currentStr.equals("0")) {
                continue;
            }

            int len_currentStr = currentStr.length();//当前数字字符串的长度

            //重新定义两个变量作为加法的操作数
            //operand_1赋值为长度较长的，operand_2赋值为长度较短的
            String operand_1, operand_2;
            int len_operand_1, len_operand_2;

            if (len_sum > len_currentStr) {
                operand_1 = sum;
                len_operand_1 = len_sum;

                operand_2 = currentStr;
                len_operand_2 = len_currentStr;
            } else {
                operand_1 = currentStr;
                len_operand_1 = len_currentStr;

                operand_2 = sum;
                len_operand_2 = len_sum;
            }

            System.out.println("operand_1 ----- " + operand_1);
            System.out.println("operand_2 ----- " + operand_2);

            StringBuilder twoStrSum = new StringBuilder();
            int carry = 0;//进位

            //先将操作数2的所有位与操作数1的对应位相加
            for (int j = 1; j <= len_operand_2; j++) {
                //取操作数1的倒数第j位
                int operand_1_digit = Integer.parseInt(operand_1.charAt(len_operand_1 - j) + "");
                //取操作数2的倒数第j位
                int operand_2_digit = Integer.parseInt(operand_2.charAt(len_operand_2 - j) + "");

                int tempSum = operand_1_digit + operand_2_digit + carry;//将两个当前位与进位位相加
                carry = tempSum / 10;//更新进位位
                twoStrSum.insert(0, tempSum % 10);//将tempSum的个位插入
            }

            //退出上方循环后，长度较短的操作数2已经处理完毕，后面仅处理进位与操作数1相加
            for (int j = len_operand_2 + 1; j <= len_operand_1; j++) {
                //若没有进位，直接退出循环
                if (carry == 0) {
                    break;
                }

                //取操作数1的倒数第j位
                int operand_1_digit = Integer.parseInt(operand_1.charAt(len_operand_1 - j) + "");

                int tempSum = operand_1_digit + carry;
                carry = tempSum / 10;//更新进位位
                twoStrSum.insert(0, tempSum % 10);//将tempSum的个位插入
            }

            //退出循环后，进位位仍可能非0
            //若进位位非0，将其插入twoStrSum的最前面
            if (carry != 0) {
                twoStrSum.insert(0, carry);
            }

            //以上操作完成了两数相加，将twoStrSum的值更新到sum中
            sum = twoStrSum.toString();
            len_sum = sum.length();

            System.out.println("sum ----- " + sum);
        }

        return sum;
    }
}
