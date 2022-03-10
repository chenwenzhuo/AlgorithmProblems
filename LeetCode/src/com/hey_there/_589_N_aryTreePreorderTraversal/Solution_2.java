package com.hey_there._589_N_aryTreePreorderTraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Solution_2 {
    public List<Integer> preorder(Node root) {
        List<Integer> preRes = new ArrayList<>();
        if (root == null) return preRes;
        ArrayDeque<Node> stack = new ArrayDeque<>();
        stack.push(root);//将根节点压入栈中
        while (!stack.isEmpty()) {
            Node node = stack.pop();//每次取出栈顶节点
            preRes.add(node.val);//访问当前节点
            int numChildren = node.children.size();//当前节点的子节点数量
            //将子节点逆序压入栈中
            for (int i = numChildren - 1; i >= 0; i--)
                stack.push(node.children.get(i));
        }
        return preRes;
    }
}
