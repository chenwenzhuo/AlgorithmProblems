package com.hey_there.InterviewProblem_32_BinaryTreeLevelOrderTraverse.Problem_1_Return1DArray;

import java.util.LinkedList;

public class Solution {
    public int[] levelOrder(TreeNode root) {
        if (root == null)
            return new int[0];
        LinkedList<TreeNode> queueForTraverse = new LinkedList<>();//辅助遍历的队列
        LinkedList<Integer> queueForRecord = new LinkedList<>();//记录遍历结果的队列
        queueForTraverse.offer(root);
        while (!queueForTraverse.isEmpty()) {
            //从队列中获取一个节点
            TreeNode curNode = queueForTraverse.poll();
            queueForRecord.add(curNode.val);//记录节点值
            //将节点的左右子结点入队
            if (curNode.left != null) queueForTraverse.offer(curNode.left);
            if (curNode.right != null) queueForTraverse.offer(curNode.right);
        }
        //将队列中记录的遍历结果转换成数组
        int[] res = new int[queueForRecord.size()];
        int idx = 0;
        for (int num : queueForRecord)
            res[idx++] = num;
        return res;
    }
}
