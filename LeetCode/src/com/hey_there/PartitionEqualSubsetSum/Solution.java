package com.hey_there.PartitionEqualSubsetSum;

import java.util.Arrays;

public class Solution {
    public boolean canPartition_1(int[] nums) {
        //计算数组中所有元素的和
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        //数组所有数字之和为奇数则无法分割
        if (sum % 2 == 1) {
            return false;
        }
        int capacity = sum / 2;//背包的容量
        //dp[i][j]表示用前i个物品去装容量为j的背包能否恰好装满
        //即，在nums数组里的前i个数中能否找出若干个数，使其和为j
        boolean[][] dp = new boolean[nums.length + 1][capacity + 1];
        //初始化base case，即数组的第一行和第一列
        Arrays.fill(dp[0], false);//第一行
        for (int i = 0; i <= nums.length; i++) {//第一列
            dp[i][0] = true;
        }
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][capacity];
    }

    public boolean canPartition_2(int[] nums) {
        //计算数组中所有元素的和
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        //数组所有数字之和为奇数则无法分割
        if (sum % 2 == 1) {
            return false;
        }
        int capacity = sum / 2;//背包的容量
        /* dp[i][j]表示用前i个物品去装容量为j的背包能否恰好装满
         * 即，在nums数组里的前i个数中能否找出若干个数，使其和为j。
         * 在canPartition_1中，下一行的仅基于前一行得出，故可使用一个两行的数组代替*/
        boolean[][] dp = new boolean[2][capacity + 1];
        //初始化base case，即数组的第一行
        Arrays.fill(dp[0], false);//第一行
        dp[0][0] = true;
        dp[1][0] = true;

        int curRow = 0, anotherRow = 0;
        for (int i = 1; i <= nums.length; i++) {
            curRow = i % 2;
            anotherRow = (i + 1) % 2;
            for (int j = 1; j <= capacity; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[curRow][j] = dp[anotherRow][j];
                } else {
                    dp[curRow][j] = dp[anotherRow][j] || dp[anotherRow][j - nums[i - 1]];
                }
            }
        }
        return dp[curRow][capacity];
    }

    public boolean canPartition_3(int[] nums) {
        //计算数组中所有元素的和
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        //数组所有数字之和为奇数则无法分割
        if (sum % 2 == 1) {
            return false;
        }
        int capacity = sum / 2;//背包的容量
        boolean[] dp = new boolean[capacity + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = capacity; j >= 0; j--) {
                if (j - num >= 0) {
                    dp[j] = dp[j] || dp[j - num];
                }
            }
        }
        return dp[capacity];
    }

    public boolean canPartition_4(int[] nums) {
        //计算数组中所有元素的和
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        //数组所有数字之和为奇数则无法分割
        if (sum % 2 == 1) {
            return false;
        }
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
        return dfs(nums, 0, sum / 2);
    }

    private boolean dfs(int[] nums, int idx, int target) {
        if (target == 0) {
            return true;
        }
        if (idx == nums.length || target < nums[idx]) {
            return false;
        }
        return dfs(nums, idx + 1, target - nums[idx]) ||
                dfs(nums, idx + 1, target);
    }

    public static void main(String[] args) {
        int[] nums = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
        Solution solution = new Solution();
        boolean ans = solution.canPartition_4(nums);
        System.out.println(ans);
    }
}
