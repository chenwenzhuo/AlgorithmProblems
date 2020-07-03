package com.hey_there.CountAndSay;

public class Solution {
    //在n较大时会发生溢出
    public String countAndSay_mayOverflow(int n) {
        if (n == 1) {
            return "1";
        }
        long prevNum = 1;//序列中前一个数
        int orderOfPrevNum = 1;//序列中前一个数在序列中的序号
        long nextNum;//序列中待计算的下一个数
        long multiplier = 1;
        while (orderOfPrevNum < n) {
            nextNum = 0;
            long prevNum_temp = prevNum;
            int tailDigit = 0;
            int tailDigitCount = 0;
            while (prevNum_temp > 0) {
                //尾数与前一个相同则继续将prevNum_temp除以10
                if (prevNum_temp % 10 == tailDigit) {
                    prevNum_temp /= 10;
                    tailDigitCount++;
                    continue;
                }
                //尾数为0表示这是第一次循环或前一次循环中更新了nextNum的值
                //故需要一个新的尾数
                if (tailDigit == 0) {
                    tailDigit = (int) (prevNum_temp % 10);
                    tailDigitCount = 1;
                    prevNum_temp /= 10;
                    continue;
                }
                //尾数发生变化则将其累加到nextNum
                nextNum = (tailDigitCount * 10 + tailDigit) * multiplier + nextNum;
                //更新各个变量值
                tailDigit = 0;
                tailDigitCount = 0;
                multiplier *= 100;
            }
            nextNum = (tailDigitCount * 10 + tailDigit) * multiplier + nextNum;
            multiplier = 1;
            prevNum = nextNum;
            orderOfPrevNum++;
        }
        return String.valueOf(prevNum);
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        //使用StringBuilder存放下一个数的各个数位
        StringBuilder nextNum = new StringBuilder();
        String prevNum = "1";//序列中前一个数
        int orderOfPrevNum = 1;//序列中前一个数在序列中的序号

        while (orderOfPrevNum < n) {//循环直到计算出第n个数
            //将字符串转化为数组，方便遍历
            char[] prevNumChs = prevNum.toCharArray();
            int digitCount = 1;//同一字符连续出现的次数

            for (int i = 1; i < prevNumChs.length; i++) {
                //当前i位置的字符与前一个字符相同时
                if (prevNumChs[i] == prevNumChs[i - 1]) {
                    digitCount++;//出现次数加1
                    continue;//继续检查下一个位置
                }
                ////当前i位置的字符与前一个字符不同时
                nextNum.append(digitCount);//出现次数加入nextNum字符串
                nextNum.append(prevNumChs[i - 1]);//前一个字符加入nextNum字符串
                digitCount = 1;//重置出现次数
            }
            //由于prevNumChs中最后一段连续相同字符无法在循环中加入nextNum字符串
            //故退出循环后补上
            nextNum.append(digitCount);
            nextNum.append(prevNumChs[prevNumChs.length - 1]);
            //更新prevNum
            prevNum = nextNum.toString();
            //清空StringBuilder
            nextNum.delete(0, nextNum.length());
            orderOfPrevNum++;
        }
        return prevNum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        //String ans = solution.countAndSay(3);
        String ans;
        for (int i = 1; i <= 10; i++) {
            ans = solution.countAndSay(i);
            System.out.println("i = " + i + "   " + ans);
        }
    }
}
