package com.hey_there.Array.PascalTriangle_2;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> triangleRowList = new ArrayList<>();
        //特殊值特殊处理
        if (rowIndex == 0) {
            triangleRowList.add(1);
            return triangleRowList;
        }
        int[] triangleRow = new int[rowIndex + 1];
        //前两个值设为1，表示数组中当前存的是杨辉三角的第1行（行下标从0开始）
        triangleRow[0] = 1;
        triangleRow[1] = 1;
        for (int i = 2; i <= rowIndex; i++) {
            //生成第i行
            int leftShoulder = triangleRow[0], rightShoulder;
            for (int j = 0; j < i - 1; j++) {
                rightShoulder = triangleRow[j + 1];
                triangleRow[j + 1] = leftShoulder + rightShoulder;
                leftShoulder = rightShoulder;
            }
            triangleRow[i] = 1;//每一行都以1结尾
        }
        //将数组中的值存储到list集合中
        for (int val : triangleRow) {
            triangleRowList.add(val);
        }
        return triangleRowList;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> row = solution.getRow(4);
        System.out.println(row);
    }
}
