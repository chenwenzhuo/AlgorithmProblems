package com.hey_there.Tree.BinarySearchTreeIterator;

import java.util.Stack;

/*用栈模拟中序遍历过程，
* 中序遍历在每次调用 next() 时逐步完成，而不是一次性完成遍历*/
public class BSTIterator_stackBased {
    private Stack<TreeNode> nodeStack;

    public BSTIterator_stackBased(TreeNode root) {
        //初始化成员变量
        this.nodeStack = new Stack<>();

        //从根结点到最左侧结点的路径上的所有结点入栈
        pushStack_fromRootToLeftmostNode(root);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode smallestValueNode = nodeStack.pop();

        //当前出栈的结点一定是未访问结点中值最小的一个，故不需考虑其左子树
        //若右子树非空，将右子树的最左侧路径上的结点入栈
        if (smallestValueNode.right != null) {
            pushStack_fromRootToLeftmostNode(smallestValueNode.right);
        }

        return smallestValueNode.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !nodeStack.empty();
    }

    private void pushStack_fromRootToLeftmostNode(TreeNode node) {
        while (node != null) {
            nodeStack.push(node);
            node = node.left;
        }
    }

    public static void main(String[] args) {
        //建树
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator_stackBased iterator = new BSTIterator_stackBased(root);
        System.out.println(iterator.next() + "     " + iterator.hasNext());
        System.out.println(iterator.next() + "     " + iterator.hasNext());
        System.out.println(iterator.next() + "     " + iterator.hasNext());
        System.out.println(iterator.next() + "     " + iterator.hasNext());
        System.out.println(iterator.next() + "     " + iterator.hasNext());
    }
}
