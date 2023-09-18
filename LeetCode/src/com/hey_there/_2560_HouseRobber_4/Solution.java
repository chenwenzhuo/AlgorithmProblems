package com.hey_there._2560_HouseRobber_4;

public class Solution {
    public int minCapability(int[] nums, int k) {
        //遍历寻找nums中的最小最大值
        int low = Integer.MAX_VALUE, high = 0;
        for (int n : nums) {
            low = Math.min(low, n);
            high = Math.max(high, n);
        }
        //二分查找
        while (low <= high) {
            int mid = (low + high) / 2;
            //只能偷窃金额不超过mid的房间，且不能偷窃相邻的房间
            //计算可偷窃的房间数量
            int count = 0;
            boolean prevRobbed = false;//表示是否偷窃了前一个房间
            for (int n : nums) {//遍历每个房间
                //当前房间金额不超过mid，且前一个房间未被偷，则可偷当前房间
                //选择偷窃当前房间，计数加1，标记改为true。否则将标记改为false
                if (n <= mid && !prevRobbed) {
                    count++;
                    prevRobbed = true;
                } else prevRobbed = false;
            }
            //缩减范围
            if (count >= k) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }
}
