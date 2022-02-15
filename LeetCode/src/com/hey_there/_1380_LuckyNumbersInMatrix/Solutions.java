package com.hey_there._1380_LuckyNumbersInMatrix;

import java.util.LinkedList;
import java.util.List;

public class Solutions {
    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> lucky = new LinkedList<>();
        int rows = matrix.length, cols = matrix[0].length;

        int[] rowMinVal = new int[rows];//各行的最小值
        int[] colMaxVal = new int[cols];//各列的最大值
        for (int i = 0; i < rows; i++) {
            //1 <= matrix[i][j] <= 10^5，故100001大于matrix中所有数
            int rMin = 100001;
            for (int j = 0; j < cols; j++) {
                rMin = Math.min(rMin, matrix[i][j]);
                //java整型数组默认值为0，小于matrix中所有数
                colMaxVal[j] = Math.max(colMaxVal[j], matrix[i][j]);
            }
            rowMinVal[i] = rMin;
        }
        /*再次遍历matrix数组，
         * 若matrix[i][j]同时等于rowMinVal[i]和colMaxVal[j]，
         * 则为幸运数*/
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == rowMinVal[i]
                        && matrix[i][j] == colMaxVal[j])
                    lucky.add(matrix[i][j]);
            }
        }
        return lucky;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 7, 8},
                {9, 11, 13},
                {15, 16, 17}};

        Solutions solution = new Solutions();
        List<Integer> res = solution.luckyNumbers(matrix);
        for (int n : res) {
            System.out.println(n);
        }
    }
}
