package com.hey_there.MinimumSizeSubarraySum;

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len_nums = nums.length;
        int sub_size = len_nums + 1;//求最小值，其初值应设为一个较大的值
        int low = 0, high = -1;
        int sub_sum = 0;//[low,high]闭区间内的子数组的和

        while (true) {
            //不断移动high，直到sub_sum超过s
            while (high < len_nums - 1) {
                high++;
                sub_sum += nums[high];
                if (sub_sum >= s) {
                    sub_size = Math.min(sub_size, high - low + 1);
                    break;
                }
            }
            //从上方while中退出后若sub_sum小于s，直接退出
            if (sub_sum < s) {
                break;
            }
            //不断移动low，直到sub_sum小于s
            while (low <= high) {
                sub_sum -= nums[low];
                low++;
                if (sub_sum < s) {
                    sub_size = Math.min(sub_size, high - low + 2);
                    break;
                } else if (sub_sum == s) {
                    sub_size = Math.min(sub_size, high - low + 1);
                    break;
                }
            }
        }
        return sub_size == len_nums + 1 ? 0 : sub_size;
    }

    public static void main(String[] args) {
        /*int[] nums = {2, 3, 1, 2, 4, 3};
        int s = 7;*/
        /*int[] nums = {1, 4, 4};
        int s = 4;*/
        int[] nums = {1, 2, 3, 4, 5};
        int s = 11;
        /*int[] nums = {1, 1};
        int s = 3;*/

        Solution solution = new Solution();
        int ans = solution.minSubArrayLen(s, nums);
        System.out.println(ans);
    }
}
