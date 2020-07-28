package com.hey_there.InterviewProblem_32_BinaryTreeLevelOrderTraverse.Problem_3_ChangeOrder;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;//root为null，返回空列表

        //两个栈辅助遍历
        ArrayDeque<TreeNode> stack1 = new ArrayDeque<>();
        ArrayDeque<TreeNode> stack2 = new ArrayDeque<>();
        stack1.push(root);//根节点压入栈1
        /* 每个栈中记录一层的节点，
         * 故两个栈交替入栈出栈*/
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            //遍历栈1中的节点，将其子节点按先左后右的顺序压入栈2
            LinkedList<Integer> curLevelRes = new LinkedList<>();
            while (!stack1.isEmpty()) {
                TreeNode curNode = stack1.pop();
                curLevelRes.add(curNode.val);
                if (curNode.left != null) stack2.push(curNode.left);
                if (curNode.right != null) stack2.push(curNode.right);
            }
            //仅在当前层有节点时，将此层的遍历结果加入res集合，然后重置curLevelRes
            if (curLevelRes.size() > 0) {
                res.add(curLevelRes);
                curLevelRes = new LinkedList<>();
            }
            //遍历栈2中的节点，将其子节点按先右后左的顺序压入栈1
            while (!stack2.isEmpty()) {
                TreeNode curNode = stack2.pop();
                curLevelRes.add(curNode.val);
                if (curNode.right != null) stack1.push(curNode.right);
                if (curNode.left != null) stack1.push(curNode.left);
            }
            //仅在当前层有节点时，将此层的遍历结果加入res集合
            if (curLevelRes.size() > 0)
                res.add(curLevelRes);
        }
        return res;
    }
}
