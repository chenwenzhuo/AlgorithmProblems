package com.hey_there._806_Number_OfLinesToWriteString;

public class Solution {
    public int[] numberOfLines(int[] widths, String s) {
        int sLen = s.length();
        char[] sChs = s.toCharArray();
        int lines = 1;//需要的行数，至少为1行
        int lineLen = 0;//当前行的长度
        for (int i = 0; i < sLen; i++) {
            char curCh = sChs[i];//当前字符
            int curChLen = widths[curCh - 'a'];//当前字符所占宽度
            //若当前行剩余宽度小于当前字符所需宽度，则应转行
            if (100 - lineLen < curChLen) {
                lines++;//行数加1
                lineLen = curChLen;//新一行宽度为当前字符宽度
                continue;
            }
            //当前行剩余宽度足够时当前字符继续写在当前行
            lineLen += curChLen;
        }
        return new int[]{lines, lineLen};
    }
}
