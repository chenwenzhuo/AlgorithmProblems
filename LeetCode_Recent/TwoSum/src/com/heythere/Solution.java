package com.heythere;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 嵌套循环，O(n^2)的复杂度遍历所有可能情况
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumMySolution(int[] nums, int target) {
        int len = nums.length;
        int[] twoNums = new int[2];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    twoNums[0] = i;
                    twoNums[1] = j;
                    return twoNums;
                }
            }
        }
        //没有结果则抛出异常
        throw new IllegalArgumentException("Not found");
    }

    /**
     * 为了对运行时间复杂度进行优化，我们需要一种更有效的方法来检查数组中是否存在目标元素。
     * 如果存在，我们需要找出它的索引。保持数组中的每个元素与其索引相互对应的最好方法是什么？哈希表。
     *
     * 通过以空间换取速度的方式，我们可以将查找时间从 O(n)O(n) 降低到 O(1)O(1)。
     * 哈希表正是为此目的而构建的，它支持以 近似 恒定的时间进行快速查找。我用“近似”来描述，
     * 是因为一旦出现冲突，查找用时可能会退化到 O(n)O(n)。
     * 但只要你仔细地挑选哈希函数，在哈希表中进行查找的用时应当被摊销为 O(1)O(1)。
     *
     * 时间复杂度：O(n)，空间复杂度：O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumTwiceHash(int[] nums, int target) {
        /*
        第一次遍历，将每个元素的值和它的索引添加到哈希表中
         */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        /*
        第二次遍历，
        检查每个元素所对应的目标元素
        （target - nums[i]target−nums[i]）是否存在于表中
         */
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 在插入的同时查找
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumOnceHash(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
