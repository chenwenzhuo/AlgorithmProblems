package com.heythere;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solutions solutions = new Solutions();

        int[] numbers = {-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
        int[] numbers1 = {3, 0, -2, -1, 1, 2, -1, 2, 3};
        List<List<Integer>> sumIsZero = solutions.threeSum(numbers);

        for (List<Integer> list : sumIsZero) {
            System.out.println(list);
        }
    }
}
