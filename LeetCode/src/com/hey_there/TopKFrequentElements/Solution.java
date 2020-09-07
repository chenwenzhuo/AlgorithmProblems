package com.hey_there.TopKFrequentElements;

import java.util.*;

public class Solution {
    public int[] topKFrequent_1(int[] nums, int k) {
        //统计每个数字的出现次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int[][] elementAndOcc = new int[map.size()][2];//将map中的数据使用数组存放
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            elementAndOcc[idx][0] = key;
            elementAndOcc[idx][1] = value;
            idx++;
        }
        //将数组elementAndOcc按第二列排序
        Arrays.sort(elementAndOcc, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr2[1] - arr1[1];
            }
        });

        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            topK[i] = elementAndOcc[i][0];
        }
        return topK;
    }

    public int[] topKFrequent_2(int[] nums, int k) {
        //统计每个数字的出现次数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        /* 小顶堆辅助查询前k多的元素
         * 泛型数组是一个长为2的数组，存储数值元素及其出现次数
         * 传入Comparator实现将出现次数少的排在堆顶*/
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[1] - arr2[1];
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();//当前元素
            int value = entry.getValue();//当前元素的出现次数

            //堆大小未达到k时，直接插入
            if (minHeap.size() < k) {
                minHeap.add(new int[]{key, value});
                continue;
            }
            //堆大小为k时，比较堆顶元素的出现次数与当前元素的出现次数
            int[] heapTop = minHeap.peek();
            //堆顶元素出现更少时，弹出堆顶，将当前元素存入堆中
            if (heapTop[1] < value) {
                minHeap.poll();
                minHeap.add(new int[]{key, value});
            }
        }
        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            int[] heapTop = minHeap.poll();
            topK[i] = heapTop[0];
        }
        return topK;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] topK = solution.topKFrequent_2(nums, k);
        for (int n : topK) {
            System.out.println(n);
        }
    }
}
