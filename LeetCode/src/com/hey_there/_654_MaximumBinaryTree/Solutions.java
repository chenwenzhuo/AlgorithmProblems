package com.hey_there._654_MaximumBinaryTree;

public class Solutions {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int left, int right) {
        //若左边界越过右边界，直接返回null
        if (left > right) return null;
        if (left == right)//若左右边界重合，直接根据当前值创建节点返回，不再递归
            return new TreeNode(nums[left], null, null);
        //左边界小于右边界时
        int maxValIdx = findMax(nums, left, right);//寻找[left,right]范围内最大值的下标
        TreeNode root = new TreeNode(nums[maxValIdx]);//根据最大值创建根节点
        //递归构建子节点
        TreeNode leftSub = construct(nums, left, maxValIdx - 1);
        TreeNode rightSub = construct(nums, maxValIdx + 1, right);
        root.left = leftSub;
        root.right = rightSub;
        return root;
    }

    private int findMax(int[] nums, int left, int right) {
        int maxValIdx = left;//最大值的下标
        int i = left;
        while (i <= right) {
            maxValIdx = nums[maxValIdx] < nums[i] ? i : maxValIdx;
            i++;
        }
        return maxValIdx;
    }
}
