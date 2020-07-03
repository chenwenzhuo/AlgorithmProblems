package com.hey_there.ShowOrders;

import java.util.*;

public class Solution {
    public List<List<String>> displayTable(List<List<String>> orders) {
        LinkedList<String> header = new LinkedList<>();//展示表的标题行
        /* 外层map储存桌号到桌上菜品的映射，
         * 内层map储存菜名到此菜的数量的映射*/
        HashMap<Integer, HashMap<String, Integer>> tables = new HashMap<>();

        //遍历所有订单
        for (List<String> order : orders) {
            String tableNum = order.get(1);//桌号
            String dishName = order.get(2);//菜名
            int tableNum_int = Integer.parseInt(tableNum);//将桌号转换为整型

            //找到未出现的菜名，将其加入标题栏
            if (!header.contains(dishName)) {
                header.add(dishName);
            }

            if (tables.containsKey(tableNum_int)) {
                //获取当前桌子上所有菜品
                HashMap<String, Integer> curTable = tables.get(tableNum_int);
                if (curTable.containsKey(dishName)) {//当前桌子上已有此菜品，则将其数量加1
                    curTable.put(dishName, curTable.get(dishName) + 1);
                } else {//当前桌子上没有此菜品，则将其加入当前桌，数量为1
                    curTable.put(dishName, 1);
                }
            } else {
                HashMap<String, Integer> newTable = new HashMap<>();
                newTable.put(dishName, 1);
                tables.put(tableNum_int, newTable);
            }
        }

        //遍历完成后，进行后续处理
        List<List<String>> ans = new ArrayList<>();//结果集合
        //将标题行排序并加入结果集合
        Collections.sort(header);
        ans.add(header);
        //获取外层map的keySet，即桌号，将其转化为数组并排序
        Object[] tableNum_arr = tables.keySet().toArray();
        Arrays.sort(tableNum_arr);
        //按桌号获取所有菜品
        for (Object obj : tableNum_arr) {
            int tableNum = (Integer) obj;
            HashMap<String, Integer> dishesOnTable = tables.get(tableNum);

            List<String> curTable = new ArrayList<>();
            curTable.add(String.valueOf(tableNum));
            for (String dishName : header) {
                if (dishesOnTable.containsKey(dishName)) {
                    curTable.add(String.valueOf(dishesOnTable.get(dishName)));
                } else {
                    curTable.add("0");
                }
            }
            ans.add(curTable);
        }
        //在标题行的行首加入字符串"Table"
        header.addFirst("Table");
        return ans;
    }

    public static void main(String[] args) {
        List<String> table1 = new ArrayList<>();
        table1.add("Mike");
        table1.add("3");
        table1.add("dish_a");
        List<String> table2 = new ArrayList<>();
        table2.add("John");
        table2.add("10");
        table2.add("dish_b");
        List<List<String>> orders = new ArrayList<>();
        orders.add(table1);
        orders.add(table2);

        Solution solution = new Solution();
        System.out.println(solution.displayTable(orders));
    }
}
