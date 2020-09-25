package com.hey_there.Sougou_September_25th;

public class Problem_2 {
    public String rotatePassword(String[] s1, String[] s2) {
        int n = s1.length;

        //将两个字符串数组转为字符数组
        char[][] s1_arr = new char[n][];
        char[][] s2_arr = new char[n][];
        for (int i = 0; i < n; i++) {
            s1_arr[i] = s1[i].toCharArray();
            s2_arr[i] = s2[i].toCharArray();
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s1_arr[i][j] == '0') res.append(s2_arr[i][j]);
            }
        }
        rotateMatrix(s1_arr);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s1_arr[i][j] == '0') res.append(s2_arr[i][j]);
            }
        }
        rotateMatrix(s1_arr);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s1_arr[i][j] == '0') res.append(s2_arr[i][j]);
            }
        }
        rotateMatrix(s1_arr);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (s1_arr[i][j] == '0') res.append(s2_arr[i][j]);
            }
        }
        return res.toString();
    }

    private void rotateMatrix(char[][] matrix) {
        int scale = matrix.length;
        char temp;
        //转置矩阵
        for (int i = 0; i < scale; i++) {
            for (int j = i; j < scale; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //逐行反转
        for (char[] mRow : matrix) {
            int left = 0, right = scale - 1;
            while (left < right) {
                temp = mRow[left];
                mRow[left] = mRow[right];
                mRow[right] = temp;
                left++;
                right--;
            }
        }
    }
}
