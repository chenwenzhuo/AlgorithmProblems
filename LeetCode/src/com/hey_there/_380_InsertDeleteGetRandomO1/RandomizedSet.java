package com.hey_there._380_InsertDeleteGetRandomO1;

import java.util.ArrayList;
import java.util.HashMap;

public class RandomizedSet {
    //map的key为数据值，value为此数据值在ArrayList集合中的下标
    private HashMap<Integer, Integer> val2Index;
    private ArrayList<Integer> values;//存储数据值

    public RandomizedSet() {
        this.val2Index = new HashMap<>();
        this.values = new ArrayList<>();
    }

    public boolean insert(int val) {
        //集合中存在当前参数值，返回false
        if (val2Index.containsKey(val)) return false;
        //不存在当前参数值时，进行插入
        values.add(val);
        val2Index.put(val, values.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        //集合中不存在当前参数值，返回false
        if (!val2Index.containsKey(val)) return false;
        //存在当前参数值时，进行删除
        int index = val2Index.get(val);//待删除的值在ArrayList中的下标
        int size = values.size();
        //将待删除的值交换到ArrayList最后
        Integer temp = values.get(index);
        values.set(index, values.get(size - 1));
        values.set(size - 1, temp);
        //ArrayList原来的最后一个值被交换到前方，需要修改其在HashMap中的下标
        val2Index.put(values.get(index), index);
        //删除ArrayList最后一个值和HashMap中val对应的键值对
        values.remove(size - 1);
        val2Index.remove(val);
        return true;
    }

    public int getRandom() {
        int size = values.size();
        //产生随机下标
        int randIndex = (int) (size * Math.random());
        return values.get(randIndex);
    }
}
