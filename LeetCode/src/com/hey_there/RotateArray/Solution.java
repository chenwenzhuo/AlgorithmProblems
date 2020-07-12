package com.hey_there.RotateArray;

public class Solution {
    /* 将一个位置的数组元素循环右移k个位置，
     * 再将目标位置的元素循环右移k个位置，
     * 循环n次*/
    public void rotate_1(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;//用来记录已经对多少个数组元素进行了移动
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    public void rotate_2(int[] nums, int k) {
        int len_nums = nums.length;
        k %= len_nums;
        //反转数组
        reverseArrayRange(nums, 0, len_nums - 1);
        //反转前k个数组元素
        reverseArrayRange(nums, 0, k - 1);
        //反转后n-k个数组元素
        reverseArrayRange(nums, k, len_nums - 1);
    }

    private void reverseArrayRange(int[] nums, int left, int right) {
        int temp;
        while (left < right &&
                0 <= left && left < nums.length &&
                0 <= right && right < nums.length) {
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        /*int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;*/
        int[] nums = {-1, -100, 3, 99};
        int k = 2;
        Solution solution = new Solution();
        solution.rotate_1(nums, k);
        for (int n : nums) {
            System.out.print(n + "   ");
        }
        System.out.println();
    }
}
