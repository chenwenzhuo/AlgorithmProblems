package com.hey_there.UniqueNumbersInArray;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int[] singleNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            //n不在集合中则将其加入
            if (!set.contains(n)) {
                set.add(n);
            } else {//在集合中则将其移除
                set.remove(n);
            }
        }
        Object[] arr = set.toArray();
        int[] ans = new int[2];
        ans[0] = (Integer) arr[0];
        ans[1] = (Integer) arr[1];
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, 4, 6};
        Solution solution = new Solution();
        int[] ans = solution.singleNumbers(nums);
        System.out.println(ans[0] + "   " + ans[1]);
    }
}
