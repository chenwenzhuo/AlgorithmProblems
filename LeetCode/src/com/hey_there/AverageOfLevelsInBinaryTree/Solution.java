package com.hey_there.AverageOfLevelsInBinaryTree;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> avgOfLevels = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        double levelSum;
        queue.offer(root);

        while (!queue.isEmpty()) {
            levelSum = 0d;
            //将一层的节点全部取出
            int numLevelNodes = queue.size();
            for (int i = 0; i < numLevelNodes; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;
                //将当前节点的子节点加入队列
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            avgOfLevels.add(levelSum / numLevelNodes);
        }
        return avgOfLevels;
    }
}
