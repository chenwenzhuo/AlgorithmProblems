package com.hey_there._868_BinaryGap;

public class Solution {
    public int binaryGap_1(int n) {
        int maxGap = -1;
        //逐个计算n的二进制位
        int gap = -1;//找到第一个1之前，gap初值为-1
        while (n > 0) {
            int digit = n % 2;//对于当前n，取最低二进制位
            n /= 2;
            if (digit == 1) {//若当前二进制位为1
                if (gap == -1) {//gap为-1，表示当前找到的是第一个1
                    gap = 0;
                    continue;
                }
                gap++;//gap非-1，距离增加1
                //取maxGap与gap的较大者
                maxGap = Math.max(maxGap, gap);
                gap = 0;//比较并取值后将gap重置为0
                continue;
            }
            //若当前二进制位为0
            if (gap == -1)
                continue;//还未找到第一个1，继续循环
            gap++;//距离增加1
        }
        return maxGap == -1 ? 0 : maxGap;
    }

    public int binaryGap_2(int n) {
        int maxGap = 0, last = -1;//最大间距、上一个1的位置
        for (int i = 0; n > 0; i++) {
            //按位与，仅当n的最低位为1时值为1
            if ((n & 1) == 1) {
                //检查当前的1是否是第一个1
                if (last != -1)//不是第一个1，计算最大间距
                    maxGap = Math.max(maxGap, i - last);
                last = i;//保存1的位置
            }
            n >>= 1;//将n右移一位
        }
        return maxGap;
    }
}
