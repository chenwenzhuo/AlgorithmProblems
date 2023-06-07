package com.hey_there._2611_MiceAndCheese;

import java.util.PriorityQueue;

public class Solution {
    //动态规划，背包问题，复杂度O(n^2)，超时
    public int miceAndCheese_1(int[] reward1, int[] reward2, int k) {
        int n = reward1.length;
        //dp[i][j]表示在前i个奶酪中第一只老鼠吃掉j个的最高得分
        //base case：i==0（没有奶酪），得分都为0；j==0（一号一个不吃，二号全吃）得分为reward2前缀和
        //dp[i]行只与dp[i-1]行有关，可优化内存
        int[][] dp = new int[2][k + 1];
        dp[1][0] = reward2[0];
        //dp[i][j]=
        //1.吃掉第j个：dp[i-1][j-1]+reward1[j-1]
        //2.不吃第j个：dp[i-1][j]+reward2[j-1]
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k && j <= i; j++) {
                int curRow = i % 2;//当前行下标
                int prevRow = (i - 1) % 2;//前一行下标
                if (j == 0) {
                    //j==0（一号一个不吃，二号全吃）得分为reward2前缀和
                    dp[curRow][j] = dp[prevRow][j] + reward2[i - 1];
                } else if (j == i) {
                    //若j==i，则一号必须全吃
                    dp[curRow][j] = dp[prevRow][j - 1] + reward1[i - 1];
                } else {
                    //j<i时，一号可选择吃或不吃
                    dp[curRow][j] = Math.max(
                            dp[prevRow][j - 1] + reward1[i - 1],
                            dp[prevRow][j] + reward2[i - 1]
                    );
                }
            }
        }
        return dp[(n + 1) % 2][k];
    }

    public int miceAndCheese_2(int[] reward1, int[] reward2, int k) {
        //若一号不吃，二号全吃，得分为reward2元素和
        int sum = 0;
        for (int r : reward2)
            sum += r;
        //若一号吃掉下标i的奶酪，得分变化量为reward1[i]-reward2[i]
        //要使得分最大，则可使变化量之和最大，计算n个变化量，从中选择最大的k个
        //用小顶堆保存n个差值中最大的k个
        int n = reward1.length;
        PriorityQueue<Integer> heep = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            heep.offer(reward1[i] - reward2[i]);
            //堆体积大于k，将最小的移除
            if (heep.size() > k)
                heep.poll();
        }
        while (!heep.isEmpty())
            sum += heep.poll();
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] reward1 = {1, 1, 3, 4};
        int[] reward2 = {4, 4, 1, 1};
        int k = 2;
        int res = solution.miceAndCheese_1(reward1, reward2, k);
        System.out.println(res);
    }
}
