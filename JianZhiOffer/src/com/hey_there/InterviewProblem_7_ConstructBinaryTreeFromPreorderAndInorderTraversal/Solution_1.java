package com.hey_there.InterviewProblem_7_ConstructBinaryTreeFromPreorderAndInorderTraversal;

public class Solution_1 {
    private int[] preorder;
    private int[] inorder;
    private int numNode;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        this.numNode = preorder.length;
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
        int idx = inStart;
        while (idx <= inEnd) {
            if (inorder[idx] == preorder[preIdx]) {
                break;
            }
            idx++;
        }
        //递归构建左右子树
        root.left = buildTree(preIdx + 1, inStart, idx - 1);
        root.right = buildTree(preIdx + idx - inStart + 1, idx + 1, inEnd);
        return root;
    }

    private void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.val + "   ");
        inorderTraversal(root.right);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 1, 2, 4};
        int[] inorder = {1, 2, 3, 4};
        Solution_1 solution = new Solution_1();
        TreeNode root = solution.buildTree(preorder, inorder);
        solution.inorderTraversal(root);
    }
}
