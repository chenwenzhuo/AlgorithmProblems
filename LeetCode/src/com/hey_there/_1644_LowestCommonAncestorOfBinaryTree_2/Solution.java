package com.hey_there._1644_LowestCommonAncestorOfBinaryTree_2;

public class Solution {
    private TreeNode ancestor = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        traverse(root, p, q);
        return ancestor;
    }

    //返回值表示，在子树root中，包含p、q中的多少个
    private int traverse(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return 0;//空树直接返回0
        //递归检查子树
        int leftCnt = traverse(root.left, p, q);
        int rightCnt = traverse(root.right, p, q);
        //p是q的后代，或q是p的后代，此时一定有leftCnt与rightCnt相加为1
        if (((root == p || root == q) && leftCnt + rightCnt == 1)
                || (leftCnt == 1 && rightCnt == 1)) {//当左右子树一边一个时，当前root是p、q的最近公共祖先
            ancestor = root;
            return 2;
        }
        //当root是p、q二者之一，但另一者不是后代时，返回1
        if ((root == p || root == q) && (leftCnt == 0 && rightCnt == 0))
            return 1;
        return leftCnt + rightCnt;
    }
}
