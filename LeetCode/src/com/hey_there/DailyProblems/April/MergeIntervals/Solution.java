package com.hey_there.DailyProblems.April.MergeIntervals;

public class Solution {
    public int[][] merge(int[][] intervals) {
        int numIntervals = intervals.length;
        //区间数量小于2则无需合并
        if (numIntervals < 2) {
            return intervals;
        }
        int numItvsAfterMerging = numIntervals;
        for (int i = 0; i < numIntervals; i++) {
            if (intervals[i] == null) {
                continue;
            }
            for (int j = i + 1; j < numIntervals; j++) {
                //intervals[j]为null或区间intervals[i]与intervals[j]不重叠则继续检查下一个
                if (intervals[j] == null || !intervalOverlap(intervals[i], intervals[j])) {
                    continue;
                }
                //若两区间重叠则将其合并
                //将两区间合并到intervals[j]
                intervals[j][0] = Math.min(intervals[i][0], intervals[j][0]);
                intervals[j][1] = Math.max(intervals[i][1], intervals[j][1]);
                intervals[i] = null;//清除intervals[i]
                numItvsAfterMerging--;//合并后区间数量减一
                break;//合并后intervals[i]为null，所以直接退出内层循环
            }
        }

        int[][] ans = new int[numItvsAfterMerging][];
        int ansIndex = 0;
        for (int[] interval : intervals) {
            if (interval != null) {
                ans[ansIndex] = interval;
                ansIndex++;
            }
        }
        return ans;
    }

    private boolean intervalOverlap(int[] itv_1, int[] itv_2) {
        //将两个区间按起点大小排序
        int[] smaller, bigger;
        if (itv_1[0] < itv_2[0]) {
            smaller = itv_1;
            bigger = itv_2;
        } else {
            smaller = itv_2;
            bigger = itv_1;
        }

        return bigger[0] <= smaller[1];
    }

    public static void main(String[] args) {
        //int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals = {{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
        Solution solution = new Solution();
        int[][] ans = solution.merge(intervals);
        for (int[] ansRow : ans) {
            for (int a : ansRow) {
                System.out.print(a + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
