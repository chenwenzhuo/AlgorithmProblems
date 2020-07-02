package com.hey_there.DailyProblems.July.KthSmallestElementInSortedMatrix;

public class Solution {
    /* matrix是n*n的矩阵，故使用一个长为n的数组smallestInRows，存储每一行最小元素的下标
     * 通过迭代k次，将smallestInRows中的下标右移，即可得到matrix中第k小的值*/
    public int kthSmallest(int[][] matrix, int k) {
        int dim = matrix.length;//matrix数组的维度
        int[] smallestInRows = new int[dim];
        int curSmallest;//记录每一次迭代找到的最小值
        int curSmallestIdx = 0;//每一次迭代找到的最小值所在的行
        int kth = 0;//记录当前已找到第几小的值
        int target = 0;
        while (kth < k) {
            curSmallest = matrix[dim - 1][dim - 1] + 1;
            for (int i = 0; i < dim; i++) {
                if (smallestInRows[i] < dim &&
                        matrix[i][smallestInRows[i]] < curSmallest) {
                    curSmallest = matrix[i][smallestInRows[i]];
                    curSmallestIdx = i;
                }
            }
            target = curSmallest;
            if (smallestInRows[curSmallestIdx] < dim) {
                smallestInRows[curSmallestIdx]++;
            }
            kth++;
        }
        return target;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int k = 8;
        Solution solution = new Solution();
        int ans = solution.kthSmallest(matrix, k);
        System.out.println(ans);
    }
}
