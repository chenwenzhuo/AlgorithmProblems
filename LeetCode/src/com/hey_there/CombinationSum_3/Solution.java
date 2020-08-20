package com.hey_there.CombinationSum_3;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    private List<List<Integer>> combinations = new LinkedList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        LinkedList<Integer> tempComb = new LinkedList<>();
        backtrack(k, n, tempComb);
        return combinations;
    }

    private void backtrack(int k, int n, LinkedList<Integer> tempComb) {
        if (k == 0 && n == 0) {//k和n同时为0，表示已经找到所求集合
            //将集合复制一份，存入combinations中
            List<Integer> oneComb = new LinkedList<>(tempComb);
            combinations.add(oneComb);
            return;
        }
        if (k == 0 || n <= 0) return;

        int startVal = tempComb.size() == 0 ? 1 : tempComb.getLast() + 1;
        for (int i = startVal; i <= 9; i++) {
            if (tempComb.contains(i)) continue;
            if (i > n) break;
            tempComb.add(i);
            backtrack(k - 1, n - i, tempComb);
            tempComb.removeLast();
        }
    }

    public static void main(String[] args) {
        //int k = 3, n = 7;
        int k = 3, n = 9;

        Solution solution = new Solution();
        List<List<Integer>> combinations = solution.combinationSum3(k, n);
        System.out.println(combinations);
    }
}
