package com.hey_there.Array.CombinationSum;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<Integer>> combinations = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) {
            return combinations;
        }
        List<Integer> oneCombination = new ArrayList<>();
        findCombinations(candidates, target, oneCombination);
        return combinations;
    }

    private void findCombinations(int[] candidates, int target, List<Integer> tempComb) {
        if (target == 0) {
            List<Integer> oneComb = new ArrayList<>(tempComb);
            combinations.add(oneComb);
            return;
        }

        for (int candidate : candidates) {
            int tempCombSize = tempComb.size();
            if (target < candidate || (tempCombSize > 0 && candidate < tempComb.get(tempCombSize - 1))) {
                continue;
            }
            target -= candidate;
            tempComb.add(candidate);
            findCombinations(candidates, target, tempComb);

            target += candidate;
            tempComb.remove(tempComb.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        Solution solution = new Solution();
        System.out.println(solution.combinationSum(candidates, 7));
    }
}
