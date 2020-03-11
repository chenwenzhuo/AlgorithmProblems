package com.hey_there.LeastLetterOccurrence;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Solution {
    public static void leastLetters(String ascii) {
        Map<Character, Integer> charsOccurrence = new LinkedHashMap<>();
        int len = ascii.length();

        //计算所有不同字符的出现次数
        for (int i = 0; i < len; i++) {
            char curChar = ascii.charAt(i);
            if (!charsOccurrence.containsKey(curChar)) {
                charsOccurrence.put(curChar, 1);
            } else {
                int oldOccurrence = charsOccurrence.get(curChar);
                charsOccurrence.put(curChar, oldOccurrence + 1);
            }
        }

        //获取前两个字符及其出现次数
        Iterator<Map.Entry<Character, Integer>> iterator = charsOccurrence.entrySet().iterator();
        Map.Entry<Character, Integer> firstPair = iterator.next();
        Map.Entry<Character, Integer> secondPair = iterator.next();
        char firstChar = firstPair.getKey(), secondChar = secondPair.getKey();
        int firstCharOccurrence = firstPair.getValue(), secondCharOccurrence = secondPair.getValue();

        //遍历后方，检查是否还有出现次数更少的字符
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            char curChar = entry.getKey();
            int curCharOccurrence = entry.getValue();

            if (firstCharOccurrence <= secondCharOccurrence) {
                if (curCharOccurrence < secondCharOccurrence) {
                    secondChar = curChar;
                    secondCharOccurrence = curCharOccurrence;
                }
            } else {
                if (curCharOccurrence < firstCharOccurrence) {
                    firstChar = secondChar;
                    firstCharOccurrence = secondCharOccurrence;

                    secondChar = curChar;
                    secondCharOccurrence = curCharOccurrence;
                }
            }
        }
        System.out.println(firstChar + "" + secondChar);
    }

    public static void main(String[] args) {
        String str = "aaaabccd";
        leastLetters(str);
    }
}
