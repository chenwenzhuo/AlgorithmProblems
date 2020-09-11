package com.hey_there.NetEasy_September_11th;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int start = Integer.parseInt(line[0]);
        int end = Integer.parseInt(line[1]);
        sc.close();
        line = null;

        if (start == end) {
            System.out.println(0);
            System.out.println(1);
            System.out.println(start);
            return;
        }

        int min_time = start;
        int city_num = 0;
        ArrayList<Integer> cities = new ArrayList<>();
        for (int i = 1; i < 2 * start; i++) {
            int tmp_time;
            ArrayList<Integer> tmp_cities = new ArrayList<>();
            if (i == start) {
                tmp_time = 0;
                tmp_cities.add(i);
            } else {
                tmp_time = Math.abs(start - i);
                tmp_cities.add(start);
                tmp_cities.add(i);
            }

            if (i != end) {
                if (end % i == 0) {
                    tmp_cities.add(end);
                } else {
                    if (end / i != 0) {
                        if (end - (end / i) * i <= (end / i + 1) * i - end) {
                            tmp_time += end - (end / i) * i;
                            tmp_cities.add((end / i) * i);
                            tmp_cities.add(end);
                        } else {
                            tmp_time += (end / i + 1) * i - end;
                            tmp_cities.add((end / i + 1) * i);
                            tmp_cities.add(end);
                        }
                    } else {
                        tmp_time += (end / i + 1) * i - end;
                        tmp_cities.add((end / i + 1) * i);
                        tmp_cities.add(end);
                    }
                }
            }

            if (tmp_time < min_time) {
                min_time = tmp_time;
                city_num = tmp_cities.size();
                cities = tmp_cities;
            }
        }
        System.out.println(min_time);
        System.out.println(city_num);
        for (int n : cities) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
}
