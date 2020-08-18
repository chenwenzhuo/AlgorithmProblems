package com.hey_there.InterviewProblem_61_StraightInPoker;

import java.util.Arrays;
import java.util.HashSet;

public class Solution {
    public boolean isStraight_mine(int[] nums) {
        Arrays.sort(nums);//排序原数组
        //将原数组转存到一个长为6的数组中
        int[] copiedNums = new int[nums.length + 1];
        System.arraycopy(nums, 0, copiedNums, 0, nums.length);

        int idx = 0;//数组下标
        //计算数组中0的数量
        int countZeroes = 0;
        while (idx < nums.length) {
            if (nums[idx] != 0) break;//遇到非0的数，退出循环
            countZeroes++;
            idx++;
        }

        int countStraight = 1;//顺子长度，至少为1
        while (idx < nums.length && countStraight < 5) {
            //若当前位置的数字与下一个位置的数字相差为1，则这两个数构成顺子
            if (copiedNums[idx] + 1 == copiedNums[idx + 1]) {
                countStraight++;//顺子长度加1
                idx++;
                continue;
            }
            //当前位置的数字与下一个位置的数字相差不为1时，使用0来补位
            if (countZeroes > 0) {//有剩余的0时用0补位
                countZeroes--;
                copiedNums[idx]++;
                countStraight++;
            } else break;//没有剩余的0则退出
        }
        return countStraight == 5;
    }

    /* 给出的5张牌能够成顺子的充分条件：
     * 1.除大小王外，所有牌 无重复；
     * 2.设此5张牌中最大的牌为max，最小的牌为min（大小王除外），则需满足：max - min < 5*/
    public boolean isStraight(int[] nums) {
        HashSet<Integer> set = new HashSet<>();//集合去重
        int min = 100, max = -1;//记录最大最小牌
        for (int n : nums) {
            if (n == 0) continue;//不记录大小王
            if (set.contains(n)) return false;//遇到重复，直接返回false
            set.add(n);//不重复的则加入集合
            //比较最大最小
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        return max - min < 5;
    }
}
