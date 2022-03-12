package com.hey_there._590_N_aryTreePostorderTraversal;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {
    private List<Integer> res = new ArrayList<>();

    public List<Integer> postorder(Node root) {
        postTraversal(root);
        return res;
    }

    private void postTraversal(Node root) {
        if (root == null) return;
        //当前root非叶子节点
        for (Node child : root.children)//先遍历所有子节点
            postTraversal(child);
        res.add(root.val);//访问当前节点
    }
}
