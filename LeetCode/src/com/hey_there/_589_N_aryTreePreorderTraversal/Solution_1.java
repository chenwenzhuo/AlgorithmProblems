package com.hey_there._589_N_aryTreePreorderTraversal;

import java.util.ArrayList;
import java.util.List;

public class Solution_1 {
    private List<Integer> preRes = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        preTraversal(root);
        return preRes;
    }

    private void preTraversal(Node root) {
        if (root == null) return;
        preRes.add(root.val);
        for (Node node : root.children)
            preTraversal(node);
    }
}
