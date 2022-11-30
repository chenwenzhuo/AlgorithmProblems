package com.hey_there._895_MaximumFrequencyStack;

import java.util.ArrayDeque;
import java.util.HashMap;

public class FreqStack {
    private HashMap<Integer, Integer> frequencies;//记录栈中每个元素的出现频率
    private HashMap<Integer, ArrayDeque<Integer>> stacks;//key为频率，value为当前频率的数
    private int maxFreq;//最大频率

    public FreqStack() {
        this.frequencies = new HashMap<>();
        this.stacks = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        //记录出现频率
        int freqVal = frequencies.getOrDefault(val, 0) + 1;
        frequencies.put(val, freqVal);
        //若当前频率的栈已存在，将val加入此栈
        if (stacks.containsKey(freqVal)) {
            stacks.get(freqVal).push(val);
        } else {//不存在时新建一个栈，将其加入
            ArrayDeque<Integer> newStack = new ArrayDeque<>();
            newStack.push(val);
            stacks.put(freqVal, newStack);
        }
        //更新最大频率
        maxFreq = Math.max(maxFreq, freqVal);
    }

    public int pop() {
        //获取出现频率最高的值
        ArrayDeque<Integer> maxFreqStack = stacks.get(maxFreq);
        int maxFreqVal = maxFreqStack.pop();
        //更新其出现频率
        int freq = frequencies.get(maxFreqVal);
        if (freq > 1) {
            frequencies.put(maxFreqVal, freq - 1);
        } else {
            frequencies.remove(maxFreqVal);
        }
        //更新最大频率
        if (maxFreqStack.size() == 0) {//最大频率的栈已清空，最大频率减1
            maxFreq--;
        }
        return maxFreqVal;
    }
}
