package com.hey_there.Glodon_September_16th;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int x = Integer.parseInt(line[2]);
        //输入所有草的高度，存入一个小顶堆
        line = sc.nextLine().split(" ");
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            minHeap.add(Integer.parseInt(line[i]));
        }

        for (int i = 0; i < m; i++) {
            int minHeight = minHeap.poll();
            minHeight += x;
            minHeap.add(minHeight);
        }
        System.out.println(minHeap.poll());
    }
}
