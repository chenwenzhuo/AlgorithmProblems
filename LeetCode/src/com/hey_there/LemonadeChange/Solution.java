package com.hey_there.LemonadeChange;

public class Solution {
    public boolean lemonadeChange(int[] bills) {
        //若第一个人付10元或以上，则一定无法成功交易
        if (bills[0] >= 10) return false;

        //使用一个长为2的数组，存储手中的5元、10元钞票的数量
        int[] cashNum = {0, 0};
        //遍历bills数组
        for (int bill : bills) {
            //当前顾客付钱5元，无需找零
            if (bill == 5) cashNum[0]++;//5元钞票的数量加1
            else if (bill == 10) {//当前顾客付钱10元，需要找零5元
                if (cashNum[0] <= 0) return false;//5元钞票的数量不够，直接返回false
                //5元钞票的数量够用时，5元的数量减1,10元的数量加1
                cashNum[0]--;
                cashNum[1]++;
            } else {//当前顾客付钱20元，需要找零15元
                //首先尝试以一个5元和一个10元凑成15
                if (cashNum[0] >= 1 && cashNum[1] >= 1) {
                    //5元和10元数量都够时，成功找零
                    cashNum[0]--;
                    cashNum[1]--;
                } else if (cashNum[0] >= 3) {//无法以一个5元一个10元凑成15时，尝试使用三个5元
                    //5元的数量不少于3时，成功找零
                    cashNum[0] -= 3;
                } else return false;//无法凑出15元，返回false
            }
        }
        return true;
    }
}
