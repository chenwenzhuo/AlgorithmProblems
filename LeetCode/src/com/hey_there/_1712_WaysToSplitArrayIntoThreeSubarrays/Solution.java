package com.hey_there._1712_WaysToSplitArrayIntoThreeSubarrays;

public class Solution {
    public int waysToSplit(int[] nums) {
        int len = nums.length;
        //计算数组前缀和
        int[] preSum = new int[len];
        preSum[0] = nums[0];
        for (int i = 1; i < len; i++)
            preSum[i] = preSum[i - 1] + nums[i];
        //检查所有可能的分割方式
        //两个分割点i和j将数组分为[0,i]、[i+1,j]、[j+1,len-1]三部分
        int count = 0;
        for (int i = 0; i <= len - 2; i++) {
            int sum1 = preSum[i];//第一个子数组之和
            /* 要满足sum1<=sum2<=sum3，则2*sum1<=sum2+sum3，
             * 故当2*sum1大于sum2+sum3时，外层循环可提前结束*/
            if (sum1 * 2 > preSum[len - 1] - sum1)
                break;

            //二分查找，寻找使sum1<=sum2的最小的j
            int jSt = i + 1, jEd = len - 2;
            if (jSt > jEd) break;
            while (jSt < jEd) {
                int jMid = (jSt + jEd) / 2;
                int sum2 = preSum[jMid] - sum1;
                //sum1<=sum2，当前jMid可能为最小的j，将jEd赋值为jMid，继续寻找
                if (sum1 <= sum2) {
                    jEd = jMid;
                } else {//sum1>sum2，需要寻找使sum2更大的j
                    jSt = jMid + 1;
                }
            }
            //上方二分查找完成，获得j的最小值
            int jMin = jSt;
            //j为最小值时，sum3为最大值，若sum2>sum3，则无法完成分割
            if (preSum[jMin] - sum1 > preSum[len - 1] - preSum[jMin])
                continue;

            //二分查找，寻找使sum2<=sum3的最大的j
            jSt = jMin;//jMin可能就是jMax，故二分查找起点定为jMin
            jEd = len - 2;
            while (jSt < jEd) {
                int jMid = (jSt + jEd + 1) / 2;
                int sum2 = preSum[jMid] - sum1;
                int sum3 = preSum[len - 1] - preSum[jMid];
                //sum2<=sum3，当前jMid可能为最大的j，将jSt赋值为jMid，继续寻找
                if (sum2 <= sum3) {
                    jSt = jMid;
                } else {//sum2>sum3，需要寻找使sum2更小的j
                    jEd = jMid - 1;
                }
            }
            //上方二分查找完成，获得j的最大值
            int jMax = jSt;

            //计算有多少个j满足要求
            count += (jMax - jMin + 1);
            count %= 1000000007;
        }
        return count;
    }

    public static void main(String[] args) {
        //int[] nums = {1, 2, 2, 2, 5, 0};
        int[] nums = {8892, 2631, 7212, 1188, 6580, 1690, 5950, 7425,
                8787, 4361, 9849, 4063, 9496, 9140, 9986, 1058, 2734,
                6961, 8855, 2567, 7683, 4770, 40, 850, 72, 2285, 9328,
                6794, 8632, 9163, 3928, 6962, 6545, 6920, 926, 8885,
                1570, 4454, 6876, 7447, 8264, 3123, 2980, 7276, 470,
                8736, 3153, 3924, 3129, 7136, 1739, 1354, 661, 1309,
                6231, 9890, 58, 4623, 3555, 3100, 3437};
        Solution solution = new Solution();
        int res = solution.waysToSplit(nums);
        System.out.println(res);
    }
}
