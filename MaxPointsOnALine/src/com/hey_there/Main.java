package com.hey_there;


public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        //int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        int[][] points = {{1, 1}, {2, 2}, {3, 3}};
        System.out.println(solution.maxPoints(points));
    }
}
