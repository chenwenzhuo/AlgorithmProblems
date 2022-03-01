package com.hey_there._6_ZigzagConversion;

public class Solutions_2 {
    /* Z字变换具有周期性，周期为 2*numRows-2
     * 利用周期性直接构建结果字符串*/
    public String convert(String s, int numRows) {
        int sLen = s.length();
        if (numRows == 1 || numRows >= sLen) return s;
        StringBuilder resBuilder = new StringBuilder();
        int T = 2 * numRows - 2;
        for (int r = 0; r < numRows; r++) {//逐行构建
            for (int t = 0; t + r < sLen; t += T) {//每个周期的起始下标
                resBuilder.append(s.charAt(t + r));
                if (r > 0 && r < numRows - 1 && t + T - r < sLen)
                    resBuilder.append(s.charAt(t + T - r));
            }
        }
        return resBuilder.toString();
    }
}
