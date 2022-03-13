package com.hey_there._393_UTF8Validation;

public class Solution {
    public boolean validUtf8(int[] data) {
        boolean isValid = true;
        //将所有数字转化为二进制
        int[][] binaryData = new int[data.length][];
        for (int i = 0; i < data.length; i++) {
            binaryData[i] = getBinary(data[i]);
        }
        int idx = 0;
        while (idx < data.length) {
            int[] curByte = binaryData[idx];
            int chLen = -1;//当前字符的字节长度
            if (curByte[0] == 0) chLen = 1;
            else {
                for (int i = 2; i <= 4; i++) {
                    if (curByte[i] != 0) continue;
                    chLen = i;
                    break;
                }
            }
            if ((chLen > 1 && curByte[1] == 0) || //多字节字符的起始字节，下标1的位置不能为0
                    chLen == -1 || //未找到正确的字符长度，则不是有效编码
                    idx + chLen > data.length) {//起始字节下标加上字符长度超过data长度，则不是有效编码
                isValid = false;
                break;
            }
            //检查字符的后续字节是否正确，单字节字符无需检查
            if (chLen == 1) {
                idx++;
                continue;
            }
            for (int i = idx + 1; i < idx + chLen; i++) {
                //以10开头的是有效字节
                if (binaryData[i][0] == 1 && binaryData[i][1] == 0) continue;
                //否则为无效字节
                isValid = false;
                break;
            }
            //未发现无效字节，则继续寻找，找到无效字节直接结束
            if (isValid) idx += chLen;
            else break;
        }
        return isValid;
    }

    //将整数转化为二进制
    private int[] getBinary(int num) {
        int[] binary = new int[8];
        for (int i = 7; i >= 0; i--) {
            binary[i] = num % 2;
            num /= 2;
        }
        return binary;
    }

    public static void main(String[] args) {
        int[] data = {197, 130, 1};
        Solution solution = new Solution();
        boolean res = solution.validUtf8(data);
        System.out.println(res);
    }
}
