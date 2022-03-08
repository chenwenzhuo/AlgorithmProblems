package com.hey_there._2055_PlatesBetweenCandles;

public class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        char[] sChars = s.toCharArray();
        int sLen = s.length();
        //计算盘子数量的前缀和
        int[] preSum = new int[sLen];
        for (int i = 0, pSum = 0; i < sLen; i++) {
            if (sChars[i] == '*') pSum++;
            preSum[i] = pSum;
        }
        //left[i]表示下标i左边的第一个蜡烛的下标
        //right[i]表示下标i右边的第一个蜡烛的下标
        int[] left = new int[sLen];
        int[] right = new int[sLen];
        //l初值为-1，表示某些盘子左边没有蜡烛
        for (int i = 0, l = -1; i < sLen; i++) {
            if (sChars[i] == '|') l = i;//找到一个新的蜡烛，记录其下标
            left[i] = l;
        }
        //r初值为-1，表示某些盘子右边没有蜡烛
        for (int j = sLen - 1, r = -1; j >= 0; j--) {
            if (sChars[j] == '|') r = j;
            right[j] = r;
        }
        //计算queries[i]对应的答案
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            //对于查询queries[i]，左右边界相等，或左右边界值仅相差1，其间盘子数为0
            if (queries[i][1] <= queries[i][0] + 1) continue;
            int lCandle = right[queries[i][0]];//查询queries[i]，左边界右边的第一个蜡烛
            int rCandle = left[queries[i][1]];//查询queries[i]，右边界左边的第一个蜡烛
            res[i] = lCandle == -1 || rCandle == -1 || lCandle >= rCandle ?
                    0 : preSum[rCandle] - preSum[lCandle];
        }
        return res;
    }
}
