package com.hey_there.InterviewProblem_32_BinaryTreeLevelOrderTraverse.Problem_2_Return2DList;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;//root为null时返回空列表

        LinkedList<TreeNode> queue = new LinkedList<>();//队列辅助遍历
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> curLevelRes = new LinkedList<>();
            int num_curLevelNode = queue.size();
            for (int i = 0; i < num_curLevelNode; i++) {
                TreeNode curNode = queue.poll();
                curLevelRes.add(curNode.val);
                if (curNode.left != null) queue.offer(curNode.left);
                if (curNode.right != null) queue.offer(curNode.right);
            }
            res.add(curLevelRes);
        }
        return res;
    }
}
