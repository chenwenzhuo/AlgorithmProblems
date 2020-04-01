package com.hey_there.DailyProblems.March.ContinuousPositiveSequenceOfTargetSum;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
    //1.对暴力枚举的数学优化。solution1.png
    public static int[][] findContinuousSequence_1(int target) {
        Map<Integer, Integer> results = new LinkedHashMap<>();

        //在start值较大时，若使用int会溢出，故使用long
        for (long start = 1; start <= target / 2; start++) {
            long constantTerm = (start * start - start + target * 2) * (-1);//一元二次方程的常数项
            long delta = 1 - 4 * constantTerm;//一元二次方程的判别式
            //若判别式非负，对其开平方根
            if (delta < 0) {
                continue;
            }
            double deltaSqrt = Math.sqrt(delta);
            //判别式平方根需要为整数，且求根公式分子需要为偶数
            if (deltaSqrt != (int) deltaSqrt ||
                    (-1 + deltaSqrt) % 2 != 0) {//由于需要正整数解，所以仅取较大的根
                continue;
            }
            //满足前方条件后，计算方程的根
            int end = (int) ((-1 + deltaSqrt) / 2);
            //存储start和end键值对
            results.put((int) start, end);
        }

        //将所有结果转化成数组存储
        int resultsCount = results.size();
        int[][] resultsArr = new int[resultsCount][];//暂不初始化数组的第二维
        int row = 0;//结果数组第一维的下标
        for (Map.Entry<Integer, Integer> entry : results.entrySet()) {
            int start = entry.getKey();
            int end = entry.getValue();

            resultsArr[row] = new int[end - start + 1];
            for (int column = 0; column < resultsArr[row].length; column++) {
                resultsArr[row][column] = start + column;
            }
            row++;
        }
        return resultsArr;
    }

    //2.双指针。solution2.png
    public static int[][] findContinuousSequence_2(int target) {
        Map<Integer, Integer> results = new LinkedHashMap<>();
        int start = 1, end = 2;
        while (start < end) {
            int sum = (start + end) * (end - start + 1) / 2;
            if (sum == target) {
                results.put(start, end);
                start++;
            } else if (sum < target) {
                end++;
            } else {
                start++;
            }
        }
        //将所有结果转化成数组存储
        int resultsCount = results.size();
        int[][] resultsArr = new int[resultsCount][];//暂不初始化数组的第二维
        int row = 0;//结果数组第一维的下标
        for (Map.Entry<Integer, Integer> entry : results.entrySet()) {
            start = entry.getKey();
            end = entry.getValue();

            resultsArr[row] = new int[end - start + 1];
            for (int column = 0; column < resultsArr[row].length; column++) {
                resultsArr[row][column] = start + column;
            }
            row++;
        }
        return resultsArr;
    }

    public static void main(String[] args) {
        int[][] results = findContinuousSequence_2(98160);
        for (int[] result : results) {
            for (int r : result) {
                System.out.print(r + "  ");
            }
            System.out.println();
        }

        long n = 32719 * 32719;
        System.out.println("\n\n" + n);
        n = 1 - 4 * 32719L * 32719L;
        System.out.println(n);

        System.out.println("\n\nLong max: " + Long.MAX_VALUE);
    }
}
