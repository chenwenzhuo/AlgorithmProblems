package com.hey_there._1676_LowestCommonAncestorOfBinaryTree_4;

import java.util.HashSet;

public class Solution {
    private HashSet<TreeNode> nodesSet = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        for (TreeNode node : nodes)
            nodesSet.add(node);//将所有节点存入哈希集合中
        return lowestCommonAncestor(root);
    }

    public TreeNode lowestCommonAncestor(TreeNode root) {
        if (root == null) return null;
        //若集合包含当前root节点，则将root返回
        if (nodesSet.contains(root))
            return root;
        //递归检查子树
        TreeNode leftRes = lowestCommonAncestor(root.left);
        TreeNode rightRes = lowestCommonAncestor(root.right);
        //若左右子树中都包含集合中节点，则当前root是LCA
        if (leftRes != null && rightRes != null)
            return root;
        //左子树不含集合中节点，则返回右子树的结果，反之亦然
        return leftRes == null ? rightRes : leftRes;
    }
}
