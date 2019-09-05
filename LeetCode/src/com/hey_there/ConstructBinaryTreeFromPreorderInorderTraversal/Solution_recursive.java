package com.hey_there.ConstructBinaryTreeFromPreorderInorderTraversal;

import com.hey_there.BinaryTreeInOrderTraversal.TreeNode;

import java.util.Arrays;

public class Solution_recursive {
    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        if (preOrder.length == 0 || inOrder.length == 0) {
            return null;
        }

        TreeNode currentRoot = new TreeNode(preOrder[0]);//当前树的根结点
        int indexOfRootVal_inOrder = indexOfValue(inOrder, preOrder[0]);//当前树的根结点值在中序遍历序列中的下标
        if (indexOfRootVal_inOrder == -1) {
            System.out.println("下标值为-1！");
        }

        //获取当前树的左子树的中序遍历结果
        int[] currentTree_leftChildren_inOrder =
                Arrays.copyOfRange(inOrder, 0, indexOfRootVal_inOrder);
        //获取当前树的右子树的中序遍历结果
        int[] currentTree_rightChildren_inOrder =
                Arrays.copyOfRange(inOrder, indexOfRootVal_inOrder + 1, inOrder.length);

        //获取当前树的左子树的前序遍历结果
        int[] currentTree_leftChildren_preOrder =
                Arrays.copyOfRange(preOrder, 1, currentTree_leftChildren_inOrder.length + 1);
        //获取当前树的右子树的前序遍历结果
        int[] currentTree_rightChildren_preOrder =
                Arrays.copyOfRange(preOrder,
                        preOrder.length - currentTree_rightChildren_inOrder.length, preOrder.length);

        currentRoot.left = buildTree(currentTree_leftChildren_preOrder, currentTree_leftChildren_inOrder);
        currentRoot.right = buildTree(currentTree_rightChildren_preOrder, currentTree_rightChildren_inOrder);

        return currentRoot;
    }

    private int indexOfValue(int[] arr, int value) {
        for (int index = 0; index < arr.length; index++) {
            if (arr[index] == value) {
                return index;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution_recursive solutionRecursive = new Solution_recursive();
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};
        System.out.println(solutionRecursive.buildTree(preOrder, inOrder).val);
    }
}
