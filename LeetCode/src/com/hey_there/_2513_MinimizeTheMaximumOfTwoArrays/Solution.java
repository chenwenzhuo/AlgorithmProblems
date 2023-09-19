package com.hey_there._2513_MinimizeTheMaximumOfTwoArrays;

public class Solution {
    public int minimizeSet(int divisor1, int divisor2, int uniqueCnt1, int uniqueCnt2) {
        //求divisor1和divisor2的最小公倍数
        long lcm = Math.max(divisor1, divisor2);//最小公倍数
        long bigger = lcm;//二者之间的较大者
        while (lcm % divisor1 != 0 || lcm % divisor2 != 0)
            lcm += bigger;

        //总共需要total = uniqueCnt1 + uniqueCnt2个数
        //二分查找的下界为total，因为需要total个不同的数，最大值不可能比total更小
        long low = uniqueCnt1 + uniqueCnt2, high = Integer.MAX_VALUE;
        while (low < high) {
            long mid = (low + high) / 2L;
            //检查在区间[1,mid]中能否取出uniqueCnt1+uniqueCnt2个符合要求的数
            /* 区间中divisor1的倍数不能被选入arr1，但可以被选入arr2
             * divisor2的倍数不能被选入arr2，但可以被选入arr1
             * lcm的倍数既不能被选入arr1，也不能被选入arr2*/
            long n1 = mid / divisor1;//区间中divisor1的倍数的数量
            long n2 = mid / divisor2;//区间中divisor2的倍数的数量
            long n3 = mid / lcm;//区间中lcm的倍数的数量

            /* n1与n2的交集为n3，在区间[1,mid]中：
             * 有n2-n3个数是arr1独享的，有n1-n3个数是arr2独享的，
             * 有 mid-n1-n2+n3 个数是arr1和arr2共享的*/
            long exclusive1 = n2 - n3, exclusive2 = n1 - n3;
            //若独享的数不够填满数组，则需要从共享的数中选取
            //共需要取shared1+shared2个共享数字
            long shared1 = 0, shared2 = 0;
            if (exclusive1 < uniqueCnt1)
                shared1 = uniqueCnt1 - exclusive1;
            if (exclusive2 < uniqueCnt2)
                shared2 = uniqueCnt2 - exclusive2;

            //共享的数够用，则mid可作为最大值，尝试减小上界
            if (shared1 + shared2 <= mid - n1 - n2 + n3)
                high = mid;
            else low = mid + 1;//否则增大下界
        }
        return (int) low;
    }
}
