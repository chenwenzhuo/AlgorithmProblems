package com.hey_there.KnapsackProblem.KnapsackProblemWithDependency;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入物品个数和背包容量
        String[] line = sc.nextLine().split(" ");
        int numObj = Integer.parseInt(line[0]);
        int volKnapsack = Integer.parseInt(line[1]);
        //输入各个物品体积、价值、父节点编号
        int[] volumes = new int[numObj];
        int[] values = new int[numObj];
        int[] parents = new int[numObj];
        for (int i = 0; i < numObj; i++) {
            line = sc.nextLine().split(" ");
            volumes[i] = Integer.parseInt(line[0]);
            values[i] = Integer.parseInt(line[1]);
            parents[i] = Integer.parseInt(line[2]);
        }

        //计算从每个节点开始，向上到达根节点的路径中的所有结点的体积、价值之和
        ArrayList<Integer> combVolumes = new ArrayList<>();
        ArrayList<Integer> combValues = new ArrayList<>();
        for (int i = 0; i < numObj; i++) {
            int sumVolume = 0;
            int sumValue = 0;
            int idx = i;
            while (idx != -1) {
                sumVolume += volumes[idx];
                sumValue += values[idx];
                idx = parents[idx] == -1 ? -1 : parents[idx] - 1;
            }
            combVolumes.add(sumVolume);
            combValues.add(sumValue);
        }

        //按01背包的方法计算
        int[] dp = new int[volKnapsack + 1];
        for (int i = 1; i <= combVolumes.size(); i++) {
            for (int j = volKnapsack; j >= 1; j--) {
                int curVol = combVolumes.get(i - 1);
                if (j >= curVol) {
                    dp[j] = Math.max(dp[j], dp[j - curVol] + combValues.get(i - 1));
                }
            }
        }
        System.out.println(dp[volKnapsack]);
    }
}
