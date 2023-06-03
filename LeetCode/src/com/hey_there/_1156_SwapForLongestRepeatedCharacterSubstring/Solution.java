package com.hey_there._1156_SwapForLongestRepeatedCharacterSubstring;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int maxRepOpt1(String text) {
        char[] chs = text.toCharArray();
        //统计text中每种字符的出现次数
        HashMap<Character, Integer> ch2cnt = new HashMap<>();
        for (char c : chs)
            ch2cnt.put(c, ch2cnt.getOrDefault(c, 0) + 1);

        int ans = 1;//单字符重复的字符串长度至少为1
        //对于每种字符，计算由此字符构成的单字符的字符串长度
        for (Map.Entry<Character, Integer> entry : ch2cnt.entrySet()) {
            char curCh = entry.getKey();
            int curChCnt = entry.getValue();
            //双指针遍历chs数组，检查区间[left,right]中字符curCh的单字符重复串长度
            for (int left = 0; left < chs.length; left++) {
                if (chs[left] != curCh) continue;//不是当前需要的字符串，跳过
                int tempCnt = 0;//区间[left,right]中字符curCh的数量
                int otherCnt = 0;//区间[left,right]中其他字符的数量
                for (int right = left; right < chs.length; right++) {
                    if (chs[right] == curCh) tempCnt++;
                    else otherCnt++;
                    //若区间中其他字符数量超过1，无法通过一次交换得到单字符重复串，退出循环
                    //区间中有其他字符，但字符curCh已经用完，无法交换，退出循环
                    if (otherCnt > 1 || (otherCnt == 1 && tempCnt == curChCnt))
                        break;
                    ans = Math.max(ans, right - left + 1);//更新ans
                }
                //对于连续相同的字符chs[left]得到的答案不会更大，直接跳过
                //只跳过连续3个及以上的，若连续相同的只有2个则不跳过
                while (left < chs.length - 1 && chs[left] == chs[left + 1])
                    left++;
            }
        }
        return ans;
    }
}
