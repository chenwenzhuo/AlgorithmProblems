package com.hey_there;

import java.util.*;

public class Solution {
    private int param_a, param_b, param_c;

    public int maxPoints(int[][] points) {
        int rows = points.length;//二维数组的行数，即点数

        //用HashMap存储直线和直线上的点数
        //用List存储 ax+by+c=0 形式的直线方程中的a，b，c参数
        Map<List<Integer>, Integer> line_and_pointsCount = new HashMap<>();

        for (int index_point1 = 0; index_point1 < rows; index_point1++) {
            //第一个点的横纵坐标
            int x_point1 = points[index_point1][0];
            int y_point1 = points[index_point1][1];

            for (int index_point2 = index_point1 + 1; index_point2 < rows; index_point2++) {
                //第一个点的横纵坐标
                int x_point2 = points[index_point2][0];
                int y_point2 = points[index_point2][1];

                //计算 ax+by+c=0 形式的直线方程中的a，b，c参数
                param_a = y_point1 - y_point2;
                param_b = x_point2 - x_point1;
                param_c = y_point1 * (x_point1 - x_point2) - x_point1 * (y_point1 - y_point2);

                //得到所有参数后，需要化简直线方程，使a，b，c互质
                simplifyParams();

                //将a，b，c参数存入List
                List<Integer> lineParams = new ArrayList<>();
                lineParams.add(param_a);
                lineParams.add(param_b);
                lineParams.add(param_c);

                //检查当前直线是否已出现过
                if (!line_and_pointsCount.containsKey(lineParams)) {
                    //若当前直线未出现过，则将其加入map，点数设为2
                    line_and_pointsCount.put(lineParams, 2);
                } else {
                    //若map中已存在当前直线，则将其value值加1
                    int pointsCount = line_and_pointsCount.get(lineParams);
                    line_and_pointsCount.put(lineParams, pointsCount + 1);
                }
            }
        }

        //遍历map，获得最大的value值
        Iterator<Map.Entry<List<Integer>, Integer>> iterator = line_and_pointsCount.entrySet().iterator();
        int maxValue = 0;
        while (iterator.hasNext()) {
            Map.Entry<List<Integer>, Integer> entry = iterator.next();

            System.out.println("直线参数：" + entry.getKey() + "   点数：" + entry.getValue());

            maxValue = Math.max(maxValue, entry.getValue());
        }

        return maxValue;
    }

    private void simplifyParams() {
        //计算三个参数的绝对值
        int abs_param_a = Math.abs(param_a);
        int abs_param_b = Math.abs(param_b);
        int abs_param_c = Math.abs(param_c);

        //最小且非零的绝对值
        int min_nonzero_absParam = -1;
        if (abs_param_a != 0 && min_nonzero_absParam < abs_param_a) {
            min_nonzero_absParam = abs_param_a;
        }
        if (abs_param_b != 0 && min_nonzero_absParam < abs_param_b) {
            min_nonzero_absParam = abs_param_b;
        }
        if (abs_param_c != 0 && min_nonzero_absParam < abs_param_c) {
            min_nonzero_absParam = abs_param_c;
        }

        //当min_nonzero_absParam为 1 时，表示a,b,c,中有一个为 1 或 -1，此时不需要化简
        if (min_nonzero_absParam == 1) {
            return;
        }

        int commonDivisor = 2;//公约数
        while (commonDivisor <= min_nonzero_absParam) {
            //若commonDivisor的当前值是公约数，则除以它
            if (param_a % commonDivisor == 0 &&
                    param_b % commonDivisor == 0 &&
                    param_c % commonDivisor == 0) {
                param_a /= commonDivisor;
                param_b /= commonDivisor;
                param_c /= commonDivisor;
                min_nonzero_absParam /= commonDivisor;

                //公约数的值重新设为2
                commonDivisor = 2;
            }else {
                //不是公约数则自增1
                commonDivisor++;
            }
        }
    }

    private int countZeros() {
        int[] params = {param_a, param_b, param_c};
        int count = 0;
        for (int param : params) {
            if (param == 0) {
                count++;
            }
        }
        return count;
    }
}
