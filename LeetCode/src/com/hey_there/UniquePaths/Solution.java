package com.hey_there.UniquePaths;

public class Solution {
    public int uniquePaths(int m, int n) {
        //用一个数组存储从坐标(0,0)的格子到(i,j)格子的路径数
        int[][] pathsCount = new int[m][n];

        //从(0,0)到第一行和第一列的格子都只有一种走法
        for (int i = 0; i < n; i++) {
            pathsCount[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            pathsCount[i][0] = 1;
        }

        //计算(0,0)到其他格子的走法
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //除了第一行和第一列之外，其他格子的左边和上方都有格子
                //到当前格子的路径数等于到左边格子的路径数加到上方格子的路径数
                pathsCount[i][j] = pathsCount[i][j - 1] + pathsCount[i - 1][j];
            }
        }
        return pathsCount[m - 1][n - 1];
    }

    //路径数可以用组合数的方法求得
    //方法描述见combination.jpg
    //若使用int，在数字较大时会溢出，故计算过程中变量使用long类型
    public int uniquePaths_combination(int m, int n) {
        long x = m - 1;
        long y = m + n - 2;

        if (x > (y / 2)) {
            x = y - x;
        }

        long divided = 1;
        for (long i = 0; i < x; i++) {
            divided *= (y - i);
        }

        long divisor = factorial(x);

        return (int) (divided / divisor);
    }

    private long factorial(long num) {
        long fac = 1;
        while (num > 1) {
            fac *= num;
            num--;
        }
        return fac;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.uniquePaths_combination(36, 7));
        System.out.println(solution.uniquePaths_combination(7, 3));
    }
}
