package com.hey_there.BacktrackAlgorithm.Combinations;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution {
    private List<List<Integer>> combinations;
    private int total;
    private int chosen;

    public List<List<Integer>> combine(int n, int k) {
        this.combinations = new ArrayList<>();
        this.total = n;
        this.chosen = k;
        Deque<Integer> comb_temp = new ArrayDeque<>();
        backtrack(1, comb_temp);
        return combinations;
    }

    private void backtrack(int start, Deque<Integer> comb_temp) {
        if (comb_temp.size() == chosen) {
            combinations.add(new ArrayList<>(comb_temp));
            return;
        }

        for (int i = start; i <= total; i++) {
            comb_temp.add(i);
            backtrack(i + 1, comb_temp);
            comb_temp.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combine(4, 2));
    }
}
