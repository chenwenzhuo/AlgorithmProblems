package com.hey_there._1233_RemoveSubfoldersFromFilesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        int low = 0, high;
        while (low < folder.length) {
            ans.add(folder[low]);//每次循环的第一个，一定是父文件夹
            high = low + 1;//high指向low的下一个位置，向后搜索
            //若folder[low]是folder[high]的前缀，则folder[high]可能是folder[low]的子文件夹
            while (high < folder.length && folder[high].startsWith(folder[low])){
                //需要排除"/a/b/c"，"/a/b/ca"这样的情况
                //若folder[high]是folder[low]的子文件夹，则前缀的下一个字符一定是'/'
                if (folder[high].charAt(folder[low].length())=='/')
                    high++;
                else break;
            }
            low = high;
        }
        return ans;
    }
}
