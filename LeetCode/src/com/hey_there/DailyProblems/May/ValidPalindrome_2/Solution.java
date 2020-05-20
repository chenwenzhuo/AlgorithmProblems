package com.hey_there.DailyProblems.May.ValidPalindrome_2;

public class Solution {
    public boolean validPalindrome(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        boolean isPalindrome = true;
        int left = 0, right = len - 1;
        while (left <= right) {
            //若发现对称的位置上的字符不同
            if (chs[left] != chs[right]) {
                //若left位置与right位置相邻，则去掉其中任意一个都可使s回文
                //例如s="abca"时
                if (left + 1 == right) {
                    break;
                }
                //检查去掉left和去掉right之后的子串能否回文
                boolean res_deleteLeft = checkPalindrome(chs, left + 1, right);
                boolean res_deleteRight = checkPalindrome(chs, left, right - 1);
                isPalindrome = res_deleteLeft || res_deleteRight;
                break;
            }
            left++;
            right--;
        }
        return isPalindrome;
    }

    public boolean checkPalindrome(char[] chs, int left, int right) {
        while (left <= right) {
            if (chs[left] != chs[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        //String s = "abca";
        //String s = "abbaabcba";
        //String s = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
        String s = "eeccccbebaeeabebccceea";
        //String s = "abdca";
        Solution solution = new Solution();
        boolean ans = solution.validPalindrome(s);
        System.out.println(ans);
        /*System.out.println("s的长度：" + s.length());
        System.out.println("字符不相同的对称位置：");
        int left = 0, right = s.length() - 1;
        while (left <= right) {
            System.out.println(left + ":" + s.charAt(left) + "    " + right + ":" + s.charAt(right));
            left++;
            right--;
        }*/
    }
}
