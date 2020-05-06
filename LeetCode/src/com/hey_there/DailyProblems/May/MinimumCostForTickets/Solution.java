package com.hey_there.DailyProblems.May.MinimumCostForTickets;

public class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        int numDays = days.length;//要去旅行的天数
        //dp[i]表示进行第1天到第i+1天的旅行所需的最低费用
        int[] dp = new int[365];
        //days[0]之前的时间不进行旅行，花费为0（数组默认值为0，不进行手动初始化）
        dp[days[0] - 1] = costs[0];//第一个要旅行的日期，花费为一天的票价

        int daysIndex = 1;
        for (int i = days[0]; i < 365; i++) {
            if (i < days[daysIndex] - 1) {
                dp[i] = dp[i - 1];//进行下一次旅行之前，费用不变
                continue;
            }
            /* 到了下一个旅行的日期，计算三种买票方式的花费
             * 下一个旅行的日期的花费有三种情况：
             * 1.重新买时长一天的票，花费为oneDayTicketCost
             * 2.此日期的车票已经在前一周买过，花费oneWeekTicketCost
             * 3.此日期的车票已经在前一月买过，花费oneMonthTicketCost
             * 取这三种情况的最小值作为此日期的花费*/
            int oneDayTicketCost = dp[i - 1] + costs[0];
            int oneWeekTicketCost = i - 7 >= 0 ? dp[i - 7] + costs[1] : costs[1];
            int oneMonthTicketCost = i - 30 >= 0 ? dp[i - 30] + costs[2] : costs[2];
            /* 最后一个测试用例是days=[1,4,6,7,8,20]，costs=[7,2,15]，其中7天票价比1天票价更便宜
             * 若仅进行上方计算，前7天的花费dp={7,7,7,2,2,2,2}，
             * 到了第8天，oneDayTicketCost=dp[6]+7=9，oneWeekTicketCost=dp[0]+2=9，导致dp[7]=9，
             * 而最低的花费应该是dp[7]=dp[6]+2=4，
             * 所以要进行以下比较，取最小值*/
            oneWeekTicketCost = Math.min(oneWeekTicketCost, Math.min(dp[i - 1] + costs[1], dp[i - 1] + costs[2]));
            oneMonthTicketCost = Math.min(oneMonthTicketCost, Math.min(dp[i - 1] + costs[1], dp[i - 1] + costs[2]));
            //取其中的最小值
            dp[i] = Math.min(oneDayTicketCost, Math.min(oneWeekTicketCost, oneMonthTicketCost));
            daysIndex++;
            if (daysIndex == numDays) {
                break;//所有要旅行的天数都已经计算完，退出循环
            }
        }
        return dp[days[numDays - 1] - 1];
    }

    public static void main(String[] args) {
        /*int[] days = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        int[] cost = {2, 7, 15};*/
        /*int[] days = {1, 4, 6, 7, 8, 20};
        int[] cost = {2, 7, 15};*/
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] cost = {7, 2, 15};
        Solution solution = new Solution();
        int minCost = solution.mincostTickets(days, cost);
        System.out.println(minCost);
    }
}
