package com.hey_there.DailyProblems.March.CoinChange;

import java.util.Arrays;

public class Solution {
    private int[] dp;

    //自顶向下的dp算法
    //从目标金额amount开始递归
    public int coinChange_dp(int[] coins, int amount) {
        dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        return dpHelper(coins, amount);
    }

    private int dpHelper(int[] coins, int amount) {
        if (amount == 0) {
            return 0;//目标金额为0，返回0
        } else if (amount < 0) {
            return -1;//目标金额为负，返回-1
        }
        //首先检查当前金额所需硬币数是否计算过
        if (dp[amount] != Integer.MAX_VALUE) {
            return dp[amount];//已计算过则直接返回
        }
        //未计算则递归计算
        for (int coinVal : coins) {
            int subProblem = dpHelper(coins, amount - coinVal);
            if (subProblem < 0) {
                continue;//subProblem为负表示无法组成金额amount - coinVal
            }
            dp[amount] = Math.min(dp[amount], subProblem + 1);
        }
        //将coins中所有面值都尝试一遍后若dp[amount]值仍为Integer.MAX_VALUE，
        //则无法组成当前金额
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    //自底向上的dp算法
    //通过计算所有小于amount的金额所需硬币数来逐步得到amount所需硬币数
    public int coinChange_dp_bottomUp(int[] coins, int amount) {
        //coinQuantity[i]表示组成金额i所需的最少硬币数
        int[] coinQuantity = new int[amount + 1];
        Arrays.fill(coinQuantity, amount + 1);
        coinQuantity[0]=0;//目标金额为0，所需硬币数也为0

        //计算小于等于amount的所有金额所需硬币数
        for (int curAmount = 1; curAmount <= amount; curAmount++) {
            //对于当前目标金额curAmount，逐个尝试硬币面值，检查子问题curAmount-coin是否有解
            for (int coin : coins) {
                /*if (curAmount == coin) {
                    coinQuantity[curAmount] = 1;//二者相等表示有硬币的面值是curAmount
                } else if (curAmount > coin && coinQuantity[curAmount - coin] > 0) {
                    coinQuantity[curAmount] =
                            Math.min(coinQuantity[curAmount], coinQuantity[curAmount - coin] + 1);
                }*/
                if (curAmount >= coin) {//当前目标金额大于等于硬币面值时才可能有解
                    //二者取其小
                    coinQuantity[curAmount] =
                            Math.min(coinQuantity[curAmount], coinQuantity[curAmount - coin] + 1);
                }
            }
        }
        //coinQuantity[amount]大于amount表示无法组成amount，返回-1
        //否则返回coinQuantity[amount]的值
        return coinQuantity[amount] > amount ? -1 : coinQuantity[amount];
    }

    public static void main(String[] args) {
        //int[] coins = {1, 2, 5};
        int[] coins = {2};

        Solution solution = new Solution();
        System.out.println(solution.coinChange_dp(coins, 3));
    }
}
