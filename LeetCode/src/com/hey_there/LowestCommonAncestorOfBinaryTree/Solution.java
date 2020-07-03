package com.hey_there.LowestCommonAncestorOfBinaryTree;

public class Solution {
    private TreeNode commonAncestor = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        findCommonAncestor(root, p, q);
        return commonAncestor;
    }

    private int findCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return 0;
        }
        if (commonAncestor != null) {
            return 2;
        }
        //分别在左右子树中搜索目标值
        int targetsInLeftSubtree = findCommonAncestor(root.left, p, q);
        if (commonAncestor != null) {
            return 2;//左子树中已经找到公共祖先，不再检查右子树
        }
        int targetsInRightSubtree = findCommonAncestor(root.right, p, q);
        //在子树中已经找到公共祖先，则不再检查当前节点
        if (commonAncestor != null) {
            return 2;
        }
        int detectedTargetVal = targetsInLeftSubtree + targetsInRightSubtree;
        //检查当前节点是否是目标值节点
        if (root.val == p.val || root.val == q.val) {
            detectedTargetVal++;
        }
        //detectedTargetVal为2时表示当前结点是最近的公共祖先
        if (detectedTargetVal == 2) {
            commonAncestor = root;
            return 2;
        }
        return detectedTargetVal;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        Solution solution = new Solution();
        TreeNode ans = solution.lowestCommonAncestor(root, root.left, root.right);
        System.out.println(ans.val);
    }
}
