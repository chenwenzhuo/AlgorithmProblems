package com.hey_there.DynamicProgramming.ThreeStepsProblem;

public class Solution {
    public int waysToStep(int n) {
        if (n <= 2) {
            return n;
        } else if (n == 3) {
            return 4;
        }
        //dp[i]表示上i级台阶的方式数量
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= n; i++) {
            //直接计算 dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000007 会溢出
            //故分两步计算
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
            dp[i] = (dp[i] + dp[i - 3]) % 1000000007;
        }
        return dp[n];
    }

    public int waysToStep_matrixFastPow(int n) {
        //n较小时可直接返回，无需计算
        if (n <= 2) {
            return n;
        } else if (n == 3) {
            return 4;
        }
        int[][] A = {{0, 0, 1}, {1, 0, 1}, {0, 1, 1}};
        int[][] matrixPowResult = matPow(A, n - 3);
        int ways = 0;
        int[] initValues = {1, 2, 4};
        for (int i = 0; i < 3; i++) {
            ways = (ways + initValues[i] * matrixPowResult[i][2]) % 1000000007;
        }
        return ways;

        /*//初值，单位矩阵
        int[][] matrixPowResult = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        //用来乘方的矩阵A
        int[][] A = {{0, 0, 1}, {1, 0, 1}, {0, 1, 1}};
        int exponent = n - 3;
        while (exponent > 0) {
            if (exponent % 2 == 1) {
                matrixPowResult = matMul(matrixPowResult, A);
            }
            //计算矩阵A的平方
            A = matMul(A, A);
            exponent /= 2;
        }
        int ways = 0;
        int[] initValues = {1, 2, 4};
        for (int i = 0; i < 3; i++) {
            ways = (ways + initValues[i] * matrixPowResult[i][2]) % 1000000007;
        }
        return ways;*/
    }

    public int[][] matMul(int[][] mat1, int[][] mat2) {
        int row1 = mat1.length;
        int row2 = mat2.length, column2 = mat2[0].length;

        int[][] resultMatrix = new int[row1][column2];
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < column2; j++) {
                for (int k = 0; k < row2; k++) {
                    resultMatrix[i][j] = (resultMatrix[i][j] + mat1[i][k] * mat2[k][j]) % 1000000007;
                }
            }
        }
        return resultMatrix;
    }

    public int[][] matPow(int[][] mat, int n) {
        int rows = mat.length;
        int[][] resultMatrix = new int[rows][rows];
        //将其初始化为单位矩阵
        for (int i = 0; i < rows; i++) {
            resultMatrix[i][i] = 1;
        }
        while (n > 0) {
            if (n % 2 == 1) {
                resultMatrix = matMul(resultMatrix, mat);
            }
            mat = matMul(mat, mat);
            n /= 2;
        }
        return resultMatrix;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 1; i <= 61; i++) {
            int ans1 = solution.waysToStep(i);
            int ans2 = solution.waysToStep_matrixFastPow(i);
            System.out.println(i + "   " + ans1 + "   " + ans2);
        }
    }
}
