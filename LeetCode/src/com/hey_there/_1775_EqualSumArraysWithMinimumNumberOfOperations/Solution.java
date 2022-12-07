package com.hey_there._1775_EqualSumArraysWithMinimumNumberOfOperations;

public class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        //若一个数组和的最大值小于另一个的最小值，则一定不能使和相等
        if (len1 * 6 < len2 || len2 * 6 < len1)
            return -1;
        //计算两数组中各个数字的出现次数，及两数组元素和的差值
        int[] cnt1 = new int[7];
        int[] cnt2 = new int[7];
        int diff = 0;
        for (int n1 : nums1) {
            cnt1[n1]++;
            diff += n1;//累加nums1的元素
        }
        for (int n2 : nums2) {
            cnt2[n2]++;
            diff -= n2;//减去nums2的元素，得到差值
        }
        //diff为0，和相等，无需操作
        if (diff == 0)
            return 0;
        //diff非0，计算操作次数
        //总是将和更小的数组的cnt传递给helper的第一个参数
        if (diff < 0)
            return helper(cnt1, cnt2, -diff);
        return helper(cnt2, cnt1, diff);
    }

    private int helper(int[] cnt1, int[] cnt2, int diff) {
        //计算每个元素的最大变化量
        //数组元素nums[i]最大能变成6，变化量为6-nums[i]
        //数组元素nums[i]最小能变成1，变化量为nums[i]-1
        int[] changes = new int[7];
        for (int i = 1; i < 7; i++) {
            //nums1中有cnt1[i]个i（1<=i<=6），全部变为6，变化量6-i，有changes[6-i]个
            changes[6 - i] += cnt1[i];
            //nums2中有cnt2[i]个i（1<=i<=6），全部变为1，变化量i-1，有changes[i-1]个
            changes[i - 1] += cnt2[i];
        }
        //变化量最大为5，故从5开始倒序遍历，以减少diff
        int res = 0;
        for (int i = 5; i >= 1 && diff > 0; i--) {
            //对于变化量i，最多能让diff减少changes[i] * i
            //若能让diff减为非正数，则计算最少使用多少个变化量i，可使diff小于等于0
            //不能使diff减为非正数时，直接将changes[i]个变化量i全用上
            int chg;
            if (changes[i] * i >= diff)
                //(diff + i - 1)/i 用于对 diff/i 向上取整
                chg = (diff + i - 1) / i;
            else
                chg = changes[i];
            res += chg;
            diff -= i * chg;
        }
        return res;
    }
}
