package com.heythere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solutions {
    public List<String> letterCombinations(String digits) {
        int digitsCount = digits.length();
        if (0 == digitsCount) {
            return null;
        }

        Map<Integer, String> digitsToLetters = new HashMap<>();
        initMap(digitsToLetters);

        //运行到这里，digits长度至少为1，将第一个数字对应的字符串加入List中
        List<StringBuilder> letterCombBuilders = new ArrayList<>();
        letterCombBuilders.add(new StringBuilder());
        letterCombBuilders.add(new StringBuilder());
        letterCombBuilders.add(new StringBuilder());

        switch (Integer.parseInt(digits.charAt(0) + "")) {
            case 2:
                letterCombBuilders.get(0).append("a");
                letterCombBuilders.get(1).append("b");
                letterCombBuilders.get(2).append("c");
                break;
            case 3:
                letterCombBuilders.get(0).append("d");
                letterCombBuilders.get(1).append("e");
                letterCombBuilders.get(2).append("f");
                break;
            case 4:
                letterCombBuilders.get(0).append("g");
                letterCombBuilders.get(1).append("h");
                letterCombBuilders.get(2).append("i");
                break;
            case 5:
                letterCombBuilders.get(0).append("j");
                letterCombBuilders.get(1).append("k");
                letterCombBuilders.get(2).append("l");
                break;
            case 6:
                letterCombBuilders.get(0).append("m");
                letterCombBuilders.get(1).append("n");
                letterCombBuilders.get(2).append("o");
                break;
            case 8:
                letterCombBuilders.get(0).append("t");
                letterCombBuilders.get(1).append("u");
                letterCombBuilders.get(2).append("v");
                break;
            case 7:
                letterCombBuilders.add(new StringBuilder());
                letterCombBuilders.get(0).append("p");
                letterCombBuilders.get(1).append("q");
                letterCombBuilders.get(2).append("r");
                letterCombBuilders.get(3).append("s");
                break;
            case 9:
                letterCombBuilders.add(new StringBuilder());
                letterCombBuilders.get(0).append("w");
                letterCombBuilders.get(1).append("x");
                letterCombBuilders.get(2).append("y");
                letterCombBuilders.get(3).append("z");
                break;
        }

        int digitIndex = 1;//待处理的数字的索引
        while (digitIndex < digitsCount) {
            //获得下一个待处理的数字
            int nextDigit = Integer.parseInt(digits.charAt(digitIndex) + "");

            //以下数字可代表三个字母，将StringBuilder的List扩大为原来的三倍
            if ((nextDigit >= 2 && nextDigit <= 6) || 8 == nextDigit) {
                for (int i = 0; i < letterCombBuilders.size(); i++) {
                    String strCopy = letterCombBuilders.get(i).toString();

                    letterCombBuilders.add(i, new StringBuilder(strCopy));
                    letterCombBuilders.add(i, new StringBuilder(strCopy));
                    i += 2;
                }


            }

            digitIndex++;
        }


        return null;
    }

    private void initMap(Map<Integer, String> digitsToLetters) {
        digitsToLetters.put(2, "abc");
        digitsToLetters.put(3, "def");
        digitsToLetters.put(4, "ghi");
        digitsToLetters.put(5, "jkl");
        digitsToLetters.put(6, "mno");
        digitsToLetters.put(7, "pqrs");
        digitsToLetters.put(8, "tuv");
        digitsToLetters.put(9, "wxyz");
    }
}
