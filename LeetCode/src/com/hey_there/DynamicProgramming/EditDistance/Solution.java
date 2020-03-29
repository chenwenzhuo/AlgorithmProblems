package com.hey_there.DynamicProgramming.EditDistance;

public class Solution {
    public int minDistance(String word1, String word2) {
        //将两字符串转换为数组
        char[] array_word1 = word1.toCharArray();
        char[] array_word2 = word2.toCharArray();
        //两字符串长度
        int lenWord1 = array_word1.length;
        int lenWord2 = array_word2.length;

        /*使用一个数组来存储word1的子串到word2的子串的编辑距离。
         * editDistances[i][j]表示word1的前i个字符的子串到word2的前j个字符的子串的编辑距离。
         * 由于需要包含长度为0的子串，所以editDistances的维度为字符串长度加1*/
        int[][] editDistances = new int[lenWord1 + 1][lenWord2 + 1];

        //初始化第一行和第一列
        for (int i = 0; i <= lenWord2; i++) {
            editDistances[0][i] = i;//第一行
        }
        for (int i = 1; i <= lenWord1; i++) {
            editDistances[i][0] = i;//第一列
        }

        for (int i = 1; i <= lenWord1; i++) {
            for (int j = 1; j <= lenWord2; j++) {
                if (array_word1[i - 1] == array_word2[j - 1]) {
                    /*若当前两子串的最后一个字符相同，当前编辑距离等于去掉结尾相同字符后子串的编辑距离。
                     * 即，“good”串和“bad”串的编辑距离等于“goo”和“ba”的编辑距离*/
                    editDistances[i][j] = editDistances[i - 1][j - 1];
                } else {
                    /*若当前两子串的结尾字符不同，则有3种情况：
                     * 1.向word1插入：editDistances[i][j] = editDistances[i][j-1] + 1
                     * 2.从word1删除：editDistances[i][j] = editDistances[i-1][j] + 1
                     * 3.替换word1元素：editDistances[i][j] = editDistances[i-1][j-1] + 1
                     * editDistances[i][j]的最终取值应为其中的最小者*/
                    editDistances[i][j] = 1 + Math.min(editDistances[i - 1][j - 1],
                            Math.min(editDistances[i][j - 1], editDistances[i - 1][j]));
                }
            }
        }
        return editDistances[lenWord1][lenWord2];
    }
}
