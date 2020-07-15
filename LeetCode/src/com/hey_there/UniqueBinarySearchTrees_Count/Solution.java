package com.hey_there.UniqueBinarySearchTrees_Count;

public class Solution {
    /* 此处不同二叉搜索树的数量只与节点的取值范围有关，
     * 即，以1~k为节点值的不同BST数量，与以k+1~2k为节点值的不同BST数量相等*/
    public int numTrees(int n) {
        //treeCounts为dp数组，
        //treeCount[i]表示以1~i为节点值的不同BST数量
        int[] treeCount = new int[n + 1];
        treeCount[0] = 1;//规定treeCount[i]为1

        for (int i = 1; i <= n; i++) {
            //枚举1~i范围内的所有数，累加它们作为根时的BST数量
            for (int j = 1; j <= i; j++) {
                //treeCount[j - 1]是以1~j-1为节点值的BST数量
                //treeCount[i - j]是以j+1~i为节点值的BST数量
                treeCount[i] += treeCount[j - 1] * treeCount[i - j];
            }
        }
        return treeCount[n];
    }
}
