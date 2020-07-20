package com.hey_there.InterviewProblem_8_NextNodeOfBinaryTree;

public class Solution {
    public TreeNode findNextNode(TreeNode root, TreeNode node) {
        /* 判断node节点是否有右子树，
         * node节点有右子树时下一个节点是其右子树的最左边节点*/
        if (node.right != null) {
            TreeNode rightSubTree = node.right;
            while (rightSubTree.left != null) {
                rightSubTree = rightSubTree.left;
            }
            return rightSubTree;
        }
        /* 判断node节点是否是其父节点的左儿子，
         * 当node是其父节点的左儿子时，下一个节点就是父节点*/
        if (node.parent != null && node.parent.left == node) {
            return node.parent;
        }
        /* 当node节点没有右子树，且是父节点的右儿子时，有两种情况：
         * 1.向上检查node节点的祖先，直到找到一个祖先节点ancestor，ancestor是其父节点的左儿子，此时返回ancestor的父节点；
         * 2.node是整棵树的最右边节点，它在中序遍历序列里是最后一位，此时返回null*/
        //向上检查祖先节点
        TreeNode ancestor = node.parent;
        while (ancestor.parent != null && ancestor == ancestor.parent.right) {
            ancestor = ancestor.parent;
        }
        /* ancestor不为根节点表示ancestor是其父节点的左儿子，
         * ancestor为根节点时，表示node是整棵树的最右边节点*/
        if (ancestor != root) {
            return ancestor.parent;
        }
        return null;
    }

    public static void main(String[] args) {
        //构建二叉树
        TreeNode aNode = new TreeNode('a');
        TreeNode bNode = new TreeNode('b');
        bNode.parent = aNode;
        aNode.left = bNode;
        TreeNode cNode = new TreeNode('c');
        cNode.parent = aNode;
        aNode.right = cNode;
        TreeNode dNode = new TreeNode('d');
        dNode.parent = bNode;
        bNode.left = dNode;
        TreeNode eNode = new TreeNode('e');
        eNode.parent = bNode;
        bNode.right = eNode;
        TreeNode fNode = new TreeNode('f');
        fNode.parent = cNode;
        cNode.left = fNode;
        TreeNode gNode = new TreeNode('g');
        gNode.parent = cNode;
        cNode.right = gNode;
        TreeNode hNode = new TreeNode('h');
        hNode.parent = eNode;
        eNode.left = hNode;
        TreeNode iNode = new TreeNode('i');
        iNode.parent = eNode;
        eNode.right = iNode;

        Solution solution = new Solution();
        TreeNode nextNode = solution.findNextNode(aNode, gNode);
        System.out.println(nextNode == null ? null : nextNode.val);
    }
}
