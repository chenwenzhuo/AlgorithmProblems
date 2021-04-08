package com.hey_there.ImplementStackUsingQueues;

import java.util.ArrayDeque;

public class MyStack_2 {
    private ArrayDeque<Integer> queue1, queue2;

    /**
     * Initialize your data structure here.
     */
    public MyStack_2() {
        this.queue1 = new ArrayDeque<>();
        this.queue2 = new ArrayDeque<>();
    }

    /**
     * Push element x onto stack.
     */
    public void push(int x) {
        /* 使用queue1存放栈中已有元素，
         * 入栈时始终将新元素加入queue2中*/
        queue2.addLast(x);
        //将queue1中元素全部存入queue2中
        while (!queue1.isEmpty())
            queue2.addLast(queue1.pollFirst());
        //交换queue1和queue2引用指向的对象
        ArrayDeque<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue1.pollFirst();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue1.peekFirst();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
