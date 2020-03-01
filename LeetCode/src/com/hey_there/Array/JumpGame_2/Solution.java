package com.hey_there.Array.JumpGame_2;

public class Solution {
    public int jump_my(int[] nums) {
        int totalSteps = 0;
        int len = nums.length;

        int index = 0;
        while (index < len - 1) {
            //在当前位置，走一步再走一步后能到达的最远位置
            int farthestCapable = -1;
            int nextStepLen = 1;//下一步要走的距离长度(根据题目条件，至少要走一步)

            int tempNextStepLen = 1;
            while (tempNextStepLen <= nums[index]) {
                //检查从当前位置能否一步到达最终位置
                if (index + tempNextStepLen >= len - 1) {
                    //能一步到达则直接结束
                    totalSteps++;
                    return totalSteps;
                }
                //不能一步到达则计算走一步再走一步的最远距离
                if (index + tempNextStepLen < len &&
                        index + tempNextStepLen + nums[index + tempNextStepLen] > farthestCapable) {
                    farthestCapable = index + tempNextStepLen + nums[index + tempNextStepLen];
                    nextStepLen = tempNextStepLen;
                }
                tempNextStepLen++;
            }
            index += nextStepLen;
            totalSteps++;
        }
        return totalSteps;
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
        System.out.println(solution.jump(nums));
    }
}
