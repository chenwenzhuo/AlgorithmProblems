package com.hey_there._494_TargetSum;

public class Solution_1 {
    //回溯算法
    public int findTargetSumWays(int[] nums, int target) {
        return backtrack(nums, nums.length - 1, target);
    }

    //求使用数组中下标[0,range]闭区间的数，计算出值target的方式数
    private int backtrack(int[] nums, int range, int target) {
        if (range == 0) {//range为0，即可使用的数只有一个
            if (nums[0] == 0 && target == 0)
                return 2;
            if (Math.abs(nums[0]) == Math.abs(target))
                return 1;
            return 0;
        }
        //range非0
        return backtrack(nums, range - 1, target + nums[range]) +
                backtrack(nums, range - 1, target - nums[range]);
    }

    public static void main(String[] args) {
        /*int[] nums = {1, 1, 1, 1, 1};
        int target = 3;*/
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 1};
        int target = 1;
        Solution_1 solution = new Solution_1();
        int res = solution.findTargetSumWays(nums, target);
        System.out.println(res);
    }
}
