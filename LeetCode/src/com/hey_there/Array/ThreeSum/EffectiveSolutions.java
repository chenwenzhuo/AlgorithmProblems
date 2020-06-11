package com.hey_there.Array.ThreeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EffectiveSolutions {
    public List<List<Integer>> threeSum_LRPointer(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        //处理无效输入
        if (nums == null || nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);//对数组排序
        int length = nums.length;//获取数组长度

        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {//若当前数字大于0，则和一定大于0，故结束循环
                break;
            }
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int threeSum = nums[i] + nums[left] + nums[right];//计算三数之和
                if (0 == threeSum) {//若和为0
                    //加入答案集合
                    List<Integer> oneAns = new ArrayList<>();
                    oneAns.add(nums[i]);
                    oneAns.add(nums[left]);
                    oneAns.add(nums[right]);
                    ans.add(oneAns);
                    //排除重复解
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (threeSum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }
}
