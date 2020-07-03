package com.hey_there.MaximumProductSubarray;

public class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0];//nums的所有子数组的乘积中的最大值
        int curMax = 1;//以当前nums[i]结尾的子数组的乘积的最大值
        int curMin = 1;//以当前nums[i]结尾的子数组的乘积的最小值
        for (int n : nums) {
            if (n < 0) {
                //当前数字小于0时，交换curMax和curMin的值
                int temp = curMax;
                curMax = curMin;
                curMin = temp;
            }
            //更新max，curMax，curMin的值
            curMax = Math.max(n, curMax * n);
            curMin = Math.min(n, curMin * n);
            max = Math.max(max, curMax);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, -1};
        Solution solution = new Solution();
        int ans = solution.maxProduct(nums);
        System.out.println(ans);
    }
}
