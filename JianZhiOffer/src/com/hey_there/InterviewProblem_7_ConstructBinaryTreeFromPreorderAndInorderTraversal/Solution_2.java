package com.hey_there.InterviewProblem_7_ConstructBinaryTreeFromPreorderAndInorderTraversal;

import java.util.HashMap;

/**
 * 相对于Solution_1的改进：
 * 使用HashMap存储中序遍历的结点值与数组下标之间的映射，避免重复寻找下标
 */
public class Solution_2 {
    private int[] preorder;
    private int numNode;
    private HashMap<Integer,Integer> val2InorderIdx;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.numNode = preorder.length;
        this.val2InorderIdx = new HashMap<>();
        //储存映射
        for (int i = 0; i < numNode; i++) {
            val2InorderIdx.put(inorder[i], i);
        }
        //开始递归构建二叉树
        return buildTree(0, 0, numNode - 1);
    }

    /**
     *
     * @param preIdx 当前子树的根结点的值的下标
     * @param inStart 当前子树在中序序列中的范围起点
     * @param inEnd 当前子树在中序序列中的范围终点
     * @return 当前子树根节点引用
     */
    private TreeNode buildTree(int preIdx, int inStart, int inEnd) {
        if (preIdx >= numNode || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIdx]);//创建根节点
        //找到preorder[preIdx]在inorder数组中的位置
        int idx = val2InorderIdx.get(preorder[preIdx]);
        //递归构建左右子树
        root.left = buildTree(preIdx + 1, inStart, idx - 1);
        root.right = buildTree(preIdx + idx - inStart + 1, idx + 1, inEnd);
        return root;
    }
}
