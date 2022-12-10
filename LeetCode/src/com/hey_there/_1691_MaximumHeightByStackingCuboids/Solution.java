package com.hey_there._1691_MaximumHeightByStackingCuboids;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public int maxHeight(int[][] cuboids) {
        for (int[] cub : cuboids)//将每一个数组排序
            Arrays.sort(cub);
        //数组间按总长度由小到大排序
        Arrays.sort(cuboids, new Comparator<int[]>() {
            @Override
            public int compare(int[] c1, int[] c2) {
                return (c1[0] + c1[1] + c1[2]) - (c2[0] + c2[1] + c2[2]);
            }
        });
        int length = cuboids.length;
        int ans = 0;
        //dp[i]表示以方块i为底部的最高高度
        //base case：dp[0]为cuboids[i][2]，每个方块至少能叠成它自己的高度
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = cuboids[i][2];
            for (int j = 0; j < i; j++) {
                if (cuboids[i][0] >= cuboids[j][0] &&
                        cuboids[i][1] >= cuboids[j][1] &&
                        cuboids[i][2] >= cuboids[j][2])
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
