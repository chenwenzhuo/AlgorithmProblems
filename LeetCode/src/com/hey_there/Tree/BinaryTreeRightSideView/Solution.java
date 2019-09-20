package com.hey_there.Tree.BinaryTreeRightSideView;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Integer> rightSideViewNodes = new ArrayList<>();//右视图中包含的结点值
    private int depth_rightSideViewNodes = 0;//右视图中结点的深度

    public List<Integer> rightSideView(TreeNode root) {
        rightPreOrderTraversal(root, 1);
        return rightSideViewNodes;
    }

    /**
     * 定义一种“右先序遍历”，
     * 一般的先序遍历按 根->左->右 的顺序访问树中结点，
     * 右先序遍历按 根->右->左 的顺序访问树中结点
     *
     * @param treeNode     二叉树的根结点的引用
     * @param currentDepth 当前递归次数时的深度
     */
    private void rightPreOrderTraversal(TreeNode treeNode, int currentDepth) {
        if (treeNode == null) {
            return;
        }
        //仅当当前递归深度大于右视图集合中结点的最大深度时，结点值被加入右视图集合
        if (currentDepth > depth_rightSideViewNodes) {
            rightSideViewNodes.add(treeNode.val);
            depth_rightSideViewNodes = currentDepth;//更新最大深度值
        }
        rightPreOrderTraversal(treeNode.right, currentDepth + 1);
        rightPreOrderTraversal(treeNode.left, currentDepth + 1);
    }
}
