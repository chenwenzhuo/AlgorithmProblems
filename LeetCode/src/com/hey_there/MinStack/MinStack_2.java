package com.hey_there.MinStack;

import java.util.*;

public class MinStack_2 {
    private Deque<Integer> stack;//栈
    private PriorityQueue<Integer> sortedElements;//排序后的元素

    public MinStack_2() {
        this.stack = new ArrayDeque<>();
        sortedElements = new PriorityQueue<>();
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

    public int getMin() {
        return sortedElements.peek();
    }
}
