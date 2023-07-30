package com.hey_there._493_ReversePairs;

/* 类似第327题，区间和的个数。
 * 此题直接对nums进行归并排序，可得到区间[start,mid]、[mid+1,end]内的翻转对数量，
 * 计算区间[start,end]内的翻转对数量时，对于区间[mid+1,end]内的每一个nums[i]，
 * 将下标 p 从 start 移动到mid，直到满足nums[p]>2*nums[i],
 * 此时子数组nums[p,...,mid]内所有元素都大于2*nums[i]，故ret += (mid - p + 1)。
 * 由于区间[start,mid]、[mid+1,end]内子数组都已排序，故移动 i 后，
 * 只需要继续移动 p，即可得到下一个 i 对应的元素数量，直到 p 的值超过mid。*/
public class Solution {
    private int[] temp;//归并排序辅助数组

    public int reversePairs(int[] nums) {
        temp = new int[nums.length];
        return reversePairs(nums, 0, nums.length - 1);
    }

    //计算子数组nums[start,...,end]中，翻转对的数量
    public int reversePairs(int[] nums, int start, int end) {
        if (start == end)//区间长度为1，没有翻转对
            return 0;
        //归并排序
        int mid = (start + end) / 2;
        int lowerRet = reversePairs(nums, start, mid);
        int higherRet = reversePairs(nums, mid + 1, end);
        //统计翻转对数量
        int ret = lowerRet + higherRet;
        int p = start;
        for (int i = mid + 1; i <= end; i++) {
            //做乘法后有可能溢出int类型，转成long类型比较
            long val_p = nums[p];
            long val_i_twice = 2 * (long) nums[i];
            while (p <= mid && val_p <= val_i_twice) {
                p++;
                val_p = nums[p];
            }
            ret += (mid - p + 1);
        }
        //归并子数组排序结果
        for (int i = start; i <= end; i++)
            temp[i] = nums[i];
        int low = start, high = mid + 1;
        p = start;//变量p可以复用
        while (low <= mid && high <= end) {
            if (temp[low] <= temp[high])
                nums[p] = temp[low++];
            else nums[p] = temp[high++];
            p++;
        }
        while (low <= mid) {
            nums[p] = temp[low++];
            p++;
        }
        while (high <= end) {
            nums[p] = temp[high++];
            p++;
        }
        return ret;
    }
}
