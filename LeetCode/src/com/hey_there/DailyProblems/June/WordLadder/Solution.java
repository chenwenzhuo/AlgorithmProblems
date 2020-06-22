package com.hey_there.DailyProblems.June.WordLadder;

import java.util.*;

public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //wordList中不包含endWord时，一定无法完成转换
        if (!wordList.contains(endWord)) {
            return 0;
        }
        HashMap<String, Integer> pathLens = new HashMap<>();//记录从beginWord到作为key的word的路径长度
        HashSet<String> visitedWords = new HashSet<>();//已访问过的节点集合
        ArrayDeque<String> queue = new ArrayDeque<>();//队列辅助广度优先搜索
        pathLens.put(beginWord, 0);//beginWord到它本身路径长度为0
        visitedWords.add(beginWord);//将beginWord标记为已访问
        queue.offer(beginWord);//beginWord作为广度优先搜索的起始节点
        //广度优先搜索
        int shortest = -1;
        while (!queue.isEmpty()) {
            String curWord = queue.poll();
            Iterator<String> iterator = wordList.iterator();
            while (iterator.hasNext()) {
                String nextWord = iterator.next();
                if (visitedWords.contains(nextWord)) {
                    continue;//跳过已访问的节点
                }
                //检查curWord和nextWord之间的差异是否是1个字符
                boolean diffIsOneChar = oneCharDifferent(curWord, nextWord);
                if (diffIsOneChar && nextWord.equals(endWord)) {
                    //当nextWord是endWord时，尝试更新最短路径值
                    //shortest = Math.min(shortest, pathLens.get(curWord) + 1);
                    shortest = pathLens.get(curWord) + 1;
                    queue.clear();
                    break;
                } else if (diffIsOneChar) {
                    //记录nextWord到beginWord的路径长度
                    pathLens.put(nextWord, pathLens.get(curWord) + 1);
                    visitedWords.add(nextWord);//将nextWord标记为已访问
                    queue.offer(nextWord);//nextWord入队
                    //将nextWord从wordList中移除
                    iterator.remove();
                }
            }
        }
        /* shortest记录的是endWord到beginWord的路径长度，
         * 但方法返回的是beginWord到endWord的转换序列的长度，
         * 所以需要加1表示转换序列中的beginWord*/
        return shortest + 1;
    }

    private boolean oneCharDifferent(String s1, String s2) {
        int diff = 0;
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
            if (diff >= 2) {
                return false;
            }
        }
        return diff != 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<String>() {{
            add("hot");
            add("dot");
            add("dog");
            add("lot");
            add("log");
            add("cog");
        }};
        Solution solution = new Solution();
        int shortestPath = solution.ladderLength(beginWord, endWord, wordList);
        System.out.println(shortestPath);
    }
}
