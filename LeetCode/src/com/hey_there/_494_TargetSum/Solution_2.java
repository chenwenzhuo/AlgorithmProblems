package com.hey_there._494_TargetSum;

import java.util.HashMap;

public class Solution_2 {
    private HashMap<String, Integer> memo = new HashMap<>();

    //带备忘录的回溯算法
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
        //range非0，需要回溯求解
        //回溯前先检查备忘录中是否有当前子问题的解
        String key1 = (range - 1) + "_" + (target + nums[range]);
        int subProblem1;
        if (memo.containsKey(key1)) subProblem1 = memo.get(key1);
        else {
            subProblem1 = backtrack(nums, range - 1, target + nums[range]);
            memo.put(key1, subProblem1);
        }
        String key2 = (range - 1) + "_" + (target - nums[range]);
        int subProblem2;
        if (memo.containsKey(key2)) subProblem2 = memo.get(key2);
        else {
            subProblem2 = backtrack(nums, range - 1, target - nums[range]);
            memo.put(key2, subProblem2);
        }
        return subProblem1 + subProblem2;
    }

    public static void main(String[] args) {
        /*int[] nums = {1, 1, 1, 1, 1};
        int target = 3;*/
        int[] nums = {0, 0, 0, 0, 0, 0, 0, 0, 1};
        int target = 1;
        Solution_2 solution = new Solution_2();
        int res = solution.findTargetSumWays(nums, target);
        System.out.println(res);
    }
}
