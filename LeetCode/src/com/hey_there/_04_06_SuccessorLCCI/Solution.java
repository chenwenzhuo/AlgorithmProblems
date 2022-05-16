package com.hey_there._04_06_SuccessorLCCI;

public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode suc = null;
        //节点p的右子树非空时，其后继节点是它的右子树中最左边的节点
        if (p.right != null) {
            suc = p.right;
            while (suc.left != null) suc = suc.left;
            return suc;
        }
        //节点p的右子树为空时，其后继节点是它的祖先节点
        TreeNode node = root;
        while (node != null) {
            if (node.val > p.val) {
                suc = node;
                node = node.left;
            } else node = node.right;
        }
        return suc;
    }
}
