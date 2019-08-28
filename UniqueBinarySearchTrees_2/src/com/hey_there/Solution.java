package com.hey_there;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<TreeNode> generateTrees(int n) {


        return null;
    }

    public List<TreeNode> genBSTrees(int low, int high) {
        if (low == 0 && high == 0) {
            List<TreeNode> emptyList = new ArrayList<>();
            return emptyList;
        } else if (low == high) {
            List<TreeNode> basicTrees = new ArrayList<>();
            TreeNode node = new TreeNode(low);
            basicTrees.add(node);
            return basicTrees;
        } else if (low + 1 == high) {
            List<TreeNode> basicTrees = new ArrayList<>();

            TreeNode low_as_rootVal = new TreeNode(low);
            low_as_rootVal.right = new TreeNode(high);

            TreeNode high_as_rootVal = new TreeNode(high);
            high_as_rootVal.left = new TreeNode(low);

            basicTrees.add(low_as_rootVal);
            basicTrees.add(high_as_rootVal);

            return basicTrees;
        }

        return null;
    }
}
