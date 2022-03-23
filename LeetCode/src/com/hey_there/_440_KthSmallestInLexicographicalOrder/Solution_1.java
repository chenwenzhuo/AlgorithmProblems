package com.hey_there._440_KthSmallestInLexicographicalOrder;

import java.util.PriorityQueue;

public class Solution_1 {
    //比较字符串，时间复杂度不满足要求
    public int findKthNumber(int n, int k) {
        PriorityQueue<String> pq = new PriorityQueue<>((s1, s2) -> {
            char[] chs1 = s1.toCharArray();
            char[] chs2 = s2.toCharArray();
            int idx1 = 0, idx2 = 0;
            //遍历两个数组
            while (idx1 < chs1.length && idx2 < chs2.length) {
                if (chs1[idx1] != chs2[idx2]) break;//找到不相等的位，退出循环
                idx1++;
                idx2++;
            }
            /* 若两个下标之一等于数组长度，说明s1，s2长度不同
             * 且较短者与较长者的前若干位完全相同。
             * 此时应将较短者排在前面。*/
            if (idx1 >= chs1.length || idx2 >= chs2.length) {
                if (chs1.length < chs2.length) return -1;
                return 1;
            }
            //若两下标均小于数组长度，则将当前不等的字符比较，判断大小
            return chs1[idx1] - chs2[idx2];
        });
        //将1到n都加入优先队列
        for (int i = 1; i <= n; i++)
            pq.add(String.valueOf(i));
        //移除前n-1个
        while (pq.size() > n - k + 1)
            pq.poll();
        return Integer.parseInt(pq.poll());//此时队列最前方的即为所求的值
    }

    public static void main(String[] args) {
        int n = 13, k = 2;
        Solution_1 solution = new Solution_1();
        int res = solution.findKthNumber(n, k);
    }
}
