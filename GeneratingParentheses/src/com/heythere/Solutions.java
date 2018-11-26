package com.heythere;

import java.util.ArrayList;
import java.util.List;

public class Solutions {
    public List<String> generateParenthesis(int n) {
        List<String> parenthesesCombinations = new ArrayList<>();
        StringBuilder combBuilder = new StringBuilder();

        //先在List中加入一个初始的字符串，由n个“()”组成
        //后面对此字符串进行变换来生成其余的括号组合
        for (int i = 0; i < n; i++) {
            combBuilder.append("()");
        }
        parenthesesCombinations.add(combBuilder.toString());

        //independentParenthesisPairsCount是最后加入的字符串中独立的括号对的数量
        //如“()”是一个括号对，它未被其他括号包含在内，就是独立的
        //初始字符串中有n对独立括号对
        int independentParenthesisPairsCount = n;
        while (independentParenthesisPairsCount > 0) {
            //每一次循环开始时，builder中存储的是上一次循环中最后一个添加到List中的字符串
            //获取此字符串
            String tailCombination = combBuilder.toString();
            String newTailCombination = removeFirstParenthesisPair(tailCombination);//从字符串中删除一对括号
            combBuilder.replace(0, combBuilder.length(), newTailCombination);//用新字符串替换builder中原字符串

            //builder中字符串的长度
            int strLength = combBuilder.toString().length();
            for (int i = 0; i < strLength; i++) {
                //在字符串中查找右括号，在右括号的位置插入字符串“()”
                if ('(' == combBuilder.charAt(i)) {
                    continue;
                }
                combBuilder.insert(i, "()");
                parenthesesCombinations.add(combBuilder.toString());//将插入后的字符串加入List中

                //将插入的字符串“()”从builder中删除
                combBuilder.delete(i, i + 2);
            }

            independentParenthesisPairsCount--;
        }
        return parenthesesCombinations;
    }

    /**
     * 从一个括号组合字符串中移除第一对括号
     *
     * @param parenthesisComb 括号组合字符串，要从中移除第一对括号
     * @return 移除第一对括号后的字符串
     */
    private String removeFirstParenthesisPair(String parenthesisComb) {
        StringBuilder newCombBuilder = new StringBuilder();
        newCombBuilder.append(parenthesisComb);

        int firstIndex = newCombBuilder.indexOf("()");//第一个括号对的位置
        newCombBuilder.delete(firstIndex, firstIndex + 2);//移除第一个括号对

        return newCombBuilder.toString();
    }
}
