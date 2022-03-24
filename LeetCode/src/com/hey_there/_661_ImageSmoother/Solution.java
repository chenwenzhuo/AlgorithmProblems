package com.hey_there._661_ImageSmoother;

public class Solution {
    public int[][] imageSmoother(int[][] img) {
        int rows = img.length, cols = img[0].length;
        int[][] smoothImg = new int[rows][cols];
        //遍历img数组每个单元格，计算平均值
        for (int i = 0; i < rows; i++) {
            //windowSum为3x3大小窗口内的值之和
            int windowSum = 0;
            //每次转行，计算windowSum的值为第一列三个相邻单元格之和
            windowSum += i >= 1 ? img[i - 1][0] : 0;
            windowSum += img[i][0];
            windowSum += i + 1 < rows ? img[i + 1][0] : 0;
            //计算当前窗口高度
            int winHeight = 1;
            winHeight += (i >= 1 ? 1 : 0);
            winHeight += (i + 1 < rows ? 1 : 0);
            for (int j = 0; j < cols; j++) {
                //将从窗口中移出的值从windowSum中减去
                windowSum -= (i >= 1 && j >= 2 ? img[i - 1][j - 2] : 0);
                windowSum -= (j >= 2 ? img[i][j - 2] : 0);
                windowSum -= (i + 1 < rows && j >= 2 ? img[i + 1][j - 2] : 0);
                //将移进窗口的值加到windowSum
                windowSum += (i >= 1 && j + 1 < cols ? img[i - 1][j + 1] : 0);
                windowSum += (j + 1 < cols ? img[i][j + 1] : 0);
                windowSum += (i + 1 < rows && j + 1 < cols ? img[i + 1][j + 1] : 0);
                //计算当前窗口宽度
                int winWidth = 1;
                winWidth += (j >= 1 ? 1 : 0);
                winWidth += (j + 1 < cols ? 1 : 0);
                //计算当前平均值
                smoothImg[i][j] = windowSum / (winWidth * winHeight);
            }
        }
        return smoothImg;
    }

    public static void main(String[] args) {
        int[][] img = {{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
        Solution solution = new Solution();
        int[][] res = solution.imageSmoother(img);
        for (int[] row : res) {
            for (int n : row) {
                System.out.print(n + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
