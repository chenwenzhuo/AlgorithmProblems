package com.hey_there.DailyProblems.CoinChange;

import java.util.Arrays;

public class Solution {
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        //对coins数组排序
        Arrays.sort(coins);
        //无法组成比最小面额还小的金额
        if (amount < coins[0]) {
            return -1;
        }

        /*coinsQuantity数组用来记录组成不同金额需要的最少硬币数
        coinsQuantity[i]表示组成金额 i 需要的最少硬币数*/
        int[] coinsQuantity = new int[amount + 1];
        for (int i = 0; i <= amount; i++) {
            if (i < coins[0]) {
                coinsQuantity[i] = -1;//比最小面额小的金额都定为-1
            } else {
                coinsQuantity[i] = Integer.MAX_VALUE;//比最小面额大的金额初始化为Integer.MAX_VALUE
            }
        }

        //计算coinsQuantity数组后续元素的值
        for (int money = coins[0]; money <= amount; money++) {

            for (int coinValue : coins) {
                //若目标面额money比当前coins[i]小，直接终止内层循环
                if (money < coinValue) {
                    break;
                }
                //若有面额为money的硬币
                if (money == coinValue) {
                    coinsQuantity[money] = 1;
                    break;
                }

                //若目标面额比当前coins[i]大
                if (coinsQuantity[money - coinValue] != -1 &&
                        coinsQuantity[money - coinValue] + 1 < coinsQuantity[money]) {
                    coinsQuantity[money] = coinsQuantity[money - coinValue] + 1;
                }
            }

            //若将所有可用面额遍历一遍后coinsQuantity[money]值未改变（即无法组成当前面额）
            if (coinsQuantity[money] == Integer.MAX_VALUE) {
                coinsQuantity[money] = -1;
            }
        }
        return coinsQuantity[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2, 4};
        System.out.println(coinChange(coins, 11));
    }
}
