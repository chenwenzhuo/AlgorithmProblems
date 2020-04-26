package com.hey_there.WeeklyCompetition._2020_4_19.MinimumNumberOfFrogsCroaking;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    //O(n^2)复杂度，超时
    public int minNumberOfFrogs_timeOut(String croakOfFrogs) {
        char[] croakArr = croakOfFrogs.toCharArray();
        //检查长度，长度不为5的倍数则直接排除
        int lenCroaks = croakArr.length;
        if (lenCroaks % 5 != 0) {
            return -1;
        }

        //最多可能有 lenCroaks/5 只青蛙，给list一个初始容量避免计算过程中扩容的开销
        List<Integer> frogs = new ArrayList<>(lenCroaks / 5);
        char[] ch = {'c', 'r', 'o', 'a', 'k'};
        for (int i = 0; i < lenCroaks; i++) {
            int next = 0;
            switch (croakArr[i]) {
                case 'c':
                    next = 1;
                    break;
                case 'r':
                    next = 2;
                    break;
                case 'o':
                    next = 3;
                    break;
                case 'a':
                    next = 4;
                    break;
                case 'k':
                    next = 5;
                    break;
            }
            int curNumOfFrogs = frogs.size();
            for (int j = 0; j < curNumOfFrogs; j++) {
                if (frogs.get(j) == next - 1) {
                    if (next == 5) {
                        frogs.set(j, 0);
                    } else {
                        frogs.set(j, next);
                    }
                    next = -1;
                    break;
                }
            }
            //在上方若未找到位置将next插入
            if (1 <= next && next <= 5) {
                if (next == 1) {//next为1表示当前字符是'c'
                    //需要增加一只青蛙
                    frogs.add(1);
                } else {
                    //'c'以外的字符未能成功插入则表示croakOfFrogs不是由有效的croak构成
                    //直接返回-1
                    return -1;
                }
            }
        }
        return frogs.size();
    }

    public int minNumberOfFrogs(String croakOfFrogs) {
        int c, r, o, a, k;
        c = r = o = a = k = 0;
        char[] chars = croakOfFrogs.toCharArray();
        int ans = 0;
        for (char ch : chars) {
            if (ch == 'c') {
                if (k > 0) {
                    k--;
                } else {
                    ans++;
                }
                c++;
            } else if (ch == 'r') {
                c--;
                r++;
            } else if (ch == 'o') {
                r--;
                o++;
            } else if (ch == 'a') {
                o--;
                a++;
            } else if (ch == 'k') {
                a--;
                k++;
            }
            if (c < 0 || r < 0 || o < 0 || a < 0) {
                break;
            }
        }
        if (c != 0 || r != 0 || o != 0 || a != 0) {
            return -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minNumberOfFrogs("croakcroak"));
    }
}
