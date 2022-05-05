package com.hey_there._713_SubarrayProductLessThanK;

class Solution {
    //空间复杂度O(n^2)，超出限制
    public int numSubarrayProductLessThanK_1(int[] nums, int k) {
        int len = nums.length;
        int count = 0;
        //dp[i][j]表示闭区间[i,j]内数组元素的乘积
        int[][] dp = new int[len][len];
        if (nums[0] < k) count++;
        dp[0][0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i][i] = nums[i];//长度为1的子数组的乘积
            //单个元素大于等于k，直接进行下一次循环
            if (nums[i] >= k) continue;
            //当前元素小于k，向前计算乘积，直到乘积不小于k
            count++;
            int prod = nums[i];
            for (int j = i - 1; j >= 0 && prod < k; j--) {
                prod = dp[j][i - 1] * nums[i];
                dp[j][i] = prod;
                if (prod < k) count++;
            }
        }
        return count;
    }

    //空间复杂度下来了，但动态规划了个寂寞
    public int numSubarrayProductLessThanK_2(int[] nums, int k) {
        int len = nums.length;
        int count = 0;
        int[] dp = new int[len];
        if (nums[0] < k) count++;
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = nums[i];
            //单个元素大于等于k，直接进行下一次循环
            if (nums[i] >= k) continue;
            //当前元素小于k，向前计算乘积，直到乘积不小于k
            count++;
            for (int j = i - 1; j >= 0; j--) {
                dp[j] *= nums[i];
                if (dp[j] >= k) break;
                count++;
            }
        }
        return count;
    }

    //滑动窗口
    public int numSubarrayProductLessThanK_3(int[] nums, int k) {
        int count = 0;
        int left = 0;//滑动窗口的左边界
        int prod = 1;//乘积
        //枚举右边界
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];//计算乘积
            //对于当前右边界，右移左边界，直到乘积小于k
            while (left <= right && prod >= k) {
                prod /= nums[left];
                left++;
            }
            //退出内层循环时，闭区间[left,right]内元素的乘积小于k
            //此时左边界为闭区间[left,right]中的任意值，均满足prod<k
            count += (right - left + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        /*int[] nums = {10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3};
        int k = 19;*/
        int[] nums = {10, 5, 2, 6};
        int k = 100;
        Solution solution = new Solution();
        int count = solution.numSubarrayProductLessThanK_3(nums, k);
        System.out.println(count);
    }
}
