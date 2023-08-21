package com.hey_there._2337_MovePiecesToObtainString;

public class Solution {
    /* 若start可以移动得到target，则需要满足：
     * 1.两字符串中下划线数量相同。
     * 2.两字符串中L和R的相对顺序相同。
     * 3.每个L在start中的下标大于等于在target中下标，每个R在start中的下标小于等于在target中下标*/
    public boolean canChange(String start, String target) {
        int n = start.length();
        //两字符串转为字符数组
        char[] c_start = start.toCharArray();
        char[] c_target = target.toCharArray();
        int cntUnderline_s = 0, cntUnderline_t = 0;//下划线计数
        int index1 = 0, index2 = 0;//遍历两字符串的下标指针
        while (index1 < n || index2 < n) {
            //移动指针，寻找下一个非下划线字符
            while (index1 < n) {
                if (c_start[index1] == '_')
                    cntUnderline_s++;
                else break;
                index1++;
            }
            while (index2 < n) {
                if (c_target[index2] == '_')
                    cntUnderline_t++;
                else break;
                index2++;
            }
            if (index1 < n && index2 < n) {
                //两下标指向的字符不同，返回false
                if (c_start[index1] != c_target[index2])
                    return false;
                //当前字符相同，若为L，则index1必须大于等于index2，
                //若为R，则index1必须小于等于index2
                if ((c_start[index1] == 'L' && index1 < index2) ||
                        (c_start[index1] == 'R' && index1 > index2))
                    return false;
            }
            //index1、index2指向当前非下划线字符，将二者后移一位
            index1++;
            index2++;
        }
        //下划线数量必须相同
        return cntUnderline_s == cntUnderline_t;
    }
}
