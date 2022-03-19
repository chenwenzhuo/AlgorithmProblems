package com.hey_there._606_ConstructStringFromBinaryTree;

public class Solution {
    public String tree2str_1(TreeNode root) {
        if (root == null) return "";//空树返回空字符串
        //将左右子树转化为字符串
        String leftStr = tree2str_1(root.left);
        String rightStr = tree2str_1(root.right);
        //右子树非空时，需要将左右子树字符串均用括号包含并返回
        if (root.right != null)
            return root.val + "(" + leftStr + ")(" + rightStr + ")";
        //右子树为空但左子树非空时，只返回左子树字符串
        if (root.left != null)
            return root.val + "(" + leftStr + ")";
        return String.valueOf(root.val);//叶子节点只返回当前节点值
    }

    public String tree2str_2(TreeNode root) {
        if (root == null) return "";//空树返回空字符串
        if (root.left == null && root.right == null)
            return String.valueOf(root.val);//叶节点返回当前节点值
        //右子树为空，则只需要在左子树外添加括号
        if (root.right == null)
            return root.val + "(" + tree2str_2(root.left) + ")";
        //右子树非空，则需要在左右子树外均添加括号
        return root.val + "(" + tree2str_2(root.left) +
                ")(" + tree2str_2(root.right) + ")";
    }
}
