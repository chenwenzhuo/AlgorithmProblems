package com.hey_there.DailyProblems.May.MinimumWindowSubstring;

import java.util.HashMap;

public class Solution {
    public String minWindow(String s, String t) {
        //将t中所有字符及其出现次数存入map
        HashMap<Character, Integer> map_chs_t = new HashMap<>();
        for (char c : t.toCharArray()) {
            if (map_chs_t.containsKey(c)) {
                map_chs_t.put(c, map_chs_t.get(c) + 1);
            } else {
                map_chs_t.put(c, 1);
            }
        }

        char[] chs_s = s.toCharArray();
        int len_s = s.length();
        String res = s + s;//res代表最终答案，为其赋一个无效的初值
        int left = 0, right = 0;//双指针
        int satisfied = 0;
        int diffChsInT = map_chs_t.keySet().size();

        while (true) {
            //右移right，直到left~right范围内子串包含t的所有字符
            while (right < len_s) {
                //当找到一个t中的字符时，将map_chs_t中对应字符数量减1
                if (map_chs_t.containsKey(chs_s[right])) {
                    int updatedCount = map_chs_t.get(chs_s[right]) - 1;
                    map_chs_t.put(chs_s[right], updatedCount);
                    //当数量减到0时表示子串已包含足够数量的此字符
                    if (updatedCount == 0) {
                        satisfied++;//将满足条件的字符数加1
                        //所有字符均满足条件时尝试更新res
                        if (satisfied == diffChsInT) {
                            String substr = s.substring(left, right + 1);
                            res = substr.length() < res.length() ? substr : res;
                            break;//停止右移right
                        }
                    }
                }
                right++;
            }
            //right移到s的尽头也无法满足条件，则无需再移动left
            if (satisfied < diffChsInT && right == len_s) {
                res = res.equals(s + s) ? "" : res;
                break;
            }
            //右移left，直到left~right范围内子串不包含t的所有字符
            while (left <= right && left < len_s) {
                //当前left指向的字符即为要从子串中删除的字符
                //若此字符是t中字符，则将map_chs_t中对应字符数量加1
                if (map_chs_t.containsKey(chs_s[left])) {
                    int updatedCount = map_chs_t.get(chs_s[left]) + 1;
                    map_chs_t.put(chs_s[left], updatedCount);
                    //加1后若大于0表示子串中没有足够数量的此字符
                    //则子串无法覆盖t中所有字符
                    if (updatedCount > 0) {
                        //将right指向下一个要检查的位置
                        if (right < len_s) {
                            right++;
                        }
                        //尝试更新res
                        String substr = s.substring(left, right);
                        res = substr.length() < res.length() ? substr : res;

                        left++;//将left右移一位
                        satisfied--;//更新satisfied
                        break;
                    }
                }
                //若将要删除的字符不是t中字符或删除后仍满足条件
                //则只需要右移left
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        /*String s = "ADOBECODEBANC";
        String t = "ABC";*/
        String s = "aa";
        String t = "aa";

        Solution solution = new Solution();
        String res = solution.minWindow(s, t);
        System.out.println(res);
    }
}
