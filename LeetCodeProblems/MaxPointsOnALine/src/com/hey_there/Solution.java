package com.hey_there;

import java.util.*;

public class Solution {
    private long param_a, param_b, param_c;

    public int maxPoints(int[][] points) {
        int rows = points.length;//二维数组的行数，即点数

        if (rows == 1) {
            return 1;
        }

        //用HashMap存储直线和直线上的点数
        //用List存储 ax+by+c=0 形式的直线方程中的a，b，c参数
        Map<List<Long>, Integer> line_and_pointsCount = new HashMap<>();

        for (int index_point1 = 0; index_point1 < rows; index_point1++) {
            //第一个点的横纵坐标
            int x_point1 = points[index_point1][0];
            int y_point1 = points[index_point1][1];

            for (int index_point2 = index_point1 + 1; index_point2 < rows; index_point2++) {
                //第二个点的横纵坐标
                int x_point2 = points[index_point2][0];
                int y_point2 = points[index_point2][1];

                /*//计算 ax+by+c=0 形式的直线方程中的a，b，c参数
                param_a = y_point1 - y_point2;
                param_b = x_point2 - x_point1;
                param_c = y_point1 * (x_point1 - x_point2) - x_point1 * (y_point1 - y_point2);

                //得到所有参数后，需要化简直线方程，使a，b，c互质
                simplifyParams();

                //将a，b，c参数存入List
                List<Long> lineParams = new ArrayList<>();
                lineParams.add(param_a);
                lineParams.add(param_b);
                lineParams.add(param_c);

                //检查此次计算出的直线是否已存储于map中
                if (line_and_pointsCount.containsKey(lineParams)) {
                    //若此次计算出的直线已存储于map中，跳过后续操作
                    continue;
                }
                //若此次计算出的直线不存在于map中，检查point2之后的点是否在此直线上
                int pointsOnCurrentLine = 2;//当前直线上的点数，初值为2
                for (int index_subsequent_points = index_point2 + 1;
                     index_subsequent_points < rows; index_subsequent_points++) {
                    int x_subs = points[index_subsequent_points][0];
                    int y_subs = points[index_subsequent_points][1];

                    if (0 == param_a * x_subs + param_b * y_subs + param_c) {
                        //若检查到在当前直线上的点，点数加一
                        pointsOnCurrentLine++;
                    }
                }

                //将直线参数与点数存入map中
                line_and_pointsCount.put(lineParams, pointsOnCurrentLine);*/
            }
        }

        /*//遍历map，获得最大的value值
        Iterator<Map.Entry<List<Long>, Integer>> iterator = line_and_pointsCount.entrySet().iterator();
        int maxValue = 0;
        while (iterator.hasNext()) {
            Map.Entry<List<Long>, Integer> entry = iterator.next();

            System.out.println("直线参数：" + entry.getKey() + "   点数：" + entry.getValue());

            maxValue = Math.max(maxValue, entry.getValue());
        }*/

        return 0;
    }

    private void simplifyParams() {
        //计算三个参数的绝对值
        long abs_param_a = Math.abs(param_a);
        long abs_param_b = Math.abs(param_b);
        long abs_param_c = Math.abs(param_c);

        //最小且非零的绝对值
        long min_nonzero_absParam = -1;
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
            } else {
                //不是公约数则自增1
                commonDivisor++;
            }
        }
    }
}
