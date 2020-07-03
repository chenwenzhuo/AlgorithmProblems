package com.hey_there.CombinationSum_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<List<Integer>> combinations = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //处理特殊值
        if (candidates.length == 0) {
            return combinations;
        }
        if (candidates.length == 1) {
            if (candidates[0] == target) {
                List<Integer> comb = new ArrayList<>();
                comb.add(candidates[0]);
                combinations.add(comb);
            }
            return combinations;
        }

        //将candidates数组排序，方便在findCombinations()中操作
        Arrays.sort(candidates);

        List<Integer> combination = new ArrayList<>();//计算过程中存储combination的list
        findCombinations(candidates, 0, target, combination);
        return combinations;
    }

    private void findCombinations(int[] candidates, int start, int target, List<Integer> tempComb) {
        if (target == 0) {//若target为0，说明前方已经找到了满足条件的组合
            //复制tempComb中的值并存储
            List<Integer> comb = new ArrayList<>(tempComb);
            combinations.add(comb);
            return;
        }

        int len = candidates.length;//数组长度
        for (int i = start; i < len; i++) {
            if (target < candidates[i]) {
                break;//若当前target值小于candidates[i]，则无法组成满足条件的组合
            }
            target -= candidates[i];
            tempComb.add(candidates[i]);
            findCombinations(candidates, i + 1, target, tempComb);

            target += candidates[i];
            tempComb.remove(tempComb.size() - 1);

            //跳过重复值
            while (i + 1 < len && candidates[i] == candidates[i + 1]) {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {1};
        Solution solution = new Solution();
        System.out.println(solution.combinationSum2(candidates, 1));
    }
}
