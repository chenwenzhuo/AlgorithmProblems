package com.hey_there._590_N_aryTreePostorderTraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Solution_2 {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node topNode = stack.peek();//取栈顶节点（不弹栈）
            if (topNode.children.size() == 0) {//当前为叶子节点
                stack.pop();
                res.add(topNode.val);
                continue;
            }
            //当前非叶子节点，将子节点逆序压入栈中
            for (int i = topNode.children.size() - 1; i >= 0; i--)
                stack.push(topNode.children.get(i));
            //清空子节点，防止从子节点返回当前节点时陷入死循环
            topNode.children = new ArrayList<>();
        }
        return res;
    }
}
