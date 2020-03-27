package com.hey_there.DailyProblems.CardsGrouping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Solution {
    public boolean hasGroupsSizeX_withMap(int[] deck) {
        //数组长度较小时可直接判断
        if (deck.length <= 1) {
            return false;
        } else if (deck.length == 2) {
            return deck[0] == deck[1];
        }

        //遍历数组，统计所有数字的出现次数
        Map<Integer, Integer> map = new HashMap<>();
        for (int card : deck) {
            if (map.containsKey(card)) {
                map.put(card, map.get(card) + 1);
            } else {
                map.put(card, 1);
            }
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        if (map.size() == 1) {
            return iterator.next().getValue() >= 2;
        }
        //获取前两个数的出现次数
        int occurrence1 = iterator.next().getValue();
        int occurrence2 = iterator.next().getValue();
        //求最大公约数
        int gcd = greatestCommonDivisor(occurrence1, occurrence2);
        while (iterator.hasNext() && gcd >= 2) {
            int nextOccurrence = iterator.next().getValue();
            if (nextOccurrence % gcd == 0) {
                continue;
            }
            gcd = greatestCommonDivisor(gcd, nextOccurrence);
        }
        return gcd >= 2;
    }

    public boolean hasGroupsSizeX_withArray(int[] deck) {
        //数组长度较小时可直接判断
        if (deck.length <= 1) {
            return false;
        } else if (deck.length == 2) {
            return deck[0] == deck[1];
        }

        //题目描述中规定0 <= deck[i] < 10000，使用时长10000的数组统计数字的出现次数
        int[] occurrences = new int[10000];
        Arrays.fill(occurrences, 0);
        int minCardVal = 10000, maxCardVal = -1;//记录deck数组中的最大最小值
        for (int card : deck) {
            if (card < minCardVal) {
                minCardVal = card;
            }
            if (card > maxCardVal) {
                maxCardVal = card;
            }
            occurrences[card]++;
        }

        //最大最小值相等时表示deck中数字都相等
        if (maxCardVal == minCardVal) {
            return occurrences[minCardVal] >= 2;
        }

        int ocr_1 = -1, ocr_2 = -1;
        int gcd = 0;
        for (int i = minCardVal; i <= maxCardVal; i++) {
            if (occurrences[i] == 0) {
                continue;//跳过deck中没有出现的数字
            }
            ocr_1 = ocr_2;
            ocr_2 = occurrences[i];
            if (ocr_1 >= 0 && ocr_2 >= 0) {
                gcd = greatestCommonDivisor(ocr_1, ocr_2);
                if (gcd == 1) {
                    break;
                }
                ocr_2 = gcd;
            }
        }
        return gcd >= 2;
    }

    //求两个数的最大公约数
    private int greatestCommonDivisor(int m, int n) {
        //辗转相除法
        int gcd = 1;
        int remainder;
        int bigger = Math.max(m, n);
        int smaller = Math.min(m, n);

        do {
            remainder = bigger % smaller;
            if (remainder == 0) {
                gcd = smaller;
            } else {
                bigger = smaller;
                smaller = remainder;
            }
        } while (remainder != 0);
        return gcd;
    }

    public static void main(String[] args) {
        int[] deck = {1, 1, 1, 1, 1, 1,
                2, 2, 2, 2, 2, 2, 2, 2, 2,
                3, 3, 3, 3, 3, 3, 3, 3};
    }
}
