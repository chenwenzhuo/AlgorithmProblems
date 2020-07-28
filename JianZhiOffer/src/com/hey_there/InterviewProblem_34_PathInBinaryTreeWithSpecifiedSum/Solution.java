package com.hey_there.InterviewProblem_34_PathInBinaryTreeWithSpecifiedSum;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    private List<List<Integer>> res = new LinkedList<>();
    private LinkedList<Integer> curPath = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        backtrack(root, sum);
        return res;
    }

    private void backtrack(TreeNode root, int targetSum) {
        if (root == null) return;

        curPath.add(root.val);//将当前节点加入路径
        targetSum -= root.val;//目标值减去当前节点值
        //目标值归零，且当前节点是叶结点，则将路径加入结果集合
        if (targetSum == 0 && root.left == null && root.right == null){
            res.add(new LinkedList<>(curPath));//将路径加入结果集合
            curPath.removeLast();
            return;
        }
        //目标值已经小于等于0，但当前节点不是叶子节点，无需继续遍历
        if (targetSum <= 0 && (root.left != null || root.right != null)) {
            curPath.removeLast();
            return;
        }
        //递归向下
        backtrack(root.left, targetSum);
        backtrack(root.right, targetSum);
        curPath.removeLast();//从路径中移除当前节点
    }

    public static void main(String[] args) {
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
        List<List<Integer>> result = solution.pathSum(root, 22);
        System.out.println(result);
    }
}
