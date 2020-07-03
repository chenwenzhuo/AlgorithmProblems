package com.hey_there.SubarraySumEqualsK;

import java.util.HashMap;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int subarraySum_preSum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        int preSum = 0;//前缀和
        for (int n : nums) {
            preSum += n;
            //ans加上map中preSum-k的出现次数
            if (map.containsKey(preSum - k)) {
                ans += map.get(preSum - k);
            }
            //map中没有此前缀和则将其加入，若有则将其出现次数加1
            if (!map.containsKey(preSum)) {
                map.put(preSum, 1);
            } else {
                map.put(preSum, map.get(preSum) + 1);
            }
        }
        return ans;
    }
}
