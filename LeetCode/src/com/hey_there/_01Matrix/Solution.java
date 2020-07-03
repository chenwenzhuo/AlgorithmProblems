package com.hey_there._01Matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int matrixRows = matrix.length;
        int matrixColumns = matrix[0].length;
        int[][] ans = new int[matrixRows][matrixColumns];
        for (int[] ansRow : ans) {
            //求最小值，应使初值尽量大
            //元素到0的距离最大不会超过matrixRows + matrixColumns，可将其视为”无穷大“
            Arrays.fill(ansRow, matrixRows + matrixColumns);
        }
        //用一个队列辅助遍历
        Deque<Integer> queue = new ArrayDeque<>();
        //找出matrix中所有0并将其在matrix中的顺序位置入队
        for (int i = 0; i < matrixRows; i++) {
            for (int j = 0; j < matrixColumns; j++) {
                if (matrix[i][j] == 0) {
                    queue.offerLast(i * matrixColumns + j);//入队
                    ans[i][j] = 0;//任意一个0距离0的最短距离为0
                }
            }
        }
        //从各个0开始，进行广度优先遍历
        int[] r = {-1, 1, 0, 0};
        int[] c = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int order = queue.pollFirst();//取出队首元素
            //当前元素的坐标
            int row = order / matrixColumns;
            int column = order % matrixColumns;
            //遍历当前元素上下左右四个方向上的元素
            for (int i = 0; i < 4; i++) {
                //坐标
                int ambientRow = row + r[i];
                int ambientColumn = column + c[i];
                //若坐标越界，则继续检查下一个方向
                if (ambientRow < 0 || ambientRow >= matrixRows ||
                        ambientColumn < 0 || ambientColumn >= matrixColumns) {
                    continue;
                }
                //若ans[ambientRow][ambientColumn]未被计算过，则将其顺序位置加入队列
                if (ans[ambientRow][ambientColumn] >= (matrixRows + matrixColumns)) {
                    queue.offerLast(ambientRow * matrixColumns + ambientColumn);
                }
                //对于matrix中所有非0的位置，尝试更新它到0的最短距离
                if (matrix[ambientRow][ambientColumn] != 0) {
                    ans[ambientRow][ambientColumn] =
                            Math.min(ans[ambientRow][ambientColumn], ans[row][column] + 1);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
        Solution solution = new Solution();
        int[][] ans = solution.updateMatrix(matrix);
        for (int[] ansRow : ans) {
            for (int a : ansRow) {
                System.out.print(a + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
