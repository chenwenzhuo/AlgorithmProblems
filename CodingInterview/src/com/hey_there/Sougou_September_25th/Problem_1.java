package com.hey_there.Sougou_September_25th;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Problem_1 {
    public Interval solve(int n, int k, String str1, String str2) {
        //计算从0~n-1范围内选k个数的组合
        List<HashSet<Integer>> combinations = new LinkedList<>();
        LinkedList<Integer> tempComb = new LinkedList<>();
        backtrackComb(0, n - 1, k, combinations, tempComb);

        int minRight = Integer.MAX_VALUE;//最少对的题数
        int maxRight = Integer.MIN_VALUE;//最多对的题数
        //将两字符串转为字符数组
        char[] str1Arr = str1.toCharArray();
        char[] str2Arr = str2.toCharArray();
        for (HashSet<Integer> curComb : combinations) {
            int curRightMin = 0;
            int curRightMax = 0;
            for (int i = 0; i < n; i++) {
                //curComb中有i，说明朋友这道题做对了
                if (curComb.contains(i)) {
                    //汪仔需要与朋友一样才算对
                    if (str1Arr[i] == str2Arr[i]) {
                        curRightMin++;
                        curRightMax++;
                    }
                } else {//curComb中没有i，说明朋友这道题做错了
                    //若汪仔跟他不一样，则有可能对
                    if (str1Arr[i] != str2Arr[i]) {
                        curRightMax++;
                    }
                }
            }
            minRight = Math.min(minRight, curRightMin);
            maxRight = Math.max(maxRight, curRightMax);
        }
        return new Interval(minRight, maxRight);
    }

    private void backtrackComb(int start, int end, int k,
                               List<HashSet<Integer>> combinations, LinkedList<Integer> tempComb) {
        if (tempComb.size() == k) {
            combinations.add(new HashSet<>(tempComb));
            return;
        }
        for (int i = start; i <= end; i++) {
            tempComb.add(i);
            backtrackComb(i + 1, end, k, combinations, tempComb);
            tempComb.removeLast();
        }
    }
}
