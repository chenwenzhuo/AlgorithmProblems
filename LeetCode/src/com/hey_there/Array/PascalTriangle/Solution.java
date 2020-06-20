package com.hey_there.Array.PascalTriangle;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        //特殊值特殊处理
        if (numRows == 0) {
            return triangle;
        } else if (numRows == 1) {
            List<Integer> firstRow = new ArrayList<Integer>() {{
                add(1);
            }};
            triangle.add(firstRow);
            return triangle;
        } else if (numRows == 2) {
            List<Integer> firstRow = new ArrayList<Integer>() {{
                add(1);
            }};
            List<Integer> secondRow = new ArrayList<Integer>() {{
                add(1);
                add(1);
            }};
            triangle.add(firstRow);
            triangle.add(secondRow);
            return triangle;
        }
        //手动添加第一行
        List<Integer> firstRow = new ArrayList<Integer>() {{
            add(1);
        }};
        triangle.add(firstRow);

        int rowsToGen = numRows - 1;//还需要生成的行数
        List<Integer> prevRow = firstRow;//前一行
        while (rowsToGen > 0) {
            List<Integer> newRow = new ArrayList<>();//新的一行
            newRow.add(1);//每一行都以1开头
            for (int i = 0; i < prevRow.size() - 1; i++) {
                newRow.add(prevRow.get(i) + prevRow.get(i + 1));
            }
            newRow.add(1);//每一行都以1结尾
            triangle.add(newRow);
            prevRow = newRow;
            rowsToGen--;
        }
        return triangle;
    }
}
