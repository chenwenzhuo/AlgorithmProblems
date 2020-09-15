package com.hey_there.InterviewProblem_68.Problem_2_LowestCommonAncestorOfBinaryTree;

public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //三者之中任意一个为null时，返回null
        if (root == null || p == null || q == null) return null;

        //root与 p 和 q 其中之一相同时，返回root
        if (root == p || root == q) return root;
        //在左右子树中进行搜索
        TreeNode leftRes = lowestCommonAncestor(root.left, p, q);
        TreeNode rightRes = lowestCommonAncestor(root.right, p, q);
        //两个结果均不为null，说明 p，q 两节点分别在root节点的左右子树中，root为最近公共祖先
        if (leftRes != null && rightRes != null) return root;
        else if (leftRes == null) return rightRes;//leftRes为null，则最近公共祖先在root的右子树中
        else return leftRes;//rightRes为null，则最近公共祖先在root的左子树中
    }
}
