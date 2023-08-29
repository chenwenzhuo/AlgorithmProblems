package com.hey_there._823_BinaryTreesWithFactors;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    private long MOD = 1000000007L;
    //记录arr中所有数字及其下标
    private HashMap<Integer, Integer> numsMap = new HashMap<>();
    //memo[i]表示以arr[i]为根的二叉树数量
    private long[] memo;

    public int numFactoredBinaryTrees(int[] arr) {
        this.memo = new long[arr.length];
        Arrays.sort(arr);
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            numsMap.put(arr[i], i);
            ans += (int) (numFactoredBinaryTrees(arr, arr[i], i) % MOD);
            ans %= 1000000007;
        }
        return ans;
    }

    private long numFactoredBinaryTrees(int[] arr, int rootVal, int rootIndex) {
        long n = 1L;//以rootVal为根，至少可以构成一棵只有一个节点的树
        for (int i = 0; i < rootIndex; i++) {
            //当前值是根节点值的因数，且相除的商也在数组中，则这两个值可以作为子节点值
            if (rootVal % arr[i] == 0 && numsMap.containsKey(rootVal / arr[i])) {
                int val = rootVal / arr[i], valIndex = numsMap.get(val);
                long leftNum, rightNum;
                if (memo[i] > 0)
                    leftNum = memo[i];
                else {
                    leftNum = numFactoredBinaryTrees(arr, arr[i], i) % MOD;
                    memo[i] = leftNum;
                }
                if (memo[valIndex] > 0)
                    rightNum = memo[valIndex];
                else {
                    rightNum = numFactoredBinaryTrees(arr, val, valIndex) % MOD;
                    memo[valIndex] = rightNum;
                }
                n += (leftNum * rightNum) % MOD;
                n %= MOD;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 10};
        int ans = new Solution().numFactoredBinaryTrees(arr);
        System.out.println(ans);
    }
}
