package com.hey_there.MaxPointsOnALine;

import java.util.*;

public class Solution_2 {
    private long param_a, param_b, param_c;

    public int maxPoints(int[][] points) {
        int rows = points.length;//二维数组的行数，即点数

        if (rows == 0) {
            return 0;
        }
        if (rows == 1) {
            return 1;
        }

        //用HashMap存储直线和直线上的点数
        //用List存储 ax+by+c=0 形式的直线方程中的a，b，c参数
        Map<List<Long>, Integer> line_and_pointsCount = new HashMap<>();

        //用第一个和第二个点先计算出一条直线，存入map中
        param_a = points[0][1] - points[1][1];
        param_b = points[1][0] - points[0][0];
        param_c = points[0][1] * (points[0][0] - points[1][0]) - points[0][0] * (points[0][1] - points[1][1]);
        simplifyParams();//化简参数

        List<Long> firstLine = new ArrayList<>();
        firstLine.add(param_a);
        firstLine.add(param_b);
        firstLine.add(param_c);
        line_and_pointsCount.put(firstLine, 2);

        //从第三个点开始，检查每个点是否在map中的某条直线上
        for (int pointIndex = 2; pointIndex < rows; pointIndex++) {
            //点的横纵坐标
            int x_currentPoint = points[pointIndex][0];
            int y_currentPoint = points[pointIndex][1];

            //遍历map
            Iterator<Map.Entry<List<Long>, Integer>> iterator = line_and_pointsCount.entrySet().iterator();
            boolean point_on_existing_line = false;
            while (iterator.hasNext()) {
                Map.Entry<List<Long>, Integer> entry = iterator.next();

                List<Long> params = entry.getKey();
                if (params.get(0) * x_currentPoint + params.get(1) * y_currentPoint + params.get(2) == 0) {
                    //若当前点在map中的某条直线上，更新该条直线的点数
                    int value = entry.getValue();
                    line_and_pointsCount.put(params, entry.getValue() + 1);
                    value = entry.getValue();
                    point_on_existing_line = true;
                }
            }

            if (point_on_existing_line) {
                //继续检查下一个点
                continue;
            }

            //若当前点不在map中的直线上，计算当前点和它之前的所有点之间的直线存入map
            for (int prev_pointIndex = pointIndex - 1; prev_pointIndex >= 0; prev_pointIndex--) {
                int x_prev = points[prev_pointIndex][0];
                int y_prev = points[prev_pointIndex][1];

                param_a = y_currentPoint - y_prev;
                param_b = x_prev - x_currentPoint;
                param_c = y_currentPoint * param_b * (-1) - x_currentPoint * (param_a);
                simplifyParams();

                List<Long> line = new ArrayList<>();
                line.add(param_a);
                line.add(param_b);
                line.add(param_c);
                line_and_pointsCount.put(line, 2);
            }
        }

        //遍历map，获得最大的value值
        Iterator<Map.Entry<List<Long>, Integer>> iterator = line_and_pointsCount.entrySet().iterator();
        int maxValue = 0;
        while (iterator.hasNext()) {
            Map.Entry<List<Long>, Integer> entry = iterator.next();

            System.out.println("参数：" + entry.getKey() + "  点数：" + entry.getValue());

            maxValue = Math.max(maxValue, entry.getValue());
        }

        return maxValue;
    }

    private void simplifyParams() {
        //处理符号，让a,b中第一个不为0的数为正（a,b不会同时为0）
        if (param_a < 0) {
            param_a = param_a * (-1);
            param_b = param_b * (-1);
            param_c = param_c * (-1);
        } else if (param_a == 0) {
            if (param_b < 0) {
                param_b = param_b * (-1);
                param_c = param_c * (-1);
            }
        }

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
