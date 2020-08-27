package com.hey_there.JD_August_27th;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //读入n
        int n = Integer.parseInt(sc.nextLine());

        if (n == 1) {
            System.out.println(2);
            return;
        }
        if (n == 2) {
            System.out.println(3);
            return;
        }
        if (n == 3) {
            System.out.println(5);
            return;
        }

        //使用优先队列（小顶堆）对已生成的数进行排序
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(2);
        minHeap.add(3);
        minHeap.add(5);

        for (int i = 1; i < n; i++) {
            //从小顶堆中取出最小的一个
            int smallest = minHeap.poll();
            //通过最小的一个生成后续的数
            minHeap.add(Integer.parseInt(smallest + "2"));
            minHeap.add(Integer.parseInt(smallest + "3"));
            minHeap.add(Integer.parseInt(smallest + "5"));
        }
        System.out.println(minHeap.poll());
        sc.close();
    }
}
