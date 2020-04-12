package com.hey_there.DailyProblems.April.Intersection;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    //四个点的坐标
    private int x1, y1;
    private int x2, y2;
    private int x3, y3;
    private int x4, y4;

    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        this.x1 = start1[0];
        this.y1 = start1[1];
        this.x2 = end1[0];
        this.y2 = end1[1];
        this.x3 = start2[0];
        this.y3 = start2[1];
        this.x4 = end2[0];
        this.y4 = end2[1];
        //通过检查斜率判断两直线是否平行
        //将斜率的除法式子转化为乘法，即判断(y4-y3)(x2-x1)与(y2-y1)(x4-x3)是否相等
        boolean parallelLines =
                ((y4 - y3) * (x2 - x1) == (y2 - y1) * (x4 - x3));
        double[] ans;
        if (parallelLines) {
            ans = parallelLinesIntersection();
        } else {
            ans = nonparallelLinesIntersection();
        }
        return ans;
    }

    private double[] parallelLinesIntersection() {
        //检查两线段是否在同一直线上
        //通过检查(x1,y1)(x2,y2)与(x1,y1)(x3,y3)的斜率是否相等
        boolean twoLineTheSame =
                ((y2 - y1) * (x3 - x1) == (x2 - x1) * (y3 - y1));
        List<Double> coordinate = new ArrayList<>();
        //对于在同一直线上的线段，检查是否有交点
        if (twoLineTheSame) {
            if (inside(x1, y1, x2, y2, x3, y3)) {
                update(coordinate, x3, y3);
            }
            if (inside(x1, y1, x2, y2, x4, y4)) {
                update(coordinate, x4, y4);
            }
            if (inside(x3, y3, x4, y4, x1, y1)) {
                update(coordinate, x1, y1);
            }
            if (inside(x3, y3, x4, y4, x2, y2)) {
                update(coordinate, x2, y2);
            }
        }
        //两线段有交点时，将坐标存放在数组中返回
        if (coordinate.size() > 0) {
            double[] xy = new double[2];
            xy[0] = coordinate.get(0);
            xy[1] = coordinate.get(1);
            return xy;
        }
        //没有交点时返回一个长为0的数组
        return new double[0];
    }

    private double[] nonparallelLinesIntersection() {
        double x1d = x1, y1d = y1;
        double x2d = x2, y2d = y2;
        double x3d = x3, y3d = y3;
        double x4d = x4, y4d = y4;
        //计算两直线的交点所对应的参数
        double t1 = (x3d * (y4d - y3d) + y1d * (x4d - x3d) - y3d * (x4d - x3d) - x1d * (y4d - y3d)) /
                ((x2d - x1d) * (y4d - y3d) - (x4d - x3d) * (y2d - y1d));
        double t2 = (x1d * (y2d - y1d) + y3d * (x2d - x1d) - y1d * (x2d - x1d) - x3d * (y2d - y1d)) /
                ((x4d - x3d) * (y2d - y1d) - (x2d - x1d) * (y4d - y3d));
        //若交点同时在两线段上，则两线段有交点
        if (0 <= t1 && t1 <= 1 && 0 <= t2 && t2 <= 1) {
            double[] xy = new double[2];
            xy[0] = x1d + t1 * (x2d - x1d);
            xy[1] = y1d + t1 * (y2d - y1d);
            return xy;
        }
        //交点不同时在两线段上则线段无交点
        return new double[0];
    }

    //判断点(xk,yk)是否在线段(x1,y1)(x2,y2)上
    private boolean inside(int x_1, int y_1, int x_2, int y_2, int xk, int yk) {
        // 若与 x 轴平行，只需要判断 x 的部分
        // 若与 y 轴平行，只需要判断 y 的部分
        // 若为普通线段，则都要判断
        return ((x_1 == x_2 || (Math.min(x_1, x_2) <= xk && xk <= Math.max(x_1, x_2)))
                && (y_1 == y_2 || (Math.min(y_1, y_2) <= yk && yk <= Math.max(y_1, y_2))));
    }

    private void update(List<Double> coordinate, double xk, double yk) {
        if (coordinate.size() > 0) {
            double x = coordinate.get(0);
            double y = coordinate.get(1);
            if (xk < x || (xk == x && yk < y)) {
                coordinate.remove(1);
                coordinate.remove(0);
                coordinate.add(xk);
                coordinate.add(yk);
            }
            return;
        }
        coordinate.add(xk);
        coordinate.add(yk);
    }

    public static void main(String[] args) {
        int[] start1 = {0, 3};
        int[] end1 = {0, 6};
        int[] start2 = {0, 1};
        int[] end2 = {0, 5};
        Solution solution = new Solution();
        double[] ans = solution.intersection(start1, end1, start2, end2);
        System.out.println(ans[0] + "   " + ans[1]);
    }
}
