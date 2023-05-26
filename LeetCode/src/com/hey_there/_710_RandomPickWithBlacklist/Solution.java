package com.hey_there._710_RandomPickWithBlacklist;

import java.util.HashMap;

public class Solution {
    private int wSize;//白名单大小
    private HashMap<Integer, Integer> black2white;

    public Solution(int n, int[] blacklist) {
        this.wSize = n - blacklist.length;
        this.black2white = new HashMap<>();
        //将黑名单中所有数作为键存入map中
        //这里赋值多少都可以，目的仅仅是把键存进哈希表
        //方便快速判断数字是否在黑名单内
        for (int b : blacklist)
            black2white.put(b, 0);
        //将区间[0,sSize)内的黑名单数映射到[wSize,n)中的白名单数
        int last = n - 1;
        for (int b : blacklist) {
            if (b >= wSize)
                continue;//黑名单数在[wSize,n)中，无需映射
            while (black2white.containsKey(last))
                last--;//数last是黑名单，不能作为map的值
            black2white.put(b, last);//建立映射
            last--;
        }
    }

    public int pick() {
        int randIndex = (int) (Math.random() * wSize);
        //随机数命中黑名单，返回其映射值
        if (black2white.containsKey(randIndex))
            return black2white.get(randIndex);
        return randIndex;//未命中黑名单，直接返回
    }
}
