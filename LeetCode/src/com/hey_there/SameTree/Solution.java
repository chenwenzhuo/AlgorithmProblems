package com.hey_there.SameTree;

public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;//两者同为null，返回true
        if (p == null || q == null) return false;//两者仅有一个为null，返回false
        //两者都不为null时：
        if (p.val != q.val) return false;//节点值不相等，返回false
        //节点值相等则检查子树
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
