package com.hey_there.FractionToDecimal;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {//若分子为0，直接返回0
            return "0";
        }

        StringBuilder fraction = new StringBuilder();
        //若两数异号，加上负号
        if (numerator < 0 ^ denominator < 0) {//运算符 ^ 表示按位异或，不同为真
            fraction.append("-");
        }
        //转换成long类型计算，否则绝对值可能溢出
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(dividend / divisor);//整数部分

        long remainder = dividend % divisor;//余数
        if (remainder == 0) {
            return fraction.toString();//余数为0则直接返回
        }
        fraction.append(".");//余数不为0则计算小数部分

        Map<Long, Integer> map = new HashMap<>();//map用来存储余数及其出现的位置
        while (remainder != 0) {
            //若出现重复余数，说明找到循环节
            if (map.containsKey(remainder)) {
                //将循环节用括号括住，结束循环
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            //未出现重复余数则将余数及其出现的位置存入map
            map.put(remainder, fraction.length());
            //余数扩大10倍，计算下一位小数
            remainder *= 10;
            fraction.append(remainder / divisor);
            remainder %= divisor;//更新余数
        }
        return fraction.toString();
    }

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(523, 2475));
    }
}
