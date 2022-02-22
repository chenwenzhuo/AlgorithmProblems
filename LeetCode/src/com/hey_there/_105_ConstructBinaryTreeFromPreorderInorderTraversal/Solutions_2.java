package com.hey_there._105_ConstructBinaryTreeFromPreorderInorderTraversal;

import java.util.HashMap;

public class Solutions_2 {
    private HashMap<Integer, Integer> inorderVal2Idx = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //存储中序数组的值和下标之间的映射
        for (int i = 0; i < inorder.length; i++)
            inorderVal2Idx.put(inorder[i], i);
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR) {
        if (preL > preR) return null;
        //preorder数组的第一个位置就是根节点的值
        TreeNode root = new TreeNode(preorder[preL]);
        int rootValIdx = inorderVal2Idx.get(preorder[preL]);//根节点值在中序数组中的下标
        int leftSize = rootValIdx - inL;//左子树的节点数
        //递归构建左右子树
        root.left = build(preorder, preL + 1, preL + leftSize,
                inorder, inL, rootValIdx - 1);
        root.right = build(preorder, preL + leftSize + 1, preR,
                inorder, rootValIdx + 1, inR);
        return root;
    }
}
