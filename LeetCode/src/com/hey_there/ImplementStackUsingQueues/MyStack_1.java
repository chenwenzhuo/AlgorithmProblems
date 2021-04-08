package com.hey_there.ImplementStackUsingQueues;

import java.util.ArrayDeque;

public class MyStack_1 {
    private ArrayDeque<Integer> queue1, queue2;

    /**
     * Initialize your data structure here.
     */
    public MyStack_1() {
        this.queue1 = new ArrayDeque<>();
        this.queue2 = new ArrayDeque<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        if (queue2.size() == 0) queue1.offer(x);//队列2为空时，将新元素加入队列1中
        else queue2.offer(x);//队列2非空时，将新元素加入队列2中
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        int target;
        if (queue1.size() > 0) {
            while (queue1.size() > 1)
                queue2.addLast(queue1.pollFirst());
            target = queue1.pollFirst();
        } else {
            while (queue2.size() > 1)
                queue1.addLast(queue2.pollFirst());
            target = queue2.pollFirst();
        }
        return target;
    }

    /**
     * Get the top element.
     */
    public int top() {
        int topElem;
        if (queue1.size() > 0) topElem = queue1.peekLast();
        else topElem = queue2.peekLast();
        return topElem;
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue1.size() == 0 && queue2.size() == 0;
    }
}
