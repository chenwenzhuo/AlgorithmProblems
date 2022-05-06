package com.hey_there._933_NumberOfRecentCalls;

import java.util.ArrayDeque;

public class RecentCounter {
    private ArrayDeque<Integer> queue;

    public RecentCounter() {
        this.queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        queue.offer(t);//将最新的请求时间加入队列
        //队列仅保留[t-3000, t]范围内的值
        int earliestCall = t - 3000;
        while (!queue.isEmpty()) {
            //检查队首元素，在[t-3000, t]范围内则退出循环
            if (queue.peek() >= earliestCall) break;
            queue.poll();//队首元素小于t-3000，从队列中移除
        }
        return queue.size();
    }
}
