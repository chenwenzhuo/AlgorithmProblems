package com.hey_there.BinaryTreeLevelOrderTraversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result_levelOrder = new ArrayList<>();//储存遍历结果的List

        //若为空树，直接返回
        if (root == null) {
            return result_levelOrder;
        }

        Queue<TreeNode> traversalQueue = new LinkedList<>();//使用队列辅助遍历
        List<Integer> currentLevel = new ArrayList<>();//当前层

        traversalQueue.add(root);//根结点入队
        traversalQueue.add(null);//分层标记null

        while (!traversalQueue.isEmpty()) {
            TreeNode nextNode = traversalQueue.poll();//获得队头结点

            if (nextNode == null) {//若获得一个null结点，则完成一层的遍历
                result_levelOrder.add(currentLevel);//将当前完成的一层加入结果集合

                //检查队列中是否还有未遍历的结点,若没有则直接结束循环
                if (traversalQueue.isEmpty()) {
                    break;
                }

                currentLevel = new ArrayList<>();//新建一个当前层

                traversalQueue.add(null);//前一层遍历完成，则下一层已全部入队，加入一个分层标记null
                continue;
            }

            currentLevel.add(nextNode.val);//当前结点的值加入集合
            //若当前结点的子节点非空则将其入队
            if (nextNode.left != null) {
                traversalQueue.add(nextNode.left);
            }
            if (nextNode.right != null) {
                traversalQueue.add(nextNode.right);
            }
        }

        return result_levelOrder;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution solution = new Solution();
        System.out.println(solution.levelOrder(root));
    }
}
