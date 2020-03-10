package com.hey_there.Math.MaxPointsOnALine;


public class Main {

    public static void main(String[] args) {
        Solution_2 solution = new Solution_2();

        //int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        //int[][] points = {{1, 1}, {2, 2}, {3, 3}};
        int[][] points = {{84, 250}, {0, 0}, {1, 0}, {0, -70}, {0, -70}, {1, -1}, {21, 10}, {42, 90}, {-42, -230}};
        //int[][] points = {{0, 0}, {1, 65536}, {65536, 0}};
        System.out.println(solution.maxPoints(points));
    }
}
