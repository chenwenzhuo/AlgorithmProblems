package com.hey_there.Yuanfudao_August_22th;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读入n
        int n = Integer.parseInt(sc.nextLine());
        //读入层序遍历序列，并按空格分割成数组
        ArrayList<Integer> levelOrder = new ArrayList<>(n);
        String[] valStrs = sc.nextLine().split(" ");
        //将String转为int
        for (String str : valStrs) {
            levelOrder.add(Integer.parseInt(str));
        }

        //计算完全二叉树的深度
        int treeDepth = 1;
        while (Math.pow(2, treeDepth) - 1 < n) {
            treeDepth++;
        }

        ArrayList<Integer> res = new ArrayList<>();
        int idx = 0;//每次循环，从levelOrder中取下标为idx的元素加入res
        int tempDepth = 1;
        //将每一层的最左边节点加入res集合
        while (tempDepth <= treeDepth) {
            idx = (int) (Math.pow(2, tempDepth - 1) - 1);
            res.add(levelOrder.get(idx));
            tempDepth++;
        }

        //记录倒数第二层第一个节点的下标
        int leftmostIdx = idx / 2;
        //记录倒数第二层的最后一个节点的下标
        int rightmostIdx = idx - 1;
        //记录最底层的叶子节点数
        int lowestLeavesCount = levelOrder.size() - idx;

        /*System.out.println("leftmostIdx: " + leftmostIdx);
        System.out.println("rightmostIdx: " + rightmostIdx);
        System.out.println("lowestLeavesCount: " + lowestLeavesCount);*/

        //将最底层的叶子节点加入res集合
        idx++;
        while (idx < levelOrder.size()) {
            res.add(levelOrder.get(idx));
            idx++;
        }
        //将倒数第二层的叶子节点加入res集合
        int idx1 = leftmostIdx + lowestLeavesCount / 2 - 1;
        if (idx1 == leftmostIdx) idx1++;
        while (idx1 <= rightmostIdx) {
            res.add(levelOrder.get(idx1));
            idx1++;
        }

        //将每层的最右边节点加入res集合
        idx = rightmostIdx;
        tempDepth = treeDepth - 1;
        while (tempDepth > 2) {
            idx -= (int) Math.pow(2, tempDepth - 1);
            res.add(levelOrder.get(idx));
            tempDepth--;
        }

        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i));
            if (i < res.size() - 1) System.out.print(" ");
        }
        System.out.println();
    }
}
