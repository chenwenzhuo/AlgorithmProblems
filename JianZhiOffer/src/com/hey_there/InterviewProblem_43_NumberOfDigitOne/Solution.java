package com.hey_there.InterviewProblem_43_NumberOfDigitOne;

public class Solution {
    public int countDigitOne(int n) {
        int total = 0;//1的出现次数
        int curDigit = n % 10;//从个、十、百位逐位向上，curDigit为各个数位上的数字
        int high = n / 10, low = 0;
        int digitFactor = 1;

        //从个位向上，逐位计算各个数位上的1的数量
        while (high > 0 || curDigit > 0) {
            if (curDigit == 0) {
                total += (high * digitFactor);
            } else if (curDigit == 1) {
                total += (high * digitFactor + low + 1);
            } else {
                total += (high * digitFactor + digitFactor);
            }
            low += curDigit * digitFactor;
            curDigit = high % 10;
            high /= 10;
            digitFactor *= 10;
        }
        return total;
    }
}
