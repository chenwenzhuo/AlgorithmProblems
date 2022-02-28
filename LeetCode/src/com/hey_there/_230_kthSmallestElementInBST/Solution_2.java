package com.hey_there._230_kthSmallestElementInBST;

public class Solution_2 {
    private int count = 0;//计数器
    private int target = -1;//节点值非负，-1表示未找到目标值

    public int kthSmallest(TreeNode root, int k) {
        traversal(root, k);
        return target;
    }

    private void traversal(TreeNode root, int k) {
        if (root == null || target != -1) return;
        //中序遍历BST
        traversal(root.left, k);
        count++;
        if (count == k) target = root.val;
        traversal(root.right, k);
    }
}
