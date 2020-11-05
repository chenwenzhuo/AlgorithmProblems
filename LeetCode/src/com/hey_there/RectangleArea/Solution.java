package com.hey_there.RectangleArea;

public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A > E) return computeArea(E, F, G, H, A, B, C, D);

        int area1 = Math.abs(A - C) * Math.abs(B - D);
        int area2 = Math.abs(E - G) * Math.abs(F - H);
        int sumArea = area1 + area2;
        /* 判断两矩形是否相交，不相交则返回两矩形面积之和
         * C <= E 表示矩形1在矩形2的左侧
         * A >= G 表示矩形1在矩形2的右侧
         * B >= H 表示矩形1在矩形2的上方
         * D <= F 表示矩形1在矩形2的下方*/
        if ((C <= E) || (A >= G) || (B >= H) || (D <= F)) return sumArea;

        //计算重叠面积
        int up = Math.min(D, H);//重叠部分的上边界
        int down = Math.max(B, F);//重叠部分的下边界
        int left = Math.max(A, E);//重叠部分的左边界
        int right = Math.min(C, G);//重叠部分的右边界
        int overlapArea = Math.abs(up - down) * Math.abs(left - right);
        return sumArea - overlapArea;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int area = solution.computeArea(-2, -2, 2, 2, 1, -3, 3, -1);
        System.out.println(area);
    }
}
