package com.hey_there._1026_MaximumDifferenceBetweenNodeAndAncestor;

import java.io.FileOutputStream;
import java.util.HashSet;

public class Solution {
    private HashSet<TreeNode> ancestors = new HashSet<>();
    private int maxDiff = 0;

    public int maxAncestorDiff_1(TreeNode root) {
        if (ancestors.size() > 0) {//ancestors集合非空，则当前节点不是整棵树的根
            //逐个计算当前节点值与祖先节点值的差值绝对值
            for (TreeNode ancestor : ancestors)
                maxDiff = Math.max(maxDiff, Math.abs(root.val - ancestor.val));
        }
        //计算完当前节点值与祖先节点值的差，或当前节点是整棵树的根节点
        //将当前节点加入集合作为祖先，递归进行计算
        ancestors.add(root);
        if (root.left != null) maxAncestorDiff_1(root.left);
        if (root.right != null) maxAncestorDiff_1(root.right);
        //返回上一层递归前，将当前节点从集合中移除
        ancestors.remove(root);
        return maxDiff;
    }

    public int maxAncestorDiff(TreeNode root) {
        return maxAncestorDiff(root, root.val, root.val);
    }

    //参数minInPath和maxInPath分别表示根节点到当前节点路径上的最大、最小节点值
    private int maxAncestorDiff(TreeNode root, int minInPath, int maxInPath) {
        if (root == null) return 0;//空树直接返回0
        //更新最小、最大值
        minInPath = Math.min(minInPath, root.val);
        maxInPath = Math.max(maxInPath, root.val);
        int diff = Math.abs(minInPath - maxInPath);//计算差值
        //递归计算子树
        diff = Math.max(diff, maxAncestorDiff(root.left, minInPath, maxInPath));
        diff = Math.max(diff, maxAncestorDiff(root.right, minInPath, maxInPath));
        return diff;
    }
}
