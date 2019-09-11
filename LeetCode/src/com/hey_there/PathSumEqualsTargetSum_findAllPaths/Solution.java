package com.hey_there.PathSumEqualsTargetSum_findAllPaths;

import com.hey_there.BinaryTreeInOrderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<Integer>> allResults;
    private int targetSum;

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        allResults = new ArrayList<>();
        if (root == null) {
            return allResults;
        }

        this.targetSum = sum;
        helper(root, 0, new ArrayList<>());
        return allResults;
    }

    private void helper(TreeNode node, int prevSum, List<Integer> prevList) {
        if (node == null) {
            return;
        }

        int currentSum = node.val + prevSum;//根结点到当前结点的路径之和
        /*//若路径和等于目标值，且当前结点是叶子
        if (currentSum == targetSum && node.left == null && node.right == null) {
            //新建一个List，加入路径上所有结点的值
            List<Integer> onePath = new ArrayList<>(prevList);
            onePath.add(node.val);//将当前结点值加入onePath
            allResults.add(onePath);//将当前路径加入结果集合
            return;
        }

        //新建一个List，加入根结点到当前结点的路径上所有结点的值
        List<Integer> nextList = new ArrayList<>(prevList);
        nextList.add(node.val);
        //继续递归
        helper(node.left, currentSum, nextList);
        helper(node.right, currentSum, nextList);*/

        prevList.add(node.val);
        if (currentSum == targetSum && node.left == null && node.right == null) {
            allResults.add(new ArrayList<>(prevList));
            //return;
        }
        helper(node.left, currentSum, prevList);
        helper(node.right, currentSum, prevList);
        prevList.remove(prevList.size() - 1);
    }

    public static void main(String[] args) {
        //建树
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        Solution solution = new Solution();
        List<List<Integer>> paths = solution.pathSum(root, 22);
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
    }
}
