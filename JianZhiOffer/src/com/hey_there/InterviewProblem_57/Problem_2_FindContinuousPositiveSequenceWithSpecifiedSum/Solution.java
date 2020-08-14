package com.hey_there.InterviewProblem_57.Problem_2_FindContinuousPositiveSequenceWithSpecifiedSum;

import java.util.LinkedList;

public class Solution {
    public int[][] findContinuousSequence(int target) {
        LinkedList<int[]> list = new LinkedList<>();//list存储所有可能的情况
        int low = 1, high = 2;
        int sum = low + high;
        while (high < (target + 1) / 2) {
            while (sum < target) {
                high++;
                sum += high;
            }
            //当sum的值不小于target时停止累加
            if (sum > target) {//若sum的值超过了target，则缩小求和范围
                sum -= (low + high);
                low++;
                high--;
            } else {//sum的值等于target，则添加一条记录
                //添加前检查high是否等于target，等于则不添加
                //if (high == target) continue;

                int[] oneAns = new int[high - low + 1];
                for (int i = 0; i < oneAns.length; i++) {
                    oneAns[i] = low + i;
                }
                list.add(oneAns);
                //添加完一条记录，将low右移一位
                sum -= low;
                low++;
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
