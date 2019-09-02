package com.heythere;

public class Main {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        Solution solution = new Solution();
        int[] twoNums = solution.twoSumMySolution(nums, 20);
        if (null != twoNums) {
            System.out.println(twoNums[0] + "   " + twoNums[1]);
        } else {
            System.out.println("Not found");
        }
    }
}
