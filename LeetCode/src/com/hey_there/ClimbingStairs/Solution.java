package com.hey_there.ClimbingStairs;

public class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        //到达当前台阶的方式数
        int waysToCurStair = 0;
        //到达前一级台阶的方式数
        int prevOne = 2;
        //到达前两级台阶的方式数
        int prevTwo = 1;
        for (int i = 2; i < n; i++) {
            waysToCurStair = prevOne + prevTwo;
            prevTwo = prevOne;
            prevOne = waysToCurStair;
        }
        return waysToCurStair;
    }

    public int climbStairs_1(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        //到达前一级台阶的方式数
        int prevOne = 2;
        //到达前两级台阶的方式数
        int prevTwo = 1;
        for (int i = 2; i < n; i++) {
            prevOne += prevTwo;
            prevTwo = prevOne - prevTwo;
        }
        return prevOne;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int ans = solution.climbStairs(4);
        System.out.println(ans);
    }
}
