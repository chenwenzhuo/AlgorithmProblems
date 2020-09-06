package com.hey_there.Tencent_September_6th;

import java.util.HashSet;
import java.util.Scanner;

public class Problem_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);

        int[][] groups = new int[m][];
        for (int i = 0; i < m; i++) {
            line = sc.nextLine().split(" ");

            int groupLen = Integer.parseInt(line[0]);
            groups[i] = new int[groupLen];
            for (int j = 0; j < groupLen; j++) {
                groups[i][j] = Integer.parseInt(line[j + 1]);
            }
        }

        HashSet<Integer> set = new HashSet<>();//存储已知消息的人的编号
        //遍历groups，寻找编号0
        int zeroLine = -1;
        for (int i = 0; i < groups.length; i++) {
            for (int j = 0; j < groups[i].length; j++) {
                if (groups[i][j] == 0) {
                    zeroLine = i;
                    break;
                }
            }
            if (zeroLine != -1) break;
        }

        //将0所在的行的数字加入set
        for (int no : groups[zeroLine]) {
            set.add(no);
        }
        for (int i = 0; i < groups.length; i++) {
            if (i == zeroLine) continue;
            for (int j = 0; j < groups[i].length; j++) {
                if (set.contains(groups[i][j])) {
                    for (int no : groups[i]) {
                        set.add(no);
                    }
                    break;
                }
            }
        }
        System.out.println(set.size());
    }
}
