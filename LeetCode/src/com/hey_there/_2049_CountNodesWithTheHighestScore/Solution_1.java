package com.hey_there._2049_CountNodesWithTheHighestScore;

import java.util.ArrayList;

public class Solution_1 {
    private long maxScore = 0;//最高分
    private int maxCount = 0;//最高分出现次数
    private int numNodes;//树的总节点数
    private ArrayList<Integer>[] children;//children[i]表示节点i的直接子节点

    public int countHighestScoreNodes(int[] parents) {
        numNodes = parents.length;
        children = new ArrayList[numNodes];
        for (int i = 0; i < numNodes; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 0; i < numNodes; i++) {
            int p = parents[i];//节点i的父节点为p
            //将节点i加入节点p的子节点集合
            if (p != -1) children[p].add(i);
        }
        dfs(0);
        return maxCount;
    }

    private int dfs(int root) {
        long curScore = 1;//当前节点得分
        int offspring = 0;//当前节点的后代节点数
        for (int child : children[root]) {
            int c = dfs(child);//c表示一棵子树的节点数
            offspring += c;
            curScore *= c;//累乘子树节点数
        }
        //从整棵树中去掉当前子树所有节点，剩余节点数，乘上子树得分，为当前节点最后得分
        if (root != 0) {//当前节点为根节点时不进行这部分计算
            curScore *= (numNodes - offspring - 1);
        }
        if (curScore == maxScore) maxCount++;//等于最高分，则计数器加1
        else if (curScore > maxScore) {//大于最高分，重置maxScore和maxCount
            maxScore = curScore;
            maxCount = 1;
        }
        return offspring + 1;
    }

    public static void main(String[] args) {
        int[] parents = {-1, 2, 0, 2, 0};
        Solution_1 solution = new Solution_1();
        int res = solution.countHighestScoreNodes(parents);
        System.out.println(res);
    }
}
