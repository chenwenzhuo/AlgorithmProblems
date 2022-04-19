package com.hey_there._821_ShortestDistanceToCharacter;

import java.util.ArrayList;
import java.util.Arrays;

public class Solutions {
    public int[] shortestToChar_1(String s, char c) {
        char[] sCh = s.toCharArray();
        int sLen = s.length();
        //找出字符串s中所有字符c的下标
        ArrayList<Integer> idxesC = new ArrayList<>();
        for (int i = 0; i < sLen; i++) {
            if (sCh[i] == c) idxesC.add(i);
        }
        //计算距离
        int[] res = new int[sLen];
        Arrays.fill(res, sLen);
        for (int idx : idxesC) {
            res[idx] = 0;//下标idx处是字符c，距离为0
            //从idx处往前、往后
            int dist = 1;
            for (int i = idx - 1; i >= 0; i--) {
                //下标i距离下标idx太远，有另一个字符c距离下标i更近
                if (dist >= res[i]) break;
                res[i] = dist;
                dist++;
            }
            dist = 1;
            for (int i = idx + 1; i < sLen; i++) {
                //遇到后一个字符c，直接退出
                if (sCh[i] == c) break;
                res[i] = dist;
                dist++;
            }
        }
        return res;
    }

    public int[] shortestToChar_2(String s, char c) {
        char[] sCh = s.toCharArray();
        int sLen = s.length();
        int[] res = new int[sLen];//距离总是小于sLen，故值sLen可表示距离为"无穷大"
        //从左向右遍历，记录每个下标到左边最近的字符c的距离
        for (int i = 0, idx_c = -sLen; i < sLen; i++) {
            if (sCh[i] == c) idx_c = i;//每找到一个字符c，记录下标
            /* 记录下标i与左边字符c的距离
             * idx_c初值为-sLen，使第一个字符c左边的位置，与字符c的距离为一个"无穷大"值
             * 便于从右向左遍历使用Math.min进行比较*/
            res[i] = i - idx_c;
        }
        //从右向左遍历，记录每个下标到右边最近的字符c的距离
        for (int i = sLen - 1, idx_c = 2 * sLen; i >= 0; i--) {
            if (sCh[i] == c) idx_c = i;//每找到一个字符c，记录下标
            /* 计算每个位置与右边的字符c的距离
             * 将右边距离与当前值（左边距离）比较，取较小者*/
            res[i] = Math.min(res[i], idx_c - i);
        }
        return res;
    }
}
