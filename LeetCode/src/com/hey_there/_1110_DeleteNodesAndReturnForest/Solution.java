package com.hey_there._1110_DeleteNodesAndReturnForest;

import java.util.*;

public class Solution {
    private ArrayDeque<TreeNode> queue = new ArrayDeque<>();
    private HashMap<Integer, TreeNode> val2node = new HashMap<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        HashSet<Integer> tdSet = new HashSet<>();
        for (int td : to_delete)
            tdSet.add(td);
        queue.offer(root);
        val2node.put(root.val, root);
        while (!queue.isEmpty() && tdSet.size() > 0) {
            TreeNode node = queue.poll();
            dfs(node, null, tdSet);
        }
        List<TreeNode> ans = new ArrayList<>();
        for (Map.Entry<Integer, TreeNode> entry : val2node.entrySet()) {
            ans.add(entry.getValue());
        }
        return ans;
    }

    private void dfs(TreeNode root, TreeNode parent, HashSet<Integer> tdSet) {
        if (root == null)
            return;
        if (tdSet.contains(root.val)) {//当前节点需要被删除
            if (parent != null) {//从父节点中删除当前节点
                if (parent.left == root) parent.left = null;
                if (parent.right == root) parent.right = null;
            }
            tdSet.remove(root.val);//当前节点已删除，tdSet中也删除节点值
            val2node.remove(root.val);//根节点集合无需包含当前节点
            //将子节点加入队列
            if (root.left != null) {
                queue.offer(root.left);
                val2node.put(root.left.val, root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
                val2node.put(root.right.val, root.right);
            }
        } else {//当前节点不需要被删除，则递归检查子树
            dfs(root.left, root, tdSet);
            dfs(root.right, root, tdSet);
        }
    }
}
