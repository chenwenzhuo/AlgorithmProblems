package com.hey_there.InterviewProblem_29_MinStack;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class MinStack {
    private ArrayDeque<Integer> stack;//栈
    private PriorityQueue<Integer> sortedElements;//排序后的元素

    public MinStack() {
        this.stack = new ArrayDeque<>();
        this.sortedElements = new PriorityQueue<>();
    }

    public void push(int x) {
        stack.push(x);
        //插入优先队列，自动排序
        sortedElements.offer(x);
    }

    public void pop() {
        Integer topElement = stack.pop();//移除栈顶元素
        sortedElements.remove(topElement);
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return sortedElements.peek();
    }
}
