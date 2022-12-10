package com.hey_there._1780_CheckIfNumberIsSumOfPowersOfThree;

public class Solution {
    public boolean checkPowersOfThree(int n) {
        //将n转为三进制，若每一位都不为2，则返回true
        while (n > 0) {
            if (n % 3 == 2)
                return false;
            n /= 3;
        }
        return true;
    }
}
