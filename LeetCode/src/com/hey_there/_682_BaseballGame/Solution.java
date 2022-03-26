package com.hey_there._682_BaseballGame;

import java.util.ArrayList;

public class Solution {
    public int calPoints(String[] ops) {
        ArrayList<Integer> list = new ArrayList<>();
        for (String s : ops) {
            int size = list.size();
            if (s.equals("+")){
                list.add(list.get(size - 1) + list.get(size - 2));
                continue;
            }
            if (s.equals("D")){
                list.add(2 * list.get(size - 1));
                continue;
            }
            if (s.equals("C")){
                list.remove(size - 1);
                continue;
            }
            list.add(Integer.parseInt(s));
        }
        int sum = 0;
        for (int n : list) sum += n;
        return sum;
    }
}
