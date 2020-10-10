package com.hey_there.ConstructionBank_October_10th;

import java.util.Arrays;
import java.util.HashSet;

public class Problem_2 {
    public int findFriendNum(int[][] M) {
        int personNum = M.length;//总人数
        int[] pre = new int[personNum];
        Arrays.fill(pre, -1);

        for (int i = 0; i < personNum; i++) {
            for (int j = i; j < personNum; j++) {
                if (M[i][j] == 1) {
                    //检查j是否已有前驱
                    if (pre[j] >= 0) {
                        if (i != j) pre[i] = j;
                    } else {
                        pre[j] = i;
                    }
                }
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < personNum; i++) {
            int root = find(pre, i);
            set.add(root);
        }
        return set.size();
    }

    private int find(int[] pre, int x) {
        int root = x;
        while (pre[root] != root) root = pre[root];

        //路径压缩
        int i = x, j;
        while (i != root) {
            j = pre[i];
            pre[i] = root;
            i = j;
        }
        return root;
    }
}
