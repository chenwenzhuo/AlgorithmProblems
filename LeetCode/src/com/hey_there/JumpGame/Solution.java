package com.hey_there.JumpGame;

public class Solution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] > 0) {//值大于0，表示从此位置可以向后走
                continue;
            }

            //值为0时，从此位置无法向后走
            //向前检查，是否能跳过当前位置
            boolean canSkipCurPos = false;
            for (int j = i - 1; j >= 0; j--) {
                //若前方某位置j的值大于当前位置i与位置j之间的距离，则从位置j可跳过位置i向后走
                if (nums[j] > i - j) {
                    canSkipCurPos = true;
                    break;
                }
            }

            if (!canSkipCurPos) {//若不能跳过当前位置，则无法继续
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 0};
        Solution solution = new Solution();
        System.out.println(solution.canJump(nums));
    }
}
