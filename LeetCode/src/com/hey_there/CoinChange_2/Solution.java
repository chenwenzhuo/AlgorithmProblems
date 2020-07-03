package com.hey_there.CoinChange_2;

import java.util.Arrays;

public class Solution {
    public int change_1(int amount, int[] coins) {
        int kindsOfCoins = coins.length;//硬币的不同面额数
        //dp[i][j]表示当目标金额为i时，用coins数组的前j种金额来凑，能凑成的方式数
        int[][] dp = new int[amount + 1][kindsOfCoins + 1];
        //初始化base case
        //i==0，即目标金额为0时，使用任何金额都只有1种凑法
        //j==0，即没有硬币时，对任何金额都只有0种凑法
        Arrays.fill(dp[0], 1);//初始化第一行为全1，第一列保持默认值0

        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j <= kindsOfCoins; j++) {
                if (i < coins[j - 1]) {
                    //目标金额比面值小时，继承前方结果
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - coins[j - 1]][j];
                }
            }
        }
        return dp[amount][kindsOfCoins];
    }

    public int change_2(int amount, int[] coins) {
        int kindsOfCoins = coins.length;//硬币的不同面额数
        //dp[i][j]表示使用前i种面值的硬币来凑金额j，能凑成的方式数
        int[][] dp = new int[kindsOfCoins + 1][amount + 1];
        //初始化base case
        //i==0时，即没有硬币时，对任何金额都只有0种凑法
        //j==0时，即目标金额为0时，使用任何金额都只有1种凑法
        //初始化第一列为全1，第一行保持默认值0
        for (int i = 0; i <= kindsOfCoins; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= kindsOfCoins; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j < coins[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[kindsOfCoins][amount];
    }

    public int change_3(int amount, int[] coins) {
        int kindsOfCoins = coins.length;//硬币的不同面额数
        //dp[i][j]表示使用前i种面值的硬币来凑金额j，能凑成的方式数
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coin) {
                    dp[j] = dp[j] + dp[j - coin];
                }
            }
        }
        return dp[amount];
    }
}
