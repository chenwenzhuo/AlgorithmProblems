package com.hey_there._744_FindSmallestLetterGreaterThanTarget;

public class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length;
        //若target大于等于数组中最后一个字符，则返回数组第一个字符
        if (target >= letters[len - 1]) return letters[0];
        //二分查找
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            //letters[mid] <= target时，答案一定在letters[mid]右边
            if (letters[mid] <= target)
                left = mid + 1;
            else//letters[mid] > target时，letters[mid]本身可能为答案
                right = mid;
        }
        return letters[left];//搜索范围大小为1时，letters[left]即为答案
    }
}
