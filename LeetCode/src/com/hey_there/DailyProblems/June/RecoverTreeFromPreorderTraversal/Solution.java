package com.hey_there.DailyProblems.June.RecoverTreeFromPreorderTraversal;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public TreeNode recoverFromPreorder(String S) {
        if (S.equals("")) {
            return null;
        }
        int lenS = S.length();
        //第一个数字表示的一定是根节点的值，先创建根节点
        int idx = 0;
        while (idx < lenS &&
                '0' <= S.charAt(idx) && S.charAt(idx) <= '9') {
            //计算根节点的值的范围
            idx++;
        }
        int val_root = Integer.parseInt(S.substring(0, idx));
        TreeNode root = new TreeNode(val_root);

        if (idx == lenS) {//idx等于S的长度表示此根节点没有子节点
            //直接返回root
            return root;
        }
        //idx小于S的长度，则需要创建root的子树
        int dashes = 0;//根节点值之后跟的短横线的数量
        while (S.charAt(idx) == '-') {//计算短横线数量
            dashes++;
            idx++;
        }
        int leftSubtreeIdx = idx;//左子树根节点值在S中的下标
        int rightSubtreeIdx = lenS;//右子树根节点值在S中的下标
        //计算rightSubtreeIdx的值
        while (idx < lenS) {
            if (S.charAt(idx) != '-') {//跳过数字
                idx++;
                continue;
            }
            //idx指向短横线时，计算连续的短横线的数量
            int continuousDashes = 0;
            while (S.charAt(idx) == '-') {
                continuousDashes++;
                idx++;
            }
            //连续的短横线数量与dashes相等时，表示找到了右子树根节点值的下标
            if (continuousDashes == dashes) {
                rightSubtreeIdx = idx;
                break;
            }
        }
        //递归建立左子树和右子树
        if (rightSubtreeIdx < lenS) {//rightSubtreeIdx小于lenS时说明有右子树
            //左子树字符串不应该以短横线结尾
            root.left = recoverFromPreorder(S.substring(leftSubtreeIdx, rightSubtreeIdx - dashes));
            root.right = recoverFromPreorder(S.substring(rightSubtreeIdx, lenS));
        } else {
            //rightSubtreeIdx等于lenS时说明没有右子树
            root.left = recoverFromPreorder(S.substring(leftSubtreeIdx, rightSubtreeIdx));
        }
        return root;
    }

    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        //队列辅助层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        //层序遍历结果
        StringBuilder traversalRes = new StringBuilder();

        //遍历开始前将根节点存入队列
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            //当前节点为null时无需向队列中添加子节点
            if (curNode == null) {
                //向遍历结果中加入一个null
                traversalRes.append(",null");
                continue;
            }
            /*当前结点非null时需要将其子节点加入队列*/
            //将节点值加入遍历结果中
            if (traversalRes.length() > 0) {
                traversalRes.append(",");
            }
            traversalRes.append(curNode.val);
            queue.offer(curNode.left);
            queue.offer(curNode.right);
        }
        //去掉尾部连续的null
        while (traversalRes.length() > 4 && traversalRes.substring(traversalRes.length() - 4, traversalRes.length()).equals("null")) {
            traversalRes.delete(traversalRes.length() - 5, traversalRes.length());
        }
        return traversalRes.toString();
    }

    public static void main(String[] args) {
        String tree = "1-2--3--4-5--6--7";
        Solution solution = new Solution();
        TreeNode root = solution.recoverFromPreorder(tree);
        //序列化树，检查重建是否正确
        String serializedTree = solution.serialize(root);
        System.out.println(serializedTree);
    }
}
