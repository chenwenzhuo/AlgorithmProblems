package com.hey_there.DailyProblems.June.ImplementQueueWithTwoStacks;

import java.util.ArrayDeque;

public class CQueue {
    private ArrayDeque<Integer> offeringStack;//此栈用来进行入队操作
    private ArrayDeque<Integer> pollingStack;//此栈用来进行出队操作

    public CQueue() {
        this.offeringStack = new ArrayDeque<>();
        this.pollingStack = new ArrayDeque<>();
    }

    public void appendTail(int value) {
        offeringStack.push(value);//入队时直接将值存入offeringStack
    }

    public int deleteHead() {
        //当pollingStack非空时可直接从中获得队首的值
        if (!pollingStack.isEmpty()) {
            return pollingStack.pop();
        }
        //当pollingStack为空
        if (offeringStack.isEmpty()) {
            return -1;//若offeringStack也为空，则队列为空，返回-1
        }
        //将offeringStack中全部元素转存到pollingStack
        while (!offeringStack.isEmpty()) {
            pollingStack.push(offeringStack.pop());
        }
        return pollingStack.pop();
    }
}
