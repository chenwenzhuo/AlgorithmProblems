package com.hey_there._2731_MovementOfRobots;

import java.util.Arrays;

public class Solution {
    public int sumDistance(int[] nums, String s, int d) {
        /* “当两个机器人相撞时，它们开始沿着原本相反的方向移动”
         * 但由于机器人之间没有区别，可将碰撞看作穿透，即不处理碰撞*/
        int n = nums.length;
        char[] chs = s.toCharArray();
        long[] pos = new long[n];//最终位置
        for (int i = 0; i < n; i++) {
            if (chs[i] == 'L')
                pos[i] = (long) nums[i] - d;
            else pos[i] = (long) nums[i] + d;
        }
        Arrays.sort(pos);
        long ans = 0, MOD = 1000000007;
        for (int i = 1; i < n; i++) {
            /* 假设a ,b,c,d从小大到依次排列，那么d-a = d-c + c-b + b-a，
             * 即任意两点之间的距离都可以分解为两点之间的所有点之间的距离和，也就是两点之间所有相邻点的距离和。
             * 所以只需计算相邻两点之间的距离再乘以他们会被使用的次数。
             * 按上述方法拆分，pos[i]-pos[i-1]会出现在pos[i]右边和pos[i-1]左边的任意一对数的距离计算的分解式中，
             * 也就是会被计算i*(n-i)次。为避免溢出，乘上i和n-i后都对MOD取余。*/
            ans += (pos[i] - pos[i - 1]) * i % MOD * (n - i) % MOD;
            ans %= MOD;
        }
        return (int) ans;
    }
}
