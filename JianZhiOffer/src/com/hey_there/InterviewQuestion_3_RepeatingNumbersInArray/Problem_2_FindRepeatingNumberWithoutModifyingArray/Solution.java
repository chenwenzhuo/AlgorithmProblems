package com.hey_there.InterviewQuestion_3_RepeatingNumbersInArray.Problem_2_FindRepeatingNumberWithoutModifyingArray;

public class Solution {
    public int findRepeatingWithoutModifyingArray(int[] nums) {
        int lenNums = nums.length;
        int val_low = 1;//数组中的最小值
        int val_high = lenNums - 1;//数组中的最大值
        int val_mid;//中间值
        //计数器，分别对 val_low~val_mid 和 val_mid+1~val_high 两个范围内的数进行计数
        int counter_lowerHalf, counter_higherHalf;

        //在val_high与val_low相等时推出循环，此时范围内仅有一个值，即为所求重复值
        while (val_high > val_low) {
            //每次循环都需要重新计算中间值，并对计数器清零
            val_mid = (val_low + val_high) / 2;
            counter_lowerHalf = 0;
            counter_higherHalf = 0;
            //枚举数组中所有值，检查是否在 val_low~val_mid 和 val_mid+1~val_high 范围内
            //在范围内则对应计数器加1
            for (int n : nums) {
                if (val_low <= n && n <= val_mid) {
                    counter_lowerHalf++;
                } else if (val_mid + 1 <= n && n <= val_high) {
                    counter_higherHalf++;
                }
            }
            //枚举完成后更新val_low或val_high
            if (counter_lowerHalf > val_mid - val_low + 1) {
                val_high = val_mid;
                //加上continue确保只更新val_low和val_high其中之一
                //因为数组中可能有多个重复值，可能导致两个if判断均成立
                continue;
            }
            if (counter_higherHalf > val_high - val_mid) {
                val_low = val_mid + 1;
            }
        }
        return val_low;
    }

    public static void main(String[] args) {
        //int[] nums = {2, 5, 1, 4, 3, 5};
        int[] nums = {2, 3, 5, 4, 3, 2, 6, 7};
        Solution solution = new Solution();
        int ans = solution.findRepeatingWithoutModifyingArray(nums);
        System.out.println(ans);
    }
}
