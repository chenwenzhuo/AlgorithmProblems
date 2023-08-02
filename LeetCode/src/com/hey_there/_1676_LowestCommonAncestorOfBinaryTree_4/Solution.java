package com.hey_there._1676_LowestCommonAncestorOfBinaryTree_4;

import java.util.HashSet;

public class Solution {
    private HashSet<TreeNode> nodesSet = new HashSet<>();
    private TreeNode ancestor = null;//最近公共祖先

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        for (TreeNode node : nodes)
            nodesSet.add(node);//将所有节点存入哈希集合中
        subsetSize(root);
        return ancestor;
    }

    //计算子树root中包含的nodesSet子集的大小
    private int subsetSize(TreeNode root) {
        if (root == null) return 0;
        //左右子树包含的子集大小
        int leftSize = subsetSize(root.left);
        int rightSize = subsetSize(root.right);
        //当前根节点在nodesSet集合中，cur为1，否则为0
        int cur = nodesSet.contains(root) ? 1 : 0;

        int size = leftSize + rightSize + cur;
        if (size == nodesSet.size() && ancestor == null)
            ancestor = root;
        return size;
    }
}
