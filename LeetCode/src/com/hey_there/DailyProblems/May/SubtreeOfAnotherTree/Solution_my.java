package com.hey_there.DailyProblems.May.SubtreeOfAnotherTree;

public class Solution_my {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return helper(s, t, false);
    }

    private boolean helper(TreeNode s, TreeNode t, boolean tHasParent) {
        if (s == null && t == null) {
            //二者均为null时返回true
            return true;
        } else if (s == null || t == null) {
            //二者一个为null一个不为null时返回false
            return false;
        }
        //s与t的值不相等时，以t为根的树不可能与以s为根的树重合
        if (s.val != t.val) {
            //若t没有父节点，则以t为根的树有可能与s的某个子树重合
            if (!tHasParent) {
                //检查t是否与s的某子树重合
                boolean isLeftSubtree = helper(s.left, t, false);
                boolean isRightSubtree = helper(s.right, t, false);
                return isLeftSubtree || isRightSubtree;
            }
            //若t有父节点，则直接返回false
            return false;
        } else {//s与t的值不相等时
            //检查s的左子树是否与t的左子树重合，以及s的右子树是否与t的右子树重合
            boolean t_subtree_s =
                    helper(s.left, t.left, true) && helper(s.right, t.right, true);
            /* t没有父节点且以t为根的树不与以s为根的树重合时
             * 检查t是否与s的左子树或右子树重合*/
            if (!tHasParent && !t_subtree_s) {
                t_subtree_s = helper(s.left, t, false) || helper(s.right, t, false);
            }
            return t_subtree_s;
        }
    }

    public static void main(String[] args) {
        /*TreeNode s = new TreeNode(3);
        s.left = new TreeNode(4);
        s.right = new TreeNode(5);
        s.left.left = new TreeNode(1);
        s.left.right = new TreeNode(2);

        TreeNode t = new TreeNode(4);
        t.left = new TreeNode(1);
        t.right = new TreeNode(2);*/
        /*TreeNode s = new TreeNode(2);
        s.left = new TreeNode(1);
        s.left.left = new TreeNode(1);

        TreeNode t = new TreeNode(1);*/
        TreeNode s = new TreeNode(3);
        s.left = new TreeNode(4);
        s.right = new TreeNode(5);
        s.left.left = new TreeNode(1);
        s.right.left = new TreeNode(2);

        TreeNode t = new TreeNode(3);
        t.left = new TreeNode(1);
        t.right = new TreeNode(2);

        Solution_my solutionMy = new Solution_my();
        boolean ans = solutionMy.isSubtree(s, t);
        System.out.println(ans);
    }
}
