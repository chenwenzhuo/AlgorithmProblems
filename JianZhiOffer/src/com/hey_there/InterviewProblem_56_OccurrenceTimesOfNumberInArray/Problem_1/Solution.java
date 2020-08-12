package com.hey_there.InterviewProblem_56_OccurrenceTimesOfNumberInArray.Problem_1;

public class Solution {
    public int[] singleNumbers(int[] nums) {
        //求数组中所有数字的异或值
        int xor = 0;
        for (int n : nums)
            xor ^= n;
        //在xor的二进制表示中任找一个为1的位（此处选取最低的为1的位）
        int digit1 = 1;
        while ((xor & digit1) == 0) {//按位与的值为0时继续循环
            digit1 <<= 1;//将digit1左移一位
        }
        //计算数组元素分为两组后，各自的异或值
        int xorGroup_1 = 0, xorGroup_2 = 0;
        for (int n : nums) {
            if ((n & digit1) == 0) xorGroup_1 ^= n;
            else xorGroup_2 ^= n;
        }
        return new int[]{xorGroup_1, xorGroup_2};
    }
}
