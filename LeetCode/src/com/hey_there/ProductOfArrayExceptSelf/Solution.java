package com.hey_there.ProductOfArrayExceptSelf;

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        //leftProd[i]表示nums[0]~nums[i-1]闭区间内所有数的乘积
        //规定leftProd[0]为1
        int[] leftProd = new int[len];
        leftProd[0] = 1;
        for (int i = 1; i < len; i++) {
            leftProd[i] = leftProd[i - 1] * nums[i - 1];
        }
        //rightProd表示从后往前所有数的乘积
        int rightProd = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            leftProd[i] *= rightProd;
            rightProd *= nums[i];
        }
        return leftProd;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        Solution solution = new Solution();
        int[] ans = solution.productExceptSelf(nums);

        for (int a : ans) {
            System.out.print(a + "   ");
        }
        System.out.println();
    }
}
