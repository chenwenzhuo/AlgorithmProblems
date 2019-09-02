package com.hey_there;

public class Main {

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 8, 16, 32, 64, 128};
        Solution solution = new Solution();
        System.out.println("最接近的和：" + solution.threeSumClosest(nums, 82));
    }
}
