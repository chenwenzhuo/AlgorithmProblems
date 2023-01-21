package com.hey_there._1824_MinimumSidewayJumps;

public class Solution {
    public int minSideJumps_1(int[] obstacles) {
        int n = obstacles.length - 1;//跑道长度为n
        //将跑道编号为0，1，2，obstacles[i]为-1时表示没有障碍
        for (int i = 0; i <= n; i++)
            obstacles[i]--;
        //dp[i][j]表示跳到点i处的跑道j，需要的最少侧跳次数
        //若点i处的跑道j有障碍物，将dp[i][j]设为无穷大
        int[][] dp = new int[n + 1][3];
        dp[0][0] = dp[0][2] = 1;//base case：侧跳一次到达起点位置的另两条跑道
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                //点i处的跑道j有障碍物，无法到达此位置，设为无穷大
                if (j == obstacles[i]) {
                    dp[i][j] = Integer.MAX_VALUE;
                    continue;
                }
                //位置(i,j)没有障碍物，则可以从点i-1到达点i
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    //位置(i-1,k)有障碍，无法从这里到达(i,j)
                    if (k == obstacles[i - 1])
                        continue;
                    //点i的跑道k有障碍，且点i-1的跑道j有障碍，则从位置(i-1,k)到达(i,j)需要侧跳2次
                    if (k == obstacles[i] && j == obstacles[i - 1])
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + 2);
                    else if (j != k)//其余情况下，若跨跑道跳，需要侧跳一次
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + 1);
                    else//否则无需侧跳，直接从位置(i-1,k)到达(i,j)
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k]);
                }
            }
        }
        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }

    public int minSideJumps_2(int[] obstacles) {
        int n = obstacles.length - 1;//跑道长度为n
        //将跑道编号为0，1，2，obstacles[i]为-1时表示没有障碍
        for (int i = 0; i <= n; i++)
            obstacles[i]--;
        //dp[i][j]表示跳到点i处的跑道j，需要的最少侧跳次数
        //若点i处的跑道j有障碍物，将dp[i][j]设为无穷大
        int[][] dp = new int[n + 1][3];
        dp[0][0] = dp[0][2] = 1;//base case：侧跳一次到达起点位置的另两条跑道
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 3; j++) {
                //点i处的跑道j有障碍物，无法到达此位置，设为无穷大
                if (j == obstacles[i]) {
                    dp[i - 1][j] = Integer.MAX_VALUE;
                    break;
                }
            }
            int x = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2])) + 1;
            for (int j = 0; j < 3; j++) {
                if (j != obstacles[i])
                    dp[i][j] = Math.min(dp[i - 1][j], x);
                else
                    dp[i][j] = Integer.MAX_VALUE;
            }
        }
        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }

    public static void main(String[] args) {
        int[] obstacles = {0, 1, 2, 3, 0};
        Solution solution = new Solution();
        int res = solution.minSideJumps_1(obstacles);
        System.out.println(res);
    }
}
