package com.hey_there._875_KokoEatingBananas;

public class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int biggestPile = 0;
        for (int p : piles)
            biggestPile = Math.max(biggestPile, p);
        int low = 1, high = biggestPile;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            //计算以速度mid，吃完所有香蕉需要的时间
            long time = 0;
            for (int p : piles)
                time += ((p + mid - 1) / mid);//对p/mid向上取整
            if (time > h)//需要的时间大于h，则加快速度
                low = mid + 1;
            else//需要的时间小于等于h，可以尝试减慢
                high = mid - 1;
        }
        return low;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] piles = {805306368, 805306368, 805306368};
        int h = 1000000000;
        int res = solution.minEatingSpeed(piles, h);
        System.out.println(res);
    }
}
