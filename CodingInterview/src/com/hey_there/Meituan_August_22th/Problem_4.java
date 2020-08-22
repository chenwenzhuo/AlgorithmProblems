package com.hey_there.Meituan_August_22th;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Problem_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] n_k = sc.nextLine().split(" ");
        int n = Integer.parseInt(n_k[0]);
        int k = Integer.parseInt(n_k[1]);

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            String[] curLine = sc.nextLine().split(" ");
            int[] nums = new int[2];
            nums[0] = Integer.parseInt(curLine[0]);
            nums[1] = Integer.parseInt(curLine[1]);
            int min = Math.min(nums[0], nums[1]);
            int max = Math.max(nums[0], nums[1]);
            if (map.containsKey(min)) {
                map.get(min).add(max);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(max);
                map.put(min, list);
            }
        }
        int[] levels = new int[n];
        String[] levelsStr = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            levels[i] = Integer.parseInt(levelsStr[i]);
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int[] arr = new int[2];
            arr[0] = levels[i - 1];
            arr[1] = levels[i - 1];
            ans += (helper(i, arr, k, map, levels) - 1);
            ans %= 1000000007;
        }
        System.out.println(ans);
    }

    private static int helper(int node, int[] max_min, int k, HashMap<Integer, List<Integer>> map, int[] levels) {
        int[] tmp_max_min = {Math.max(max_min[0], levels[node - 1]), Math.min(max_min[1], levels[node - 1])};
        if (tmp_max_min[0] - tmp_max_min[1] > k) return 0;
        max_min[0] = tmp_max_min[0];
        max_min[1] = tmp_max_min[1];
        if (!map.containsKey(node)) return 2;
        int res = 1;
        for (int n : map.get(node)) {
            res = res * helper(n, max_min, k, map, levels);
            res %= 1000000007;
        }
        res++;
        res %= 1000000007;
        return res;
    }
}
