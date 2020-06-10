package com.hey_there.Array.PlusOne;

public class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        //个位不超过8时，直接将个位加1，返回
        if (digits[len - 1] <= 8) {
            digits[len - 1] += 1;
            return digits;
        }
        //个位为9时，会产生进位
        int carry = 1;
        digits[len - 1] = 0;//将个位置为0
        //从十位开始，处理进位
        for (int i = len - 2; i >= 0; i--) {
            digits[i] += carry;//加上进位值
            if (digits[i] < 10) {
                //没有再次产生进位，则结束计算
                carry = 0;
                break;
            } else {
                //再次产生进位，则当前位需要置为0
                digits[i] = 0;
            }
        }
        if (carry == 0) {
            //结束计算后进位为0，则直接返回digit数组
            return digits;
        } else {
            //结束计算后进位不为0，表示原数组的最高位产生了进位
            //需要一个更长的数组存储答案
            int[] extended = new int[len + 1];
            extended[0]=carry;
            System.arraycopy(digits, 0, extended, 1, len);
            return extended;
        }
    }

    public static void main(String[] args) {
        //int[] digits = {1, 2, 3};
        //int[] digits = {1, 2, 9};
        int[] digits = {9, 9, 9};
        Solution solution = new Solution();
        int[] ans = solution.plusOne(digits);
        for (int digit : ans) {
            System.out.print(digit + "  ");
        }
        System.out.println();
    }
}
