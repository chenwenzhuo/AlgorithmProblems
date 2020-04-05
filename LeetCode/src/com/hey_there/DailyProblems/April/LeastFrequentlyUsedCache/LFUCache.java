package com.hey_there.DailyProblems.April.LeastFrequentlyUsedCache;

public class LFUCache {
    //使用一个二维数组存储
    //第一行为key，第二行为value，第三行为frequency
    private int[][] cache;
    private int capacity;
    private int count;//缓存中已有的元素数量

    public LFUCache(int capacity) {
        this.cache = new int[3][capacity];
        this.capacity = capacity;
        this.count = 0;
    }

    public int get(int key) {
        for (int i = 0; i < count; i++) {
            if (cache[0][i] == key) {
                int value = cache[1][i];
                adjustCache(i);
                return value;
            }
        }
        return -1;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        int possibleInsertionPos = -1;
        //遍历cache，查看key值是否已存在
        for (int i = count - 1; i >= 0; i--) {
            //检查cache中是否已经包含待插入的key值
            if (cache[0][i] == key) {
                //若key值已存在，直接改变其value，将frequency加1
                cache[1][i] = value;
                cache[2][i]++;
                //调整位置
                adjustCache(i);
                return;
            }
            //记录最后一个访问过的元素的位置
            if (cache[2][i] > 0 && possibleInsertionPos < 0) {
                possibleInsertionPos = i;
            }
        }
        //未达到容量时，直接插入
        if (count < capacity) {
            cache[0][count] = key;
            cache[1][count] = value;
            cache[2][count] = 1;
            adjustCache(count);
            count++;//cache中元素数量加1
            return;
        }
        cache[0][capacity - 1] = key;
        cache[1][capacity - 1] = value;
        cache[2][capacity - 1] = 1;
        adjustCache(capacity - 1);
    }

    //访问元素后调整缓存区
    private void adjustCache(int index_elementUsed) {
        //首先将被访问的元素的频率加1
        cache[2][index_elementUsed]++;
        //若被访问的是第一个元素，则无需调整
        if (index_elementUsed == 0) {
            return;
        }
        //将被访问的元素按频率排序
        int destIndex = index_elementUsed - 1;
        //向前遍历，寻找移动被访问元素的目标位置
        while (destIndex >= 0 && cache[2][destIndex] <= cache[2][index_elementUsed]) {
            destIndex--;
        }
        //循环退出后，要么destIndex位置的元素frequency大于被访问元素的frequency，要么destIndex为-1
        //将destIndex加一得到其最终值
        destIndex++;
        //保存被访问元素的key，value，frequency
        int tempKey = cache[0][index_elementUsed];
        int tempValue = cache[1][index_elementUsed];
        int tempFrequency = cache[2][index_elementUsed];
        //将destIndex到index_elementUsed闭区间内的元素后移
        for (int i = index_elementUsed - 1; i >= destIndex; i--) {
            cache[0][i + 1] = cache[0][i];
            cache[1][i + 1] = cache[1][i];
            cache[2][i + 1] = cache[2][i];
        }
        //将被访问元素的key，value，frequency填入destIndex位置
        cache[0][destIndex] = tempKey;
        cache[1][destIndex] = tempValue;
        cache[2][destIndex] = tempFrequency;
    }

    private void printCache() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < capacity; j++) {
                System.out.print(cache[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println("----------");
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        int acquiredVal;
        lfuCache.put(1, 1);
        lfuCache.printCache();
        lfuCache.put(2, 2);
        lfuCache.printCache();

        acquiredVal = lfuCache.get(2);
        System.out.println("value of key 2: " + acquiredVal);
        lfuCache.printCache();

        lfuCache.put(3, 3);
        lfuCache.printCache();

        acquiredVal = lfuCache.get(2);
        System.out.println("value of key 2: " + acquiredVal);
        lfuCache.printCache();
    }
}
