package com.hey_there.BinarySearchTreeIterator;

import com.hey_there.BinaryTreeInOrderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*基于中序遍历，
 * 在创建迭代器时即对树进行中序遍历，储存其中序遍历序列*/
public class BSTIterator_inOrderTraversalBased {
    private List<Integer> smallestToBiggest = new ArrayList<>();
    private int returnedNumsCount = 0;

    public BSTIterator_inOrderTraversalBased(TreeNode root) {
        genSmallToBigSequence(root);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        return smallestToBiggest.get(returnedNumsCount++);
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {

        return returnedNumsCount < smallestToBiggest.size();
    }

    private void genSmallToBigSequence(TreeNode node) {
        if (node == null) {
            return;
        }
        genSmallToBigSequence(node.left);
        smallestToBiggest.add(node.val);
        genSmallToBigSequence(node.right);
    }
}
