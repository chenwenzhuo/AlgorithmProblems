package com.hey_there.DecodeWays;

public class Solution {
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;//所有以字符'0'开头的串都无法解码
        }

        int sLen = s.length();//字符串长度
        if (sLen == 1) {
            return 1;//长度为1的串仅有一种解码方式
        }

        //ways[i]表示包含下标0~i的字符的子串的解码方式数
        int[] ways = new int[sLen];
        //手动初始化前两个值
        ways[0] = 1;//一个字符只有一种解码方式
        //计算ways[1]
        int value_firstTwoDigits = Integer.parseInt(s.substring(0, 2));
        if (value_firstTwoDigits > 26) {
            //若前两位表示大于26的无效值
            if (value_firstTwoDigits % 10 == 0) {
                //若前两位表示整十的值如30,80，无法进行后续解码，直接返回0
                return 0;
            } else {
                //若前两位表示大于26的非整十值，如57,88，则前两位仅有一种解码方式
                ways[1] = 1;
            }
        } else {
            if (value_firstTwoDigits % 10 == 0) {
                //若为10或20，则前两位仅有一种解码方式
                ways[1] = 1;
            } else {
                //若为非整十值，则前两位有两种解码方式
                ways[1] = 2;
            }
        }

        //计算后续值
        for (int index = 2; index < sLen; index++) {
            /*对于后续的每个字符都有两种解码方式：
             * 1.此字符单独解码
             * 2.与前一个字符一起解码*/
            //1.若当前字符单独解码
            ways[index] = s.charAt(index) == '0' ? 0 : ways[index - 1];

            //2.若当前字符与前一个字符一起解码
            if (s.charAt(index - 1) == '0') {
                //若前一个字符为0，则无法一起解码
                continue;
            }
            //当前字符和前一个字符表示的两位数的值
            int value_curAndPrev = Integer.parseInt(s.substring(index - 1, index + 1));
            if (value_curAndPrev <= 26) {
                ways[index] += ways[index - 2];
            }
        }
        /*for (int i = 0; i < sLen; i++) {
            System.out.print(ways[i] + "   ");
        }
        System.out.println();*/
        return ways[sLen - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDecodings("2257"));
    }
}
