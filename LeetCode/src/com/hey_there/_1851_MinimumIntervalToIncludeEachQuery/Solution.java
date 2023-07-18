package com.hey_there._1851_MinimumIntervalToIncludeEachQuery;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = intervals.length, m = queries.length;
        //对intervals按区间起点，升序排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        //使用一个新的qs数组代替queries，其中包含了每个查询的顺序
        int[][] qs = new int[m][];
        for (int i = 0; i < m; ++i) {
            qs[i] = new int[]{queries[i], i};
        }
        //将所有查询按升序排序
        Arrays.sort(qs, (a, b) -> a[0] - b[0]);
        int[] ans = new int[m];
        Arrays.fill(ans, -1);
        //小顶堆，记录二元组，分别表示区间长度、区间终点
        //堆中二元组按区间长度升序排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int i = 0;//intervals的下标
        //逐个进行查询
        for (int[] q : qs) {
            //将所有的起点小于当前查询的区间加入小顶堆
            while (i < n && intervals[i][0] <= q[0]) {
                int a = intervals[i][0], b = intervals[i][1];
                pq.offer(new int[]{b - a + 1, b});
                ++i;
            }
            //逐个检查当前查询是否落在区间中，不包含当前查询的区间被删除
            //由于堆中区间按长度升序排序，第一个满足的区间即为最小区间
            while (!pq.isEmpty() && pq.peek()[1] < q[0]) {
                pq.poll();
            }
            //堆非空则有解，赋值到ans数组对应位置
            if (!pq.isEmpty()) {
                ans[q[1]] = pq.peek()[0];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = {{1, 4}, {2, 4}, {3, 6}, {4, 4}};
        int[] queries = {2, 3, 4, 5};
        int[] ans = solution.minInterval(intervals, queries);
        for (int a : ans)
            System.out.print(a + " ");
    }
}
