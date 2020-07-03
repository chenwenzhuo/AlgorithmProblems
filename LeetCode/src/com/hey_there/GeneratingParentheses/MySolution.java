package com.hey_there.GeneratingParentheses;

import java.util.ArrayList;
import java.util.List;

public class MySolution {
    public List<String> generateParenthesis(int n) {
        List<String> parenthesesCombinations = new ArrayList<>();

        //处理非法输入
        if (n <= 0) {
            return parenthesesCombinations;
        }
        parenthesesCombinations.add("()");//设置初始情况，即仅有一对括号

        //调用方法递归求解，每次递归插入一对括号
        return insertOnePair(parenthesesCombinations, 1, n);
    }

    /**
     * 递归方法，每次递归将在已有的字符串基础上插入一对括号，尝试所有可能的插入情况
     * @param combinations List集合，包含 k 对括号的所有组合情况，初始值为 [ "()" ]
     * @param insertedPairs 链表的字符串中已插入的括号对数
     * @param maxPairs 最大括号对数
     * @return 返回一个List集合，其中包含 maxPairs 对括号的所有组合情况
     */
    private List<String> insertOnePair(List<String> combinations, int insertedPairs, int maxPairs) {
        //递归结束条件，即List集合的字符串中已插入 maxPairs 对括号
        if (insertedPairs >= maxPairs) {
            return combinations;//返回当前List集合
        }

        //盛装插入括号后的集合的容器
        List<String> newCombinations = new ArrayList<>();

        //用来进行插入操作的StringBuilder
        StringBuilder combinationBuilder = new StringBuilder();

        //遍历当前集合中的所有组合字符串，对每一个字符串进行插入操作
        for (String oneCombination : combinations) {

            combinationBuilder.append(oneCombination);//将初始字符串加入builder

            //当前集合的字符串中包含 insertedPairs 对括号，就有 0 ~ (insertedPairs * 2) 共 insertedPairs * 2 + 1 个位置可供插入
            //对所有可插入位置进行尝试
            for (int insertIndex = 0; insertIndex <= insertedPairs * 2; insertIndex++) {
                combinationBuilder.insert(insertIndex, "()");//插入一对括号

                //检查新集合中是否包含插入后的字符串
                if (!newCombinations.contains(combinationBuilder.toString())) {
                    //若不包含，则将其加入新集合
                    newCombinations.add(combinationBuilder.toString());
                }

                //无论以上检查成功与否，在进行下一次循环前都要将刚刚插入的括号删除
                //将字符串恢复插入前的状态，以便进行下一个位置的插入尝试
                combinationBuilder.delete(insertIndex, insertIndex + 2);
            }

            //对当前集合中的下一个字符串进行插入前，清空builder
            combinationBuilder.delete(0, combinationBuilder.length());
        }

        //完成插入后，递归调用，进行下一轮插入
        return insertOnePair(newCombinations, insertedPairs + 1, maxPairs);
    }
}
