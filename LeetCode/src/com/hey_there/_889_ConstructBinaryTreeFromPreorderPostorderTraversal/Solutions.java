package com.hey_there._889_ConstructBinaryTreeFromPreorderPostorderTraversal;

import java.util.HashMap;

public class Solutions {
    private HashMap<Integer, Integer> postVal2Idx = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        //储存后序序列的值和下标的键值对
        for (int i = 0; i < postorder.length; i++)
            postVal2Idx.put(postorder[i], i);
        return construct(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode construct(int[] preorder, int preL, int preR,
                               int[] postorder, int postL, int postR) {
        if (preL > preR) return null;
        if (preL == preR) return new TreeNode(preorder[preL]);
        //前序序列的第一个值为根节点值，构建根节点
        TreeNode root = new TreeNode(preorder[preL]);
        //根节点值的下一个值，认为它是左子树的根节点，获取其在后序序列中的位置
        int leftSubValIdx;
        if (preL + 1 < preorder.length)//存在左子树时获取其后序下标
            leftSubValIdx = postVal2Idx.get(preorder[preL + 1]);
        else return root;//不存在左子树则返回根节点

        //左子树节点数。leftSubValIdx代表的值是左子树中的值，故需要 +1
        int leftSize = leftSubValIdx - postL + 1;
        //递归构建左右子树
        root.left = construct(preorder, preL + 1, preL + leftSize,
                postorder, postL, leftSubValIdx);
        root.right = construct(preorder, preL + leftSize + 1, preR,
                postorder, leftSubValIdx + 1, postR - 1);
        return root;
    }
}
