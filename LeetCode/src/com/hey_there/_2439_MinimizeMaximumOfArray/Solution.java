package com.hey_there._2439_MinimizeMaximumOfArray;

public class Solution {
    public int minimizeArrayValue_1(int[] nums) {
        //获取数组中的最大最小值
        int low = 1000000000, high = -1;
        for (int n : nums) {
            low = Math.min(low, n);
            high = Math.max(high, n);
        }
        //二分查找，检查最大值能否小等于mid
        while (low < high) {
            int mid = (high + low) / 2;
            //从后向前遍历数组，若遇到大于mid的值，将这个值超过mid的部分加到前一个值
            //最后检查copy[0]是否大于mid
            long[] copy = new long[nums.length];
            for (int i = 0; i < copy.length; i++)
                copy[i] = nums[i];
            for (int i = copy.length - 1; i > 0; i--) {
                if (copy[i] > mid) {
                    long diff = copy[i] - mid;
                    copy[i] = mid;
                    copy[i - 1] += diff;
                }
            }
            if (copy[0] > mid) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    public int minimizeArrayValue_2(int[] nums) {
        //获取数组中的最大最小值
        int low = 1000000000, high = -1;
        for (int n : nums) {
            low = Math.min(low, n);
            high = Math.max(high, n);
        }
        //二分查找，检查最大值能否小等于mid
        while (low <= high) {
            int mid = low + (high - low) / 2;
            //从后向前遍历数组，若遇到大于mid的值，将这个值超过mid的部分加到前一个值
            //最后检查nums[0]是否大于mid
            long variation = 0;
            for (int i = nums.length - 1; i >= 0; i--) {
                //当前数大于mid，则将超过部分累加到variation
                //否则从variation中减去mid与当前数的差值
                if (nums[i] > mid)
                    variation += nums[i] - mid;
                else {
                    variation -= mid - nums[i];
                    //variation不能为负
                    variation = Math.max(variation, 0);
                }
            }
            //variation小于等于0，对应nums[0]小于等于mid，表示最大值可以小于等于mid，
            //故减小high尝试继续缩小最大值
            //variation大于0，对应nums[0]大于mid，则最大值不能小于等于mid，故增大low
            if (variation <= 0)
                high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }

    public static void main(String[] args) {
        int[] nums = {10, 1};
        int res = new Solution().minimizeArrayValue_1(nums);
        System.out.println(res);
    }
}
