package com.hey_there.NextPermutation;

public class Solution {
    public void nextPermutation(int[] nums) {
        int len = nums.length;//获取数组长度

        int loop = len - 1;
        int target = -1;
        for (; loop > 0; loop--) {//从后往前遍历数组
            if (nums[loop] <= nums[loop - 1]) {//若从后往前是升序，则继续循环
                continue;
            }
            //遇到从后往前两个数是降序时
            target = loop - 1;//记录下 loop-1 的值
            break;
        }

        //若没有找到从后往前呈降序的两个数，则反转数组，然后结束
        if (target == -1) {
            reverseSubArray(nums, 0, len - 1);
            return;
        }

        //若找到从后往前呈降序的两个数，再次从后往前遍历，找到一个比target位置的数大的数
        for (int i = len - 1; i > 0; i--) {
            if (nums[i] <= nums[target]) {
                continue;
            }
            //找到后交换两个数的位置
            int temp = nums[target];
            nums[target] = nums[i];
            nums[i] = temp;
            break;
        }

        //反转nums数组中target位置之后的数
        reverseSubArray(nums, target + 1, len - 1);
    }

    private void reverseSubArray(int[] nums, int start, int end) {
        while (start < end) {
            //交换
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            //更新start，end
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        //int[] nums = {1, 2, 3, 4, 5};
        int[] nums = {5, 1, 1};
        Solution solution = new Solution();
        solution.nextPermutation(nums);

        for (int n : nums) {
            System.out.print(n + "  ");
        }
        System.out.println();
    }
}
