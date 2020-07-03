package com.hey_there.ReformatString;

import java.util.*;

public class Solution {
    public String reformat(String s) {
        char[] ch_s = s.toCharArray();
        //两个集合分别存放字母和数字
        List<Character> chars = new ArrayList<>(ch_s.length);
        List<Character> ints = new ArrayList<>(ch_s.length);
        //遍历s的所有字符，将数字和字母分别存放
        for (char ch : ch_s) {
            if ('0' <= ch && ch <= '9') {
                ints.add(ch);
            } else if ('a' <= ch && ch <= 'z') {
                chars.add(ch);
            }
        }
        //字母和数字的数量差距大于等于2则无法重格式化
        int num_chars = chars.size();
        int num_ints = ints.size();
        if (Math.abs(num_chars - num_ints) >= 2) {
            return "";
        }

        StringBuilder ansBuilder = new StringBuilder();
        //使用迭代器遍历两个集合
        Iterator<Character> charsIt = chars.iterator();
        Iterator<Character> intsIt = ints.iterator();
        //将数量多者作为开头字符
        if (num_chars > num_ints) {
            //每次从两个集合中各取一个字符
            while (charsIt.hasNext() && intsIt.hasNext()) {
                ansBuilder.append(charsIt.next());
                ansBuilder.append(intsIt.next());
            }
            //退出循环后较多者应还剩一个字符未取
            ansBuilder.append(charsIt.next());
        } else if (num_chars < num_ints) {
            while (charsIt.hasNext() && intsIt.hasNext()) {
                ansBuilder.append(intsIt.next());
                ansBuilder.append(charsIt.next());
            }
            ansBuilder.append(intsIt.next());
        } else {
            while (charsIt.hasNext() && intsIt.hasNext()) {
                ansBuilder.append(intsIt.next());
                ansBuilder.append(charsIt.next());
            }
        }
        return ansBuilder.toString();
    }

    public static void main(String[] args) {
        String s = "619mama";
        Solution solution = new Solution();
        System.out.println(solution.reformat(s));
    }
}
