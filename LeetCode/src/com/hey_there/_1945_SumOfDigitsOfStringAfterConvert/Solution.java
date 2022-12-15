package com.hey_there._1945_SumOfDigitsOfStringAfterConvert;

public class Solution {
    public int getLucky(String s, int k) {
        //将英文字符转为数字字符
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            builder.append(s.charAt(i) - 'a' + 1);
        for (int i = 0; i < k; i++) {//共k次转化
            int sum = 0;
            //每一次对字符串每一位数字字符求和
            for (int j = 0; j < builder.length(); j++)
                sum += (builder.charAt(j) - '0');
            //将累加的和重新记录
            builder = new StringBuilder();
            builder.append(sum);
        }
        return Integer.parseInt(builder.toString());
    }
}
