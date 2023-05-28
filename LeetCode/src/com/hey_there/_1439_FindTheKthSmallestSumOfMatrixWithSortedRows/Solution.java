package com.hey_there._1439_FindTheKthSmallestSumOfMatrixWithSortedRows;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;//矩阵的行、列数
        //取mat第0行的前k个元素，若k大于n，则取第一行全部
        int[] prev = mat[0];
        //从mat第1行起，将每个元素与prev中元素相加，保留前k个
        for (int i = 1; i < m; i++) {
            int[] curRow = mat[i];
            //两数组都为非降序，元素之和最小为首元素之和，最大为尾元素之和
            //二分查找一个阈值threshold，使得有k对元素之和小于阈值
            int minSum = prev[0] + curRow[0];
            int maxSum = prev[prev.length - 1] + curRow[n - 1];
            int temp_k = Math.min(k, prev.length * curRow.length);
            int threshold = 0;
            while (minSum <= maxSum) {
                int midSum = minSum + (maxSum - minSum) / 2;
                //计算所有元素和中，小于midSum的数量
                //left是prev的下标，right是curRow下标
                int cnt = 0;
                int left = 0, right = n - 1;
                while (left < prev.length && right >= 0) {
                    int sum = prev[left] + curRow[right];
                    if (sum <= midSum) {
                        //sum不超过midSum，则下标right之前所有元素与prev[left]相加都不超过midSum
                        cnt += (right + 1);
                        left++;//增大left，即增大prev[left]，继续尝试
                    } else
                        right--;//sum大于等于midSum，则需要减小right来减小temp
                }
                if (cnt >= temp_k) {
                    maxSum = midSum - 1;
                    threshold = midSum;
                } else
                    minSum = midSum + 1;
            }
            //对prev和curRow数组的元素求和，小于threshold的保留
            ArrayList<Integer> list = new ArrayList<>();
            for (int i1 = 0; i1 < prev.length; i1++) {
                for (int i2 = 0; i2 < n; i2++) {
                    int sum = prev[i1] + curRow[i2];
                    if (sum < threshold) list.add(sum);
                    else break;
                }
            }
            //若list元素数量不足temp_k个，用threshold补足
            while (list.size() < temp_k)
                list.add(threshold);
            //将list中元素存入数组中
            int[] arr = new int[list.size()];
            for (int j = 0; j < arr.length; j++)
                arr[j] = list.get(j);
            Arrays.sort(arr);
            prev = arr;
        }
        return prev[k - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] mat = {{1, 10, 10}, {1, 4, 5}, {2, 3, 6}};
        int k = 7;
        int res = solution.kthSmallest(mat, k);
        System.out.println(res);
    }
}
