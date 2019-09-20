package com.hey_there.Tree.BinaryTreeZigzagLevelOrderTraversal;

import java.util.*;

public class Solution {
    public List<List<Integer>> zigzagLevelOrder_1(TreeNode root) {
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

            if (traversalDirectionMark) {
                //若traversalDirectionMark为true表示当前层为从左至右，新结点加在集合尾
                currentLevel.add(nextNode.val);//当前结点的值加入集合
            } else {
                //否则在集合头插入
                currentLevel.add(0, nextNode.val);
            }

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

    public List<List<Integer>> zigzagLevelOrder_2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 记录是否反转
        boolean isReverse = false;
        while (!queue.isEmpty()) {
            LinkedList<Integer> oneLevel = new LinkedList<>();
            // 每次都取出一层的所有数据
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (!isReverse)
                    oneLevel.add(node.val);
                else
                    oneLevel.addFirst(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            isReverse = !isReverse;
            result.add(oneLevel);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Solution solution = new Solution();
        System.out.println(solution.zigzagLevelOrder_1(root));
    }
}
