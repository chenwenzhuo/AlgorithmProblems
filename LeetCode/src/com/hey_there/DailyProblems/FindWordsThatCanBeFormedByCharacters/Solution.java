package com.hey_there.DailyProblems.FindWordsThatCanBeFormedByCharacters;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int countCharacters_useMap(String[] words, String chars) {
        //将chars中所有字符及其出现次数存入map中
        Map<Character, Integer> charsOccurrence = new HashMap<>();
        int len_chars = chars.length();
        for (int i = 0; i < len_chars; i++) {
            char curChar = chars.charAt(i);
            if (charsOccurrence.containsKey(curChar)) {
                charsOccurrence.put(curChar, charsOccurrence.get(curChar) + 1);
            } else {
                charsOccurrence.put(curChar, 1);
            }
        }

        int lenSum = 0;//长度总和
        for (String word : words) {
            //当前遍历到的字符串中的字符及其出现次数
            Map<Character, Integer> chInWord = new HashMap<>();
            boolean canBeFormed = true;
            int len_word = word.length();

            for (int i = 0; i < len_word; i++) {
                char curChar = word.charAt(i);//word字符串中当前被遍历到的字符

                if (charsOccurrence.containsKey(curChar)) {
                    if (chInWord.containsKey(curChar) &&
                            chInWord.get(curChar) + 1 <= charsOccurrence.get(curChar)) {

                        chInWord.put(curChar, chInWord.get(curChar) + 1);

                    } else if (chInWord.containsKey(curChar) &&
                            chInWord.get(curChar) + 1 > charsOccurrence.get(curChar)) {

                        canBeFormed = false;
                        break;
                    } else {
                        chInWord.put(curChar, 1);
                    }
                } else {
                    canBeFormed = false;
                    break;
                }
            }

            if (canBeFormed) {
                lenSum += word.length();
            }
        }
        return lenSum;
    }

    public int countCharacters_useArray(String[] words, String chars) {
        //由于只有小写字母，可以用一个长26的数组存储字符的出现次数
        int[] charsOccurrence = new int[26];
        //统计chars中所有字符的出现次数
        for (char ch : chars.toCharArray()) {
            charsOccurrence[(int) (ch - 'a')]++;
        }

        //遍历words数组中所有字符串
        int lenSum = 0;
        for (String word : words) {
            boolean canBeFormed = true;
            int[] charsInWordOccurrence = new int[26];
            //统计当前字符串word中所有字符的出现次数
            for (char chInWord : word.toCharArray()) {
                int pos = (int) (chInWord - 'a');
                charsInWordOccurrence[pos]++;
                //若word中某字符出现次数大于字母表中该字符的出现次数，则不能组成word
                if (charsInWordOccurrence[pos] > charsOccurrence[pos]) {
                    canBeFormed = false;
                    break;
                }
            }
            if (canBeFormed) {
                lenSum += word.length();
            }
        }
        return lenSum;
    }
}
