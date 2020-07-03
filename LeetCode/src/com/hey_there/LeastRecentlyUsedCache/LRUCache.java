package com.hey_there.LeastRecentlyUsedCache;

import java.util.LinkedHashMap;

public class LRUCache {
    private LinkedHashMap<Integer, Integer> map;
    private final int MAX_CAPACITY;
    private int contentCount;

    public LRUCache(int capacity) {
        this.map = new LinkedHashMap<>();
        this.MAX_CAPACITY = capacity;
        this.contentCount = 0;
    }

    public int get(int key) {
        //获取key对应的value，若map中没有键值key则value为默认值-1
        int value = map.getOrDefault(key, -1);
        //map中有键值key时，将键值对移到末尾
        if (value != -1) {
            map.remove(key);
            map.put(key, value);
        }
        return value;
    }

    public void put(int key, int value) {
        //首先检查map中是否已有键值key
        if (map.containsKey(key)) {
            //已有键值key时，将此键值对移到末尾
            map.remove(key);
            map.put(key, value);
            return;
        }
        //map中没有键值key时检查容量是否已满
        //容量未满时直接加入
        if (contentCount < MAX_CAPACITY) {
            map.put(key, value);
            contentCount++;//map内键值对数量加1
            return;
        }
        //容量已满时
        //首先移除第一个键值对（即最久未访问的键值对）
        int keyToRemove = map.keySet().iterator().next();
        map.remove(keyToRemove);
        map.put(key, value);//将新键值对加入
    }
}
