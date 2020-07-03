package com.hey_there.ImplementingQueue;

import java.util.LinkedList;

public class MaxQueue {
    private LinkedList<Integer> queue;//此队列中元素按插入顺序排列
    private LinkedList<Integer> sortedQueue;//此队列中元素按从大到小排列

    public MaxQueue() {
        queue = new LinkedList<>();
        sortedQueue = new LinkedList<>();
    }

    public int max_value() {
        if (sortedQueue.isEmpty()) {
            return -1;//若队列为空，返回-1
        }
        return sortedQueue.peekFirst();//队列非空则返回最大值
    }

    public void push_back(int value) {
        queue.offer(value);//新元素入队

        //将新元素插入排序队列中适当位置
        //首先检查排序队列是否为空
        if (sortedQueue.isEmpty()) {
            sortedQueue.offer(value);//为空则直接插入
            return;
        }

        //将排序队列队尾小于value的元素全部出队
        LinkedList<Integer> temp = new LinkedList<>();
        while (!sortedQueue.isEmpty() &&
                sortedQueue.peekLast() < value) {
            int minValInQueue = sortedQueue.pollLast();
            temp.offer(minValInQueue);
        }
        sortedQueue.offer(value);//新元素入队
        //将之前弹出的元素重新入队
        while (!temp.isEmpty()) {
            sortedQueue.offer(temp.pollFirst());
        }
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        Integer valToPop = queue.poll();
        sortedQueue.remove(valToPop);
        return valToPop;
    }

    @Override
    public String toString() {
        return "MaxQueue{" +
                "queue=" + queue +
                ", sortedQueue=" + sortedQueue +
                '}';
    }

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        maxQueue.push_back(20);
        maxQueue.push_back(2);
        maxQueue.push_back(10);
        maxQueue.push_back(5);

        System.out.println(maxQueue);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue);
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue);
    }
}
