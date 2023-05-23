package com.hey_there._1090_LargestValuesFromLabels;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        //将values和labels存到一个二维数组中便于排序
        int[][] val_lab = new int[values.length][2];
        for (int i = 0; i < values.length; i++) {
            val_lab[i][0] = values[i];
            val_lab[i][1] = labels[i];
        }
        //按照value降序排序
        Arrays.sort(val_lab, (arr1, arr2) -> arr2[0] - arr1[0]);
        //尽可能选择value大的，若label相同的选项达到useLimit个，则选择value次大的
        int totalScore = 0, chosenCnt = 0;//总分数、已选择的数量
        HashMap<Integer, Integer> label2Cnt = new HashMap<>();//存储已选择项中各label的数量
        for (int i = 0; i < val_lab.length && chosenCnt < numWanted; i++) {
            int curVal = val_lab[i][0];
            int curLab = val_lab[i][1];
            if (label2Cnt.getOrDefault(curLab, 0) >= useLimit)
                continue;//当前label的数量已达到useLimit，则不能再选择此label值的选项
            //未达到限制，则可选择此选项
            totalScore += curVal;//累加分数
            chosenCnt++;//已选择数量增加
            //当前label值的数量增加
            label2Cnt.put(curLab, label2Cnt.getOrDefault(curLab, 0) + 1);
        }
        return totalScore;
    }
}
