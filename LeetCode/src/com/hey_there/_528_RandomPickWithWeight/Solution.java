package com.hey_there._528_RandomPickWithWeight;

public class Solution {
    private int[] prefixSum;//前缀和数组
    private int sumWeight;//所有权重之和

    public Solution(int[] w) {
        this.prefixSum = new int[w.length + 1];
        this.sumWeight = 0;
        //计算数组w的前缀和数组
        for (int i = 0; i < w.length; i++) {
            this.prefixSum[i + 1] = this.prefixSum[i] + w[i];
            this.sumWeight += w[i];
        }
    }

    public int pickIndex() {
        int rand = (int) (Math.random() * sumWeight);
        //二分搜索随机数rand落在哪个区间、
        int left = 0, right = prefixSum.length - 1;//两端都闭区间
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (prefixSum[mid] <= rand && rand < prefixSum[mid + 1]) {
                return mid;
            } else if (rand < prefixSum[mid]) {
                right = mid;
            } else if (rand >= prefixSum[mid + 1]) {
                left = mid + 1;
            }
        }
        return 0;
    }
}
