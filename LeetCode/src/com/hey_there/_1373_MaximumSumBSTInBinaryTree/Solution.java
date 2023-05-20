package com.hey_there._1373_MaximumSumBSTInBinaryTree;

public class Solution {
    private int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        int[] rootInfo = recursiveSearch(root);
        if (rootInfo[0] == 1) {//若树root是有效BST，尝试更新maxSum
            maxSum = Math.max(maxSum, rootInfo[3]);
        }
        return maxSum;
    }

    /**
     * 递归遍历二叉树，返回一个长为4的数组，数组元素分别表示：
     * 下标0：当前子树是否是BST，0-否，1-是且树非空，2-空树，视为BST；
     * 下标1：当前子树中的最小值；
     * 下标2：当前子树中的最大值；
     * 下标3：当前子树中所有元素的和；
     * 由于树中节点值范围是[-40000,40000]，范围外的值用于表示无穷
     */
    private int[] recursiveSearch(TreeNode root) {
        if (root == null)//空树，是BST，最大最小值为无穷，和为0
            return new int[]{2, -50000, 50000, 0};
        //非空树，递归遍历其子树
        int[] leftInfo = recursiveSearch(root.left);
        int[] rightInfo = recursiveSearch(root.right);
        //检查左右子树遍历结果
        if (leftInfo[0] == 0 || rightInfo[0] == 0) {
            //任意子树不是BST，则当前树root也不是BST
            return new int[]{//返回当前树root的信息
                    0,
                    Math.min(root.val, Math.min(leftInfo[1], rightInfo[1])),
                    Math.max(root.val, Math.max(leftInfo[2], rightInfo[2])),
                    root.val + leftInfo[3] + rightInfo[3]
            };
        }
        //两子树都是BST时
        if (leftInfo[0] == 2 && rightInfo[0] == 2) {//两子树都为空树
            maxSum = Math.max(maxSum, root.val);//尝试更新maxSum
            return new int[]{1, root.val, root.val, root.val};
        }
        if (leftInfo[0] == 2) {//左子树为空，右子树非空
            int sumCurTree = root.val + rightInfo[3];//树root中节点和
            //root.val小于右子树最小值，则树root为BST
            if (root.val < rightInfo[1]) {
                //尝试更新maxSum
                maxSum = Math.max(maxSum, sumCurTree);
                return new int[]{1, root.val, rightInfo[2], sumCurTree};
            }
            //树root不是BST
            return new int[]{0, rightInfo[1], Math.max(root.val, rightInfo[2]), sumCurTree};
        }
        if (rightInfo[0] == 2) {//左子树非空，右子树为空
            int sumCurTree = root.val + leftInfo[3];//树root中节点和
            //root.val大于左子树最大值，则树root为BST
            if (root.val > leftInfo[2]) {
                //尝试更新maxSum
                maxSum = Math.max(maxSum, sumCurTree);
                return new int[]{1, leftInfo[1], root.val, sumCurTree};
            }
            //树root不是BST
            return new int[]{0, Math.min(root.val, leftInfo[1]), leftInfo[2], sumCurTree};
        }
        //左右子树都非空时
        int sumCurTree = root.val + leftInfo[3] + rightInfo[3];//树root中节点和
        //root.val大于左子树最大值，且小于右子树最小值，则树root为BST
        if (root.val > leftInfo[2] && root.val < rightInfo[1]) {
            maxSum = Math.max(maxSum, sumCurTree);//尝试更新maxSum
            return new int[]{1, leftInfo[1], rightInfo[2], sumCurTree};
        }
        //树root不是BST
        return new int[]{
                0,
                Math.min(root.val, Math.min(leftInfo[1], rightInfo[1])),
                Math.max(root.val, Math.max(leftInfo[2], rightInfo[2])),
                sumCurTree
        };
    }
}
