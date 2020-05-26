package com.hey_there.DailyProblems.May.FindDuplicateNumber;

public class Solution {
    public int findDuplicate_binarySearch(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            int lowerHalf = 0;
            //统计nums数组中在[low,mid]闭区间范围内的数的个数
            for (int n : nums) {
                if (low <= n && n <= mid) {
                    lowerHalf++;
                }
            }
            if (lowerHalf <= mid - low + 1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public int findDuplicate_doublePointer(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
