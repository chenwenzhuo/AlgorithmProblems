package com.hey_there.String.TextJustification;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> ans = new ArrayList<>();//存放调整好的字符串
    private List<String> temp = new ArrayList<>();//存放ans集合中每个字符串中的单词
    private StringBuilder sb = new StringBuilder();//将temp集合中的单词加上空格连接成字符串
    private int wordsNumInTemp = 0;//temp集合中的单词数量
    private int tempLen = 0;//temp集合中的单词长度总和
    private int maxWidth;

    public List<String> fullJustify(String[] words, int maxWidth) {
        this.maxWidth = maxWidth;
        int numWords = words.length;

        for (int i = 0; i < numWords; i++) {
            String word = words[i];
            //将当前word和一个空格加入temp后若长度不超过maxWidth，则可直接加入
            if (tempLen + wordsNumInTemp + word.length() <= maxWidth) {
                temp.add(word);
                tempLen += word.length();
                wordsNumInTemp++;
                continue;
            }
            //若加入word和一个空格后超长，则将temp集合中字符串加上空格连接成字符串
            concatWords();
            i--;//i的值减1，避免跳过未加入temp的word
        }
        //处理最后一行
        for (int i = 0; i < wordsNumInTemp; i++) {
            sb.append(temp.get(i));
            if (i == wordsNumInTemp - 1) {
                break;
            }
            sb.append(" ");
        }
        while (sb.length() < maxWidth) {
            sb.append(" ");
        }
        ans.add(sb.toString());
        return ans;
    }

    private void concatWords() {
        //temp集合中仅有一个单词时，直接在最后补空格
        if (wordsNumInTemp == 1) {
            sb.append(temp.get(0));
            while (sb.length() < maxWidth) {
                sb.append(" ");
            }
        } else {
            //两个word之间平均需要插入的空格数
            int averageSpaces = (maxWidth - tempLen) / (wordsNumInTemp - 1);
            //无法平均分配的部分空格
            int extraSpaces = (maxWidth - tempLen) % (wordsNumInTemp - 1);
            for (int j = 0; j < wordsNumInTemp; j++) {
                sb.append(temp.get(j));//连接字符串
                //若当前已将temp中最后一个字符串连接上，则不再连接空格
                if (j == wordsNumInTemp - 1) {
                    break;
                }
                //连接空格
                for (int spaces = 0; spaces < averageSpaces; spaces++) {
                    sb.append(" ");
                }
                //若有无法平均分配的空格，则连接在尾部
                if (extraSpaces > 0) {
                    sb.append(" ");
                    extraSpaces--;
                }
            }
        }
        //连接完成后，将sb中的字符串加入ans集合
        ans.add(sb.toString());
        //重置变量
        temp.clear();
        sb.delete(0, sb.length());
        wordsNumInTemp = 0;
        tempLen = 0;
    }

    public static void main(String[] args) {
        /*String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;*/
        /*String[] words = {"What", "must", "be", "acknowledgment", "shall", "be"};
        int maxWidth = 16;*/
        /*String[] words = {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a",
                "computer.", "Art", "is", "everything", "else", "we", "do"};
        int maxWidth = 20;*/
        String[] words = {"Listen", "to", "many,", "speak", "to", "a", "few."};
        int maxWidth = 6;

        Solution solution = new Solution();
        List<String> ans = solution.fullJustify(words, maxWidth);
        /*for (String s : ans) {
            System.out.println("*" + s + "*");
        }*/
        System.out.println(ans);
    }
}
