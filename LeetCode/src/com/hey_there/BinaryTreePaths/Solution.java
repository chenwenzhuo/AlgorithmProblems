package com.hey_there.BinaryTreePaths;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> paths = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        binaryTreePaths(builder, root);
        return paths;
    }

    private void binaryTreePaths(StringBuilder builder, TreeNode root) {
        if (root == null) return;
        //若当前节点是叶子节点，向list集合中存入一条路径
        if (root.left == null && root.right == null) {
            paths.add(builder.toString() + root.val);
            return;
        }
        //若当前结点不是叶子节点，则将当前节点值加入builder，再向下递归
        int initLen = builder.length();//记录builder的初始长度
        builder.append(root.val).append("->");
        binaryTreePaths(builder, root.left);
        binaryTreePaths(builder, root.right);
        //返回上一层递归前，移除当前节点值
        builder.delete(initLen, builder.length());
    }
}
