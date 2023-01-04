package com.hey_there.InterviewProblem_30_MinStack;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class MinStack {
    private ArrayDeque<Integer> stack;
    private ArrayDeque<Integer> minValues;

    public MinStack() {
        this.stack = new ArrayDeque<>();
        this.minValues = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minValues.isEmpty() || x <= minValues.peek())
            minValues.push(x);
    }

    public void pop() {
        int value = stack.pop();
        if (!minValues.isEmpty() && value == minValues.peek())
            minValues.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minValues.peek();
    }
}
