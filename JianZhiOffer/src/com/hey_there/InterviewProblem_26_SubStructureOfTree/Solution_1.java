package com.hey_there.InterviewProblem_26_SubStructureOfTree;

public class Solution_1 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return isSubStructure(A, B, true);
    }

    public boolean isSubStructure(TreeNode A, TreeNode B, boolean BIsRoot) {
        /* 当B为null且B不是整棵树的根节点时，
         * 说明B的所有节点都在A中找到对应节点，返回true*/
        if (B == null && !BIsRoot)
            return true;
        else if (B == null)
            //当B为null且B是整棵树的根节点时，返回false
            return false;
        //当B不为null但A为null时，返回false
        if (A == null) return false;

        //二者均不为null时：
        //若当前A节点的值与当前B节点的值不相等，且B是整棵树的根节点
        if (A.val != B.val && BIsRoot) {
            //检查B是否是A的左右子树的子结构
            return isSubStructure(A.left, B, true) ||
                    isSubStructure(A.right, B, true);
        } else if (A.val != B.val) {
            /* 当B不是整棵树的根节点，
             * 说明当前B节点的父节点已经与当前A节点的父节点匹配，
             * 当前B节点必须与当前A节点匹配，B才可能是A的子结构。
             * 但A，B节点的值不同，匹配失败，B不是A的子结构*/
            return false;
        }
        //若A的值与B的值相等：
        //先检查B的左右子树是否分别是A的左右子树的子结构
        boolean BLeftIsSubOfALeft = isSubStructure(A.left, B.left, false);
        boolean BRightIsSubOfARight = isSubStructure(A.right, B.right, false);
        if (BLeftIsSubOfALeft && BRightIsSubOfARight) {
            return true;
        }
        //检查B是否是A的左右子树的子结构
        boolean BIsSubOfALeft = isSubStructure(A.left, B, true);
        boolean BIsSubOfARight = isSubStructure(A.right, B, true);
        return BIsSubOfALeft || BIsSubOfARight;
    }

    public static void main(String[] args) {
        /*TreeNode A = new TreeNode(1);
        A.left = new TreeNode(2);
        A.right = new TreeNode(3);
        A.left.left = new TreeNode(4);
        TreeNode B = new TreeNode(2);
        B.left = new TreeNode(4);*/
        TreeNode A = new TreeNode(1);
        A.left = new TreeNode(2);
        A.right = new TreeNode(3);
        A.left.left = new TreeNode(4);
        TreeNode B = new TreeNode(3);

        Solution_1 solution = new Solution_1();
        boolean res = solution.isSubStructure(A, B);
        System.out.println(res);
        String s = "abc";
        String t = "a";
    }
}
