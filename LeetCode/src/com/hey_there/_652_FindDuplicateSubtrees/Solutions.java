package com.hey_there._652_FindDuplicateSubtrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solutions {
    private List<TreeNode> duplicates = new ArrayList<>();
    private HashMap<String, Integer> treeTraversals = new HashMap<>();//还未被重复的遍历结果

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        find(root);
        return duplicates;
    }

    private String find(TreeNode root) {
        if (root == null) return "n";//"n"代表null
        //递归遍历子树，获得子树的遍历结果
        String leftSubTraversal = find(root.left);
        String rightSubTraversal = find(root.right);
        //若子树曾出现过，且仅出现过一次，则将子树根节点加入集合
        if (treeTraversals.getOrDefault(leftSubTraversal, 0) == 1)
            duplicates.add(root.left);
        else if (!leftSubTraversal.equals("n"))//将子树遍历结果加入map中，空树的结果不加入
            treeTraversals.put(leftSubTraversal,
                    treeTraversals.getOrDefault(leftSubTraversal, 0) + 1);
        if (treeTraversals.getOrDefault(rightSubTraversal, 0) == 1)
            duplicates.add(root.right);
        else if (!rightSubTraversal.equals("n"))
            treeTraversals.put(rightSubTraversal,
                    treeTraversals.getOrDefault(rightSubTraversal, 0) + 1);
        /* 将左子树、右子树的遍历结果与根节点值拼接，形成当前树的遍历结果
         * 加入"."防止两个节点的值在字符串中直接相邻，成为另一个值*/
        return leftSubTraversal + "." +
                rightSubTraversal + "." + root.val;
    }

    public static void main(String[] args) {
        /*TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.left.left = new TreeNode(4);*/

        /*TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.right.right = new TreeNode(0);
        root.right.right.right = new TreeNode(0);*/

        /*TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(12);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(1);*/

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);

        Solutions solution = new Solutions();
        List<TreeNode> res = solution.findDuplicateSubtrees(root);
    }
}
