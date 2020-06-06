package com.hey_there.Array.InsertInterval;

public class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //intervals数组中的区间数量
        int oldIntervalNum = intervals.length;

        //尝试将newInterval与intervals数组中某个区间进行合并
        int mergingIdx = -1;//记录intervals数组中与newInterval进行合并的区间
        for (int i = 0; i < oldIntervalNum; i++) {
            //newInterval区间的起点在intervals[i]区间的范围内则可进行合并
            //intervals[i]区间的起点在newInterval区间的范围内也可进行合并
            if ((intervals[i][0] <= newInterval[0] && newInterval[0] <= intervals[i][1]) ||
                    (newInterval[0] <= intervals[i][0] && intervals[i][0] <= newInterval[1])) {
                mergingIdx = i;
                //[1,2],[5,7],[10,12]
                //[4,8]
                //合并后的区间，左端点二者取最小，右端点二者取最大
                intervals[i][0] = Math.min(intervals[i][0], newInterval[0]);
                intervals[i][1] = Math.max(intervals[i][1], newInterval[1]);
                break;
            }
        }

        //检查newInterval是否已与intervals数组中某个区间合并
        if (mergingIdx == -1) {
            //mergingIdx值仍为-1，表示newInterval与intervals数组中所有区间都不相交
            int[][] ansIntervals = new int[oldIntervalNum + 1][2];
            int ansIdx = 0;//答案数组中下一个被插入的行下标

            int oldIdx=0;//intervals数组中下一个待插入的行下标
            while (oldIdx < oldIntervalNum) {
                if (intervals[oldIdx][0] < newInterval[0]) {
                    ansIntervals[ansIdx][0] = intervals[oldIdx][0];
                    ansIntervals[ansIdx][1] = intervals[oldIdx][1];
                    oldIdx++;
                } else {
                    ansIntervals[ansIdx][0] = newInterval[0];
                    ansIntervals[ansIdx][1] = newInterval[1];
                    //newInterval区间加入新区间数组后，将其起止点均置为“无穷大”
                    //让newInterval不会被再次插入
                    newInterval[0] = Integer.MAX_VALUE;
                    newInterval[1] = Integer.MAX_VALUE;
                }
                ansIdx++;
            }
            //ansIdx小于等于oldIntervalNum表示newInterval排在最后，在循环中没有被加入
            if (ansIdx <= oldIntervalNum) {
                ansIntervals[ansIdx][0] = newInterval[0];
                ansIntervals[ansIdx][1] = newInterval[1];
            }
            return ansIntervals;
        } else {
            //mergingIdx不为-1，表示newInterval与intervals数组中某区间进行了合并
            //此时需要检查新区间能否与interval数组中后面的区间合并
            int intervalNumAfterMerging = oldIntervalNum;
            for (int i = mergingIdx + 1; i < oldIntervalNum; i++) {
                //后面区间的起点在新区间的范围内则可进行合并
                if (intervals[mergingIdx][0] <= intervals[i][0] &&
                        intervals[i][0] <= intervals[mergingIdx][1]) {
                    //此时合并区间仅需要改变右端点的值
                    intervals[mergingIdx][1] = Math.max(intervals[mergingIdx][1], intervals[i][1]);
                    //区间数量减1
                    intervalNumAfterMerging--;
                    //将第i个区间的起止点置为一个“无效值”
                    intervals[i][0] = Integer.MAX_VALUE;
                    intervals[i][1] = Integer.MAX_VALUE;
                } else {
                    //遇到第一个无法合并的区间，则后方的区间都不能合并
                    break;
                }
            }
            //新建一个数组，将intervals数组中的有效区间复制到新数组
            int[][] ansIntervals = new int[intervalNumAfterMerging][2];
            int ansIdx = 0;
            for (int[] interval : intervals) {
                if (interval[0] != Integer.MAX_VALUE) {
                    ansIntervals[ansIdx][0] = interval[0];
                    ansIntervals[ansIdx][1] = interval[1];
                    ansIdx++;
                }
            }
            return ansIntervals;
        }
    }

    public static void main(String[] args) {
        /*int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {2, 5};*/
        /*int[][] intervals = {{1, 3}, {6, 9}};
        int[] newInterval = {4, 5};*/
        int[][] intervals = {{2, 4}, {6, 9}};
        int[] newInterval = {1, 5};

        Solution solution = new Solution();
        int[][] ansInterval = solution.insert(intervals, newInterval);
        for (int[] interval : ansInterval) {
            for (int i : interval) {
                System.out.print(i + "   ");
            }
            System.out.println();
        }
    }
}
