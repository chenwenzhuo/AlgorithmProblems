package com.hey_there._1801_NumberOfOrdersInTheBacklog;

import java.util.PriorityQueue;

public class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> buys = new PriorityQueue<>((arr1, arr2) -> arr2[0] - arr1[0]);
        PriorityQueue<int[]> sells = new PriorityQueue<>((arr1, arr2) -> arr1[0] - arr2[0]);
        for (int[] order : orders) {
            int price = order[0], amount = order[1], orderType = order[2];
            if (orderType == 0) {//当前为采购订单
                while (amount > 0 && !sells.isEmpty() && sells.peek()[0] <= price) {
                    int[] lowestSell = sells.peek();//价格最低的销售订单
                    //将采购和销售订单进行抵消
                    int minAmt = Math.min(amount, lowestSell[1]);//取数量较小者
                    lowestSell[1] -= minAmt;
                    amount -= minAmt;
                    //若抵消后销售订单数量小于等于0，将其从队列中移除
                    if (lowestSell[1] == 0)
                        sells.poll();
                }
                //完成抵消后，采购订单还有剩余，将其加入采购队列
                if (amount > 0)
                    buys.offer(new int[]{price, amount});
                continue;
            }
            //当前为销售订单
            while (amount > 0 && !buys.isEmpty() && buys.peek()[0] >= price) {
                int[] highestBuy = buys.peek();//价格最高的采购订单
                //将采购和销售订单进行抵消
                int minAmt = Math.min(amount, highestBuy[1]);//取数量较小者
                highestBuy[1] -= minAmt;
                amount -= minAmt;
                //若抵消后采购订单数量小于等于0，将其从队列中移除
                if (highestBuy[1] == 0)
                    buys.poll();
            }
            //完成抵消后，销售订单还有剩余，将其加入队列
            if (amount > 0)
                sells.offer(new int[]{price, amount});
        }
        int backlogOrders = 0;
        final int MOD = 1000000007;
        while (buys.size() > 0) {
            int[] buy = buys.poll();
            backlogOrders = (backlogOrders + buy[1]) % MOD;
        }
        while (sells.size() > 0) {
            int[] sell = sells.poll();
            backlogOrders = (backlogOrders + sell[1]) % MOD;
        }
        return backlogOrders;
    }
}
