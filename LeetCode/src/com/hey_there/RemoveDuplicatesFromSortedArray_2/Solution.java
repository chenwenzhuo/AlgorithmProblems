package com.hey_there.RemoveDuplicatesFromSortedArray_2;

public class Solution {
    /* 两次遍历数组，
     * 第一次遍历将多余的重复值标记为一个无效值，
     * 第二次遍历使用有效值覆盖无效值*/
    public int removeDuplicates_twiceTraversal(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1 || nums.length == 2) {
            return nums.length;
        }
        int len_nums = nums.length;
        //使用一个范围外的值作为“无效值”
        int invalidVal = nums[0] - 1;

        int dupTmp = invalidVal;
        int idx = 2;
        while (idx < len_nums) {
            //发现连续三个重复值时，使用一个dupTmp变量记录重复值
            if (nums[idx] == nums[idx - 2]) {
                dupTmp = nums[idx];
            } else {
                idx++;
            }
            //将后续所有重复项标记为无效值
            while (idx < len_nums && nums[idx] == dupTmp) {
                nums[idx] = invalidVal;
                idx++;
            }
        }
        //找到第一个无效值的下标
        int invalidIdx = 2;
        while (invalidIdx < len_nums && nums[invalidIdx] != invalidVal) {
            invalidIdx++;
        }
        //找到第一个无效值之后的有效值下标
        int validIdx = invalidIdx + 1;
        while (validIdx < len_nums && nums[validIdx] == invalidVal) {
            validIdx++;
        }
        //使用有效值覆盖无效值
        while (validIdx < len_nums) {
            if (nums[validIdx] == invalidVal) {
                validIdx++;
                continue;
            }
            nums[invalidIdx] = nums[validIdx];
            invalidIdx++;
            validIdx++;
        }
        return invalidIdx;
    }

    public int removeDuplicates_onceTraversal(int[] nums) {
        int coveredIdx = 1, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count <= 2) {
                nums[coveredIdx++] = nums[i];
            }
        }
        return coveredIdx;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3};
        Solution solution = new Solution();
        int newLen = solution.removeDuplicates_onceTraversal(nums);
        for (int i = 0; i < newLen; i++) {
            System.out.print(nums[i] + "   ");
        }
        System.out.println();
    }
}
