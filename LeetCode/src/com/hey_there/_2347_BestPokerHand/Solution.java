package com.hey_there._2347_BestPokerHand;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        HashSet<Character> suitSet = new HashSet<>();
        //统计各种点数和花色的牌的数量
        for (int i = 0; i < 5; i++) {
            rankMap.put(ranks[i], rankMap.getOrDefault(ranks[i], 0) + 1);
            suitSet.add(suits[i]);
        }
        //suitSet大小为1，表示只有一种花色，则返回Flush
        if (suitSet.size() == 1)
            return "Flush";
        //rankMap大小为5，表示五张牌大小不同，返回High Card
        if (rankMap.size() == 5)
            return "High Card";
        //检查各种点数的数量
        for (Map.Entry<Integer, Integer> entry : rankMap.entrySet())
            if (entry.getValue() >= 3)//不少于三张，为三条
                return "Three of a Kind";
        return "Pair";//没有三张以上的点数，对子
    }
}
