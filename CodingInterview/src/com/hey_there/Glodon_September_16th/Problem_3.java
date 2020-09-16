package com.hey_there.Glodon_September_16th;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Problem_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //输入怪物数量和技能范围
        String[] line = sc.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int y = Integer.parseInt(line[1]);
        //输入各个怪物位置及其血量
        int[][] monsterInfo = new int[n][2];
        for (int i = 0; i < n; i++) {
            line = sc.nextLine().split(" ");
            monsterInfo[i][0] = Integer.parseInt(line[0]);
            monsterInfo[i][1] = Integer.parseInt(line[1]);
        }
        sc.close();
        line = null;

        //将怪物按位置排序
        Arrays.sort(monsterInfo, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[0] - arr2[0];
            }
        });

        int totalAttacks = 0;//攻击总次数
        int attackRange = y * 2;
        for (int i = 0; i < n; i++) {
            while (monsterInfo[i][1] > 0) {
                int attacksToKill = monsterInfo[i][1];//需要多少次攻击才能杀死当前怪物
                //检查aoe伤害能攻击到哪些怪物
                for (int j = i; j < n; j++) {
                    //若j位置的怪物在攻击范围内，则对其扣血
                    if (monsterInfo[j][0] - monsterInfo[i][0] <= attackRange) {
                        monsterInfo[j][1] -= attacksToKill;
                    }
                }
                totalAttacks += attacksToKill;
            }
        }
        System.out.println(totalAttacks);
    }
}
