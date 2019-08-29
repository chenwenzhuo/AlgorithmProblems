package com.hey_there;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<TreeNode> generateTrees(int n) {
        List<List<TreeNode>> resultTrees = new ArrayList<>();

        List<TreeNode> trees_zero = new ArrayList<>();//包含0个结点的数，即仅包含null
        trees_zero.add(null);
        resultTrees.add(trees_zero);

        //range是结点值的范围，即以1~range的构建二叉搜索树
        for (int range = 1; range <= n; range++) {
            //rootVal是根结点的值
            for (int rootVal = 1; rootVal <= range; rootVal++) {

            }
        }

        return null;
    }
}
