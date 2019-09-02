package com.heythere;

public class Solutions {
    /**
     * 使用StringBuilder存储中间步骤的Z字
     * 在最后构建字符串时速度更快
     */
    public String convert_StringBuilder(String s, int numRows) {
        int sLength = s.length();
        if (numRows < 1) {
            throw new IllegalArgumentException("行数为：" + numRows + "，此参数非法！");
        }
        if (s.equals("") || 1 == numRows || numRows >= sLength) {
            return s;
        }

        StringBuilder[] rowBuilders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rowBuilders[i] = new StringBuilder();
        }

        int charIndex_S = 0;//下一个从s中读出的字符下标
        int rowBuilderIndex = 0;//下一个字符要加入的StringBuilder的下标
        boolean isGoingDown = true;//当前加入字符到builder中的方向，true=向后，false=向前
        while (charIndex_S < sLength) {
            rowBuilders[rowBuilderIndex].append(s.charAt(charIndex_S));
            charIndex_S++;

            if (isGoingDown) {
                rowBuilderIndex++;
            } else {
                rowBuilderIndex--;
            }
            if (rowBuilderIndex == numRows - 1 || 0 == rowBuilderIndex) {
                isGoingDown = !isGoingDown;
            }
        }

        StringBuilder finalStringBuilder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            finalStringBuilder.append(rowBuilders[i].toString());
        }
        return finalStringBuilder.toString();
    }


    /**
     * 时间复杂度O(n^2)，过高
     * 时间主要消耗在从矩阵中取出字符构建字符串上
     */
    public String convert(String s, int numRows) {
        int sLength = s.length();
        if (numRows < 1) {
            throw new IllegalArgumentException("行数为：" + numRows + "，此参数非法！");
        }
        if (s.equals("") || 1 == numRows || numRows >= sLength) {
            return s;
        }

        char[][] zigzagMatrix = new char[numRows][sLength];//二维数组保存变换后的字符
        int charIndex_S = 0;//下一个从s中读出的字符下标
        int matrixRowIndex = 0;//将字符放入矩阵中的行号
        int matrixColumnIndex = 0;//将字符放入矩阵中的列号
        boolean isGoingDown = true;//当前过程实在沿Z字的直边向下或是沿斜边向上？true=向下，false=向上

        while (charIndex_S < sLength) {
            //将读取的字符放入矩阵中
            zigzagMatrix[matrixRowIndex][matrixColumnIndex] = s.charAt(charIndex_S);
            charIndex_S++;

            //计算下一个字符的存入位置
            if (isGoingDown) {
                matrixRowIndex++;
            } else {
                matrixRowIndex--;
                matrixColumnIndex++;
            }
            //控制Z字拐弯
            if ((0 == matrixRowIndex) || (numRows - 1 == matrixRowIndex)) {
                isGoingDown = !isGoingDown;
            }
        }

        //用矩阵中字符构建字符串
        StringBuilder buildString = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < sLength; j++) {
                if (s.contains("" + zigzagMatrix[i][j])) {
                    buildString.append(zigzagMatrix[i][j]);
                    if (buildString.length() == sLength) {
                        return buildString.toString();
                    }
                }
            }
        }

        return buildString.toString();
    }
}
