package com.hey_there._6_ZigzagConversion;

public class Solutions_1 {
    /**
     * 使用StringBuilder存储中间步骤的Z字
     * 在最后构建字符串时速度更快
     */
    public String convert_StringBuilder(String s, int numRows) {
        int sLength = s.length();
        if (1 == numRows || numRows >= sLength) return s;

        StringBuilder[] sbArr = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbArr[i] = new StringBuilder();
        }

        int sIdx = 0;//下一个从s中读出的字符下标
        int sbArrIdx = 0;//下一个字符要加入的StringBuilder的下标
        boolean isGoingDown = true;//当前加入字符到builder中的方向，true=向后，false=向前
        while (sIdx < sLength) {
            sbArr[sbArrIdx].append(s.charAt(sIdx));
            sIdx++;

            if (isGoingDown)
                sbArrIdx++;
            else
                sbArrIdx--;
            if (sbArrIdx == numRows - 1 || 0 == sbArrIdx)
                isGoingDown = !isGoingDown;
        }
        StringBuilder resBuilder = new StringBuilder();
        for (int i = 0; i < numRows; i++)
            resBuilder.append(sbArr[i].toString());
        return resBuilder.toString();
    }


    /**
     * 时间复杂度O(n^2)，过高
     * 时间主要消耗在从矩阵中取出字符构建字符串上
     */
    public String convert(String s, int numRows) {
        int sLength = s.length();
        if (1 == numRows || numRows >= sLength) return s;

        char[][] zigzagMatrix = new char[numRows][sLength];//二维数组保存变换后的字符
        int sIdx = 0;//下一个从s中读出的字符下标
        int mRow = 0, mCol = 0;//将字符放入矩阵中的h行、列下标
        boolean isGoingDown = true;//当前过程实在沿Z字的直边向下或是沿斜边向上？true=向下，false=向上
        while (sIdx < sLength) {
            //将读取的字符放入矩阵中
            zigzagMatrix[mRow][mCol] = s.charAt(sIdx);
            sIdx++;
            //计算下一个字符的存入位置
            if (isGoingDown) mRow++;
            else {
                mRow--;
                mCol++;
            }
            //控制Z字拐弯
            if ((0 == mRow) || (numRows - 1 == mRow))
                isGoingDown = !isGoingDown;
        }
        //用矩阵中字符构建字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < sLength; j++) {
                if (zigzagMatrix[i][j] != 0)
                    sb.append(zigzagMatrix[i][j]);
            }
        }
        return sb.toString();
    }
}
