package com.hey_there.DailyProblems.May.MinStack;

import java.util.*;

public class MinStack_1 {
    private Deque<Integer> stack;//栈
    private List<Integer> sortedElements;//排序后的栈元素
    private int elementCounter;//栈中元素数量

    public MinStack_1() {
        this.stack = new ArrayDeque<>();
        this.sortedElements = new ArrayList<>();
        this.elementCounter = 0;
    }

    public void push(int x) {
        stack.push(x);
        elementCounter++;
        //寻找x在排序序列中的插入位置
        for (int i = 0; i < elementCounter - 1; i++) {
            if (x <= sortedElements.get(i)) {
                sortedElements.add(i, x);//插入
                return;
            }
        }
        //for循环中未找到插入位置则将x插入到队尾
        sortedElements.add(x);
    }

    public void pop() {
        Integer topElement = stack.pop();//移除栈顶元素
        elementCounter--;
        //将栈顶元素在排序序列中移除
        sortedElements.remove(topElement);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return sortedElements.get(0);
    }
}
