package com.hey_there._824_GoatLatin;

public class Solution {
    public String toGoatLatin(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder resBuilder = new StringBuilder();
        StringBuilder aBuilder = new StringBuilder();
        for (String word : words) {
            aBuilder.append('a');//每次增加一个a
            //检查当前单词的首字母
            char firstLetter = word.charAt(0);
            if (firstLetter == 'a' || firstLetter == 'e' || firstLetter == 'i' ||
                    firstLetter == 'o' || firstLetter == 'u' ||
                    firstLetter == 'A' || firstLetter == 'E' || firstLetter == 'I' ||
                    firstLetter == 'O' || firstLetter == 'U') {
                //首字母为元音字母，直接将原单词加入
                resBuilder.append(word);
            } else {
                //首字母为辅音字母，将首字母移到最后
                resBuilder.append(word.substring(1)).append(firstLetter);
            }
            resBuilder.append("ma").append(aBuilder).append(" ");
        }
        return resBuilder.toString().trim();
    }
}
