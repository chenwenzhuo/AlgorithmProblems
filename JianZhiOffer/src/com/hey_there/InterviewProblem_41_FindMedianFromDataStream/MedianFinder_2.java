package com.hey_there.InterviewProblem_41_FindMedianFromDataStream;

import java.util.Comparator;
import java.util.PriorityQueue;

//使用大顶堆小顶堆实现
public class MedianFinder_2 {
    private PriorityQueue<Integer> smallerHalf;
    private PriorityQueue<Integer> biggerHalf;
    private int heapSize;//两个堆的大小之和，即堆中所有元素的数量

    public MedianFinder_2() {
        //较小的一半使用大顶堆存储
        //当heapSize为奇数时，smallerHalf中比biggerHalf多存储一个元素
        this.smallerHalf = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer n1, Integer n2) {
                return n2 - n1;
            }
        });
        //较大的一半使用小顶堆存储
        this.biggerHalf = new PriorityQueue<>();
        this.heapSize = 0;
    }

    public void addNum(int num) {
        if (smallerHalf.size() == biggerHalf.size()) {
            biggerHalf.add(num);
            smallerHalf.add(biggerHalf.poll());
        } else {
            smallerHalf.add(num);
            biggerHalf.add(smallerHalf.poll());
        }
        heapSize++;
    }

    public double findMedian() {
        if (heapSize % 2 == 1) return smallerHalf.peek();
        else {
            double n1 = smallerHalf.peek();
            double n2 = biggerHalf.peek();
            return (n1 + n2) / 2;
        }
    }

    public static void main(String[] args) {
        MedianFinder_2 finder = new MedianFinder_2();
        finder.addNum(1);
        System.out.println(finder.findMedian());
        finder.addNum(2);
        System.out.println(finder.findMedian());
        finder.addNum(3);
        System.out.println(finder.findMedian());
    }
}
