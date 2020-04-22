package com.hey_there.InterviewQuestion_3_RepeatingNumbersInArray.Problem_1_FindRepeatingNumber;

import java.util.Arrays;

public class Solution {
    /* 对数组排序，相同的数字一定相邻
     * 排序需要O(n*log n)的时间复杂度，空间复杂度为O(1)*/
    public int findRepeating_sort(int[] nums) {
        Arrays.sort(nums);//排序
        for (int i = 1; i < nums.length; i++) {
            //找到重复的就返回
            if (nums[i] == nums[i - 1]) {
                return i;
            }
        }
        //没有重复的返回-1
        return -1;
    }

    /* 由于nums数组中数字范围为0~n-1，
     * 所以可将nums中下标为i的数字n映射到另一个数组assist的下标为n的位置，
     * 两个数字映射到同一个位置即为重复
     * 时间复杂度O(n)，空间复杂度O(n)*/
    public int findRepeating_assistArray(int[] nums) {
        int[] assist = new int[nums.length];
        Arrays.fill(assist, -1);//使用无效数字-1来初始化hash数组
        for (int n : nums) {
            if (assist[n] != n) {
                assist[n] = n;
            } else {
                return n;
            }
        }
        return -1;
    }

    /*时间复杂度O(n)，空间复杂度O(1)*/
    public int findRepeating_swap(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            //当下标为 i 的位置的值不等于 i 时才进行操作
            while (nums[i] != i) {
                int val_pos_i = nums[i];
                //下标为 val_pos_i 的位置的值不为 val_pos_i 时，进行交换
                if (nums[val_pos_i] != val_pos_i) {
                    swap(nums, i, nums[i]);
                } else {
                    //下标为 val_pos_i 的位置的值等于 val_pos_i 时表示找到重复值
                    return nums[i];
                }
            }
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 2, 2};
        Solution solution = new Solution();
        System.out.println(solution.findRepeating_swap(nums));
    }
}
