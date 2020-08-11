package com.hey_there.InterviewProblem_54_KthLargestInBST;

import java.util.ArrayList;

public class Solution {
    private ArrayList<Integer> inorderList;

    public int kthLargest(TreeNode root, int k) {
        this.inorderList = new ArrayList<>(k);//list只需要大小为k的空间
        inorderTraversal(root, k);
        return inorderList.get(inorderList.size() - 1);
    }

    private void inorderTraversal(TreeNode tree, int k) {
        if (tree == null) return;
        //先遍历右子树，从大到小排序
        inorderTraversal(tree.right, k);
        if (inorderList.size() >= k) return;//在右子树已经找到k个最大的数，不再继续遍历
        inorderList.add(tree.val);
        if (inorderList.size() >= k) return;//当前节点是第k大的节点，不再遍历左子树
        inorderTraversal(tree.left, k);
    }
}
