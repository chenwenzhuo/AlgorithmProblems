package com.hey_there.InterviewProblem_44_NthDigit;

public class Solution {
    //在n较大时超时
    public int findNthDigit_1(int n) {
        int num = 0;//num从0,1,2开始往后数，直到数到第n位
        long countDigit = 0;//记录num的最后一位在序列中的下标
        while (countDigit < n) {
            num++;
            //计算num当前是几位数
            int numDigits = ((int) Math.log10(num)) + 1;
            countDigit += numDigits;
        }
        //上方循环结束后，序列中的第n位已经包含在当前num中
        while (countDigit > n) {
            num /= 10;
            countDigit--;
        }
        return num % 10;
    }

    public int findNthDigit_2(int n) {
        /* 将 101112⋯ 中的每一位称为数位，记为n
         * 将 10, 11, 12⋯ 称为数字，记为num
         * 数字 10 是一个两位数，称此数字的位数为2，记为digit
         * 每 digit 位数的起始数字（即：1, 10, 100⋯），记为start*/
        //由于当n较大时，步骤中涉及的数可能很大，所以全部使用long类型
        long countDigit = 0L;
        long digit = 1, start = 1;
        while (countDigit < n) {
            countDigit += (start * 9 * digit);//计算 digit 位数在序列中占据的最大下标
            digit++;
            start *= 10;
        }
        //上方循环退出时，可确定序列的第n位所在的数字num是一个(digit-1)位数
        //接下来确定数字num
        long num = start - ((countDigit - n) / (digit - 1) + 1);
        //计算num的个位在序列中的下标
        long idx_num = countDigit - (start - 1 - num) * (digit - 1);
        while (idx_num > n) {
            num /= 10;
            idx_num--;
        }
        return (int) num % 10;
    }

    public static void main(String[] args) {
        //int n = 1000000000;
        int n = 2147483647;
        Solution solution = new Solution();
        int digit = solution.findNthDigit_2(n);
        System.out.println(digit);
    }
}
