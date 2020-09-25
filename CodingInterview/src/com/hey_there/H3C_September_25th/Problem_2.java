package com.hey_there.H3C_September_25th;

public class Problem_2 {
    public long MidFactor(long val) {
        double sqrt = Math.sqrt(val);//计算平方根

        //转为long类型
        long sqrtLong = (long) sqrt;
        //若val是平方数，直接返回平方根
        if (sqrtLong * sqrtLong == val) return sqrtLong;
        //若不为平方数，从整型平方根开始从大到小查找
        long factor = sqrtLong;
        while (factor >= 1) {
            if (val % factor == 0) break;
            factor--;
        }
        return factor;
    }
}
