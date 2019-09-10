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
        helper(root, 0, null);
        return allResults;
    }

    private void helper(TreeNode node, int prevSum, List<Integer> prevList) {
        if (node == null) {
            return;
        }

        int currentSum = node.val + prevSum;
        if (currentSum == targetSum) {
            if (node.left == null && node.right == null) {
                List<Integer> onePath = new ArrayList<>();

                //将prevList中的所有元素加入onePath
                if (prevList != null) {
                    onePath.addAll(prevList);
                }
                //将当前结点值加入onePath
                onePath.add(node.val);
                allResults.add(onePath);
                return;
            }
        }

        List<Integer> nextList;
        if (prevList != null) {
            nextList = new ArrayList<>(prevList);
        }else {
            nextList = new ArrayList<>();
        }
        nextList.add(node.val);
        helper(node.left, currentSum, nextList);
        helper(node.right, currentSum, nextList);
    }
}
