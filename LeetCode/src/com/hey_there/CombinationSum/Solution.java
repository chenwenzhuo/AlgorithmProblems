package com.hey_there.CombinationSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private List<List<Integer>> combinations = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return combinations;
        }
        Arrays.sort(candidates);
        List<Integer> oneCombination = new ArrayList<>();
        findCombinations(candidates, 0, target, oneCombination);
        return combinations;
    }

    private void findCombinations(int[] candidates, int start, int target, List<Integer> tempComb) {
        if (target == 0) {
            List<Integer> oneComb = new ArrayList<>(tempComb);
            combinations.add(oneComb);
            return;
        }

        int len = candidates.length;
        for (int i = start; i < len; i++) {
            int tempCombSize = tempComb.size();
            if (target < candidates[i] || (tempCombSize > 0 && candidates[i] < tempComb.get(tempCombSize - 1))) {
                continue;
            }
            target -= candidates[i];
            tempComb.add(candidates[i]);
            findCombinations(candidates, i, target, tempComb);

            target += candidates[i];
            tempComb.remove(tempComb.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        Solution solution = new Solution();
        System.out.println(solution.combinationSum(candidates, 7));
    }
}
