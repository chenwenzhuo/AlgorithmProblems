package com.hey_there.BinaryTreeZigzagLevelOrderTraversal;

import com.hey_there.BinaryTreeInOrderTraversal.TreeNode;

import java.util.*;

public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result_levelOrder = new ArrayList<>();//储存遍历结果的List

        //若为空树，直接返回
        if (root == null) {
            return result_levelOrder;
        }

        Queue<TreeNode> traversalQueue = new LinkedList<>();//使用队列辅助遍历
        List<Integer> currentLevel = new ArrayList<>();//当前层
        boolean traversalDirectionMark = true;//遍历方向的标记

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
                traversalDirectionMark = !traversalDirectionMark;//更改遍历方向标记
                continue;
            }

            currentLevel.add(nextNode.val);//当前结点的值加入集合

            if (traversalDirectionMark) {
                //若traversalDirectionMark为true表示当前层为从左至右，下一层应该先入队右子节点
                //若当前结点的子节点非空则将其入队
                if (nextNode.right != null) {
                    traversalQueue.add(nextNode.right);
                }
                if (nextNode.left != null) {
                    traversalQueue.add(nextNode.left);
                }
            } else {
                //若traversalDirectionMark为false表示当前层为从右至左，下一层应该先入队左子节点
                if (nextNode.left != null) {
                    traversalQueue.add(nextNode.left);
                }
                if (nextNode.right != null) {
                    traversalQueue.add(nextNode.right);
                }
            }
        }

        return result_levelOrder;
        /*List<List<Integer>> result_zigzagTraversal = new ArrayList<>();//遍历结果
        if (root == null) {
            return result_zigzagTraversal;
        }

        Queue<TreeNode> left2right = new LinkedList<>();//从左至右时用队列
        Stack<TreeNode> right2left = new Stack<>();//从右至左时用栈

        left2right.add(root);//根结点入队
        //left2right.add(null);//分层标记null

        while (!left2right.isEmpty() || !right2left.empty()) {//当队列和栈同时为空时结束循环
            List<Integer> levelResult_left2right = new ArrayList<>();//从左至右的层序结果
            while (!left2right.isEmpty()) {
                TreeNode nextQueueNode = left2right.poll();//下一个队列结点
                //将其子节点入栈
                if (nextQueueNode.left != null) {
                    right2left.push(nextQueueNode.left);
                }
                if (nextQueueNode.right != null) {
                    right2left.push(nextQueueNode.right);
                }

                levelResult_left2right.add(nextQueueNode.val);
            }
            result_zigzagTraversal.add(levelResult_left2right);

            List<Integer> levelResult_right2left = new ArrayList<>();//从右至左的层序结果
            while ()
        }

        return null;*/
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution solution = new Solution();
        System.out.println(solution.zigzagLevelOrder(root));
    }
}
