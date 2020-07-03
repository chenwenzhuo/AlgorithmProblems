package com.hey_there.PathSumEqualsTargetSum_findAllPaths;

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
        prevList.add(node.val);
        if (currentSum == targetSum && node.left == null && node.right == null) {
            allResults.add(new ArrayList<>(prevList));
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
