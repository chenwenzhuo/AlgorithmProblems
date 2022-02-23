package com.hey_there._106_ConstructBinaryTreeFromInorderPostorderTraversal;

import java.util.HashMap;

public class Solutions {
    private HashMap<Integer, Integer> inorderVal2Idx = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //储存中序遍历的值和下标的键值对
        for (int i = 0; i < inorder.length; i++)
            inorderVal2Idx.put(inorder[i], i);
        return construct(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode construct(int[] inorder, int inL, int inR,
                               int[] postorder, int postL, int postR) {
        if (postL > postR) return null;
        //后序的最后一个值为根节点的值，构造根节点
        TreeNode root = new TreeNode(postorder[postR]);
        //根节点值在中序序列中的下标
        int rootValIdx = inorderVal2Idx.get(postorder[postR]);
        //右子树的节点数
        int rightSize = inR - rootValIdx;
        //递归构建左右子树
        root.right = construct(inorder, rootValIdx + 1, inR,
                postorder, postR - rightSize, postR - 1);
        root.left = construct(inorder, inL, rootValIdx - 1,
                postorder, postL, postR - rightSize - 1);
        return root;
    }
}
