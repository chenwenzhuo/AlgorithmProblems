package com.hey_there.UniqueBinarySearchTrees_GenTrees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        //HashMap中储存节点数和不同二叉搜索树之间的映射
        HashMap<Integer, List<TreeNode>> nodeNum2Trees = new HashMap<>();
        //没有0个节点的BST，故只需存入一个null
        nodeNum2Trees.put(0, new LinkedList<TreeNode>() {{
            add(null);
        }});

        //先生成仅包含1个节点的所有BST，在此基础上生成包含2个节点的BST...以此类推，直到生成包含n个节点的所有BST
        for (int range = 1; range <= n; range++) {
            List<TreeNode> nNodeBSTs = new LinkedList<>();
            //枚举1~range范围内的值作为根节点值
            for (int rootVal = 1; rootVal <= range; rootVal++) {
                int numNodes_lSubTree = rootVal - 1;//左子树的节点数
                int numNodes_rSubTree = range - rootVal;//右子树的节点数
                //获得左右子树的所有结构
                List<TreeNode> leftTrees = nodeNum2Trees.get(numNodes_lSubTree);
                List<TreeNode> rightTrees = nodeNum2Trees.get(numNodes_rSubTree);
                //两层循环排列组合左右子树的所有结构
                for (TreeNode leftTree : leftTrees) {
                    for (TreeNode rightTree : rightTrees) {
                        TreeNode copiedTree = new TreeNode(rootVal);//根节点
                        copiedTree.left = leftTree;//左子树直接复用之前创建的树
                        //右子树需要复制之前的树，并对节点重新赋值
                        copiedTree.right = copyTree(rightTree, rootVal);
                        //加入List集合
                        nNodeBSTs.add(copiedTree);
                    }
                }
            }
            nodeNum2Trees.put(range, nNodeBSTs);
        }
        return nodeNum2Trees.get(n);
    }

    private TreeNode copyTree(TreeNode tree, int offset) {
        if (tree == null) {
            return null;
        }
        TreeNode node = new TreeNode(tree.val + offset);
        node.left = copyTree(tree.left, offset);
        node.right = copyTree(tree.right, offset);
        return node;
    }
}
