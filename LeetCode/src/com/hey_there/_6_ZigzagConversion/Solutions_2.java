package com.hey_there._6_ZigzagConversion;

public class Solutions_2 {
    /* Z字变换具有周期性，周期为 2*numRows-2
     * 利用周期性直接构建结果字符串*/
    public String convert(String s, int numRows) {
        int sLen = s.length();
        if (numRows == 1 || numRows >= sLen) return s;
        StringBuilder resBuilder = new StringBuilder();
        int T = 2 * numRows - 2;//T为周期
        for (int r = 0; r < numRows; r++) {//逐行构建
            for (int t = 0; t + r < sLen; t += T) {//t为每个周期的起始下标，t是T的整数倍
                resBuilder.append(s.charAt(t + r));//每个周期内的第r个字符
                if (r > 0 && r < numRows - 1 && t + T - r < sLen)
                    resBuilder.append(s.charAt(t + T - r));
            }
        }
        return resBuilder.toString();
    }
}
