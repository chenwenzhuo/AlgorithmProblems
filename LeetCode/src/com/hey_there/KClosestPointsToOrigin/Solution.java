package com.hey_there.KClosestPointsToOrigin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution {
    public int[][] kClosest(int[][] points, int K) {
        /* map的key表示点到原点的距离平方，
         * value表示一个点集，集合中所有点到原点的距离都等于key*/
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
        PriorityQueue<Integer> distances = new PriorityQueue<>();
        for (int[] point : points) {
            //计算当前点到原点的距离
            int curDist = point[0] * point[0] + point[1] * point[1];
            //检查map中是否已有距离相等的点
            if (map.containsKey(curDist)) {
                map.get(curDist).add(point);//已有距离相等的点，将当前点加入点集
            } else {
                //没有距离相等的点，创建一个新的点集
                ArrayList<int[]> list = new ArrayList<>();
                list.add(point);
                map.put(curDist, list);
                //将距离加入小顶堆中
                distances.add(curDist);
            }
        }

        int[][] res = new int[K][];
        int resIdx = 0;
        while (resIdx < K) {
            Integer curDist = distances.poll();
            ArrayList<int[]> pointsList = map.get(curDist);
            for (int[] point : pointsList) {
                if (resIdx >= K) break;
                res[resIdx++] = point;
            }
        }
        return res;
    }
}
