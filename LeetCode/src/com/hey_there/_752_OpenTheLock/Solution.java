package com.hey_there._752_OpenTheLock;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000"))
            return 0;
        HashSet<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        if (deadSet.contains("0000"))
            return -1;

        ArrayDeque<String> queue = new ArrayDeque<>();//队列，用于BFS
        queue.addLast("0000");//BFS起点
        HashMap<String, Integer> steps = new HashMap<>();//map用于记录BFS过程中到达每一个节点的步骤数
        steps.put("0000", 0);//到达起点仅需0步
        //BFS
        while (!queue.isEmpty()) {
            String cur = queue.removeFirst();//移除队头
            int curStep = steps.get(cur);//到达当前节点需要的步骤数
            //计算从cur可以到达的下一个节点
            for (int i = 0; i < 4; i++) {
                char c = cur.charAt(i);
                //旋转当前数字一次，可到达的位置
                String next1 = cur.substring(0, i) +
                        (c == '9' ? '0' : (char) (c + 1)) +
                        cur.substring(i + 1);
                String next2 = cur.substring(0, i) +
                        (c == '0' ? '9' : (char) (c - 1)) +
                        cur.substring(i + 1);
                int nextStep = curStep + 1;
                //检查是否到达target
                if (next1.equals(target) || next2.equals(target))
                    return nextStep;//到达target，返回步骤数
                //未到达，记录步骤数
                if (!deadSet.contains(next1))//下一步的数值不能在deadSet中
                    recordStep(queue, steps, next1, nextStep);
                if (!deadSet.contains(next2))//下一步的数值不能在deadSet中
                    recordStep(queue, steps, next2, nextStep);
            }
        }
        return -1;
    }

    private void recordStep(ArrayDeque<String> queue, HashMap<String, Integer> steps, String pos, int posStep) {
        if (!steps.containsKey(pos)) {//到达一个新的节点
            queue.addLast(pos);//节点加入队列
            steps.put(pos, posStep);//记录步骤数
            return;
        }
        //节点pos为已有节点时，比较原步骤数和新步骤数，取较小者
        int oldStep = steps.get(pos);
        if (oldStep <= posStep) return;//原步骤数更小时，不进行操作
        //新步骤数更小时，更新步骤数
        steps.put(pos, posStep);
    }
}
