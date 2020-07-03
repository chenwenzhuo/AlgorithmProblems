package com.hey_there.JumpGame_2;

import java.util.Arrays;

public class Solution {
    public int jump_my_1(int[] nums) {
        int totalSteps = 0;
        int len = nums.length;

        int index = 0;
        while (index < len - 1) {
            //在当前位置，走两步后能到达的最远位置
            int farthestWith2Steps = -1;
            int nextStepLen = 1;//下一步要走的距离长度(根据题目条件，至少要走一步)

            int tempNextStepLen = 1;
            //枚举在当前位置能走的所有距离，从1到nums[index]
            while (tempNextStepLen <= nums[index]) {
                //检查从当前位置能否一步到达最终位置
                if (index + tempNextStepLen >= len - 1) {
                    //能一步到达则直接结束
                    totalSteps++;
                    return totalSteps;
                }
                //不能一步到达则计算走两步的最远距离
                int tempFarthest = index + tempNextStepLen + nums[index + tempNextStepLen];
                if (index + tempNextStepLen < len && tempFarthest > farthestWith2Steps) {
                    //找到一种走法能到达更远的位置，则更新变量值
                    farthestWith2Steps = tempFarthest;
                    nextStepLen = tempNextStepLen;
                }
                tempNextStepLen++;
            }
            index += nextStepLen;//向后走一步
            totalSteps++;//总步数增加
        }
        return totalSteps;
    }

    //能通过所有测试用例但超时
    public int jump_my_2(int[] nums) {
        int lenNums = nums.length;
        //dp[i]表示到达下标为i的位置需要的最少步数
        int[] dp = new int[lenNums];
        Arrays.fill(dp, lenNums);//到达任意一个位置，最多不超过lenNums步
        dp[0] = 0;//初始位置所需步数为0

        //计算每一个dp[i]
        for (int i = 0; i < lenNums; i++) {
            int steps = 1;
            while (steps <= nums[i]) {
                int dest = i + steps;
                if (dest >= lenNums) {
                    //从当前位置走steps步后若越界则不再继续尝试
                    break;
                }
                dp[dest] = Math.min(dp[dest], dp[i] + 1);
                steps++;
            }
        }
        return dp[lenNums - 1];
    }

    public int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if (i == end) { //遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        Solution solution = new Solution();
        System.out.println(solution.jump_my_2(nums));
    }
}
