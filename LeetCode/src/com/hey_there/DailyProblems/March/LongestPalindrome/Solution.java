package com.hey_there.DailyProblems.March.LongestPalindrome;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    //使用HashMap，存取时间过长
    public static int longestPalindrome_my(String s) {
        //存储字符和其出现次数之间的映射
        Map<Character, Integer> charOccurrence = new HashMap<>();
        //统计字符的出现次数
        for (char ch : s.toCharArray()) {
            if (charOccurrence.containsKey(ch)) {
                charOccurrence.put(ch, charOccurrence.get(ch) + 1);
            } else {
                charOccurrence.put(ch, 1);
            }
        }

        //遍历map，计算长度和
        int sum = 0;
        int odd = 0;//odd用于判断是否有出现次数为奇数的字符
        for (Map.Entry<Character, Integer> entry : charOccurrence.entrySet()) {
            int val = entry.getValue();
            if (val % 2 == 0) {//值是偶数，则累加
                sum += val;
            } else {
                odd = val;
                if (val >= 3) {
                    sum += (val - 1);
                }
            }
        }
        if (odd == 0) {
            return sum;
        }
        return sum + 1;
    }

    public static int longestPalindrome(String s) {
        int[] chOccurrence = new int[128];//字符串中仅包含大小写字母，可以用数组保存其出现次数
        //统计各个字符的出现次数
        for (char c : s.toCharArray())
            chOccurrence[c]++;

        int sum = 0;
        boolean hasOdd = false;//用于判断是否有出现次数为奇数的字符
        for (int occurrence : chOccurrence) {
            //当occurrence为偶数时，occurrence/2*2为原值，否则为occurrence-1
            sum += occurrence / 2 * 2;
            if (occurrence % 2 == 1) {
                hasOdd = true;
            }
        }
        if (hasOdd) {
            sum += 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        String str = "civilwartestingwhetherthatnaptionoranynartionsoconceivedan" +
                "dsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarW" +
                "ehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthose" +
                "whoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandpro" +
                "perthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecr" +
                "atewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehave" +
                "consecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittl" +
                "enotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereIt" +
                "isforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywho" +
                "foughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtot" +
                "hegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddev" +
                "otiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehi" +
                "ghlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshall" +
                "haveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeoples" +
                "hallnotperishfromtheearth";

        System.out.println(longestPalindrome_my(str));
    }
}
