package com.hey_there.Tree.SumOfRootToLeafNumbers;

import com.hey_there.BinaryTreeInOrderTraversal.TreeNode;

public class Solution {
    private int resultSum;

    public int sumNumbers(TreeNode root) {
        resultSum = 0;
        helper(root, 0);
        return resultSum;
    }

    private void helper(TreeNode node, int prevPathNum) {
        if (node == null) {
            return;
        }

        int currentNum = prevPathNum * 10 + node.val;//根到当前结点路径代表的数字
        if (node.left == null && node.right == null) {
            //若当前结点是叶子结点，将currentNum加到resultSum上
            resultSum += currentNum;
            return;
        }

        //若当前不是叶子，继续递归后续结点
        helper(node.left, currentNum);
        helper(node.right, currentNum);
    }
}
