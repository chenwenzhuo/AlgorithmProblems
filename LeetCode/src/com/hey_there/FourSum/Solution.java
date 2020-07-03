package com.hey_there.FourSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    //O(n^3)的解法
    //通过在外面两层循环中计算当前能产生的最大最小和，避免不必要的计算
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);//先对数组进行排序
        int len = nums.length;//获取数组长度

        //存放结果的list容器
        List<List<Integer>> results = new ArrayList<>();
        //计算过程中的暂存变量
        int tempSum;

        //以四个下标分别表示数组中不同位置
        int a, b, c, d;
        for (a = 0; a <= len - 4; a++) {
            //当下标a表示的值与前一个值相等时，产生重复解，故继续下一次循环
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }

            //计算当前a值能产生的最小和
            //若最小和大于target，后面只能得到更大的和，故直接终止循环
            int minSum_withCurA = nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3];
            if (minSum_withCurA > target) {
                break;
            }
            //计算当前a值能产生的最大和
            //若最大和小于target，则继续下一次循环
            int maxSum_withCurA = nums[a] + nums[len - 1] + nums[len - 2] + nums[len - 3];
            if (maxSum_withCurA < target) {
                continue;
            }

            for (b = a + 1; b <= len - 3; b++) {
                //当下标b表示的值与前一个值相等时，产生重复解，故继续下一次循环
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }

                //为c、d赋值
                c = b + 1;
                d = len - 1;

                //计算当前a、b值能产生的最小最大和
                int minSum_withCurAB = nums[a] + nums[b] + nums[b + 1] + nums[b + 2];
                if (minSum_withCurAB > target) {
                    break;
                }
                int maxSum_withCurAB = nums[a] + nums[b] + nums[len - 1] + nums[len - 2];
                if (maxSum_withCurAB < target) {
                    continue;
                }

                //最内层循环使用双指针法
                while (c < d) {
                    tempSum = nums[a] + nums[b] + nums[c] + nums[d];//计算当前四个下标表示的值之和

                    //若当前四数和等于target，保存至results
                    if (tempSum == target) {
                        List<Integer> oneResult = new ArrayList<>();
                        oneResult.add(nums[a]);
                        oneResult.add(nums[b]);
                        oneResult.add(nums[c]);
                        oneResult.add(nums[d]);
                        results.add(oneResult);

                        //找出一个解后，c、d的值都要改变
                        c++;//将c右移
                        while (c < d && nums[c] == nums[c - 1]) {
                            c++;//若c右移后的值与前一个值相等，则继续右移
                        }
                        d--;//将d左移
                        while (d > c && nums[d] == nums[d + 1]) {
                            d--;//若d左移后的值与前一个值相等，则继续左移
                        }
                        continue;
                    }

                    //若当前四数和小于target，将c右移
                    if (tempSum < target) {
                        c++;
                    } else {//若当前四数和大于target，将d左移
                        d--;
                    }
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        //int[] nums = {1, 0, -1, 0, -2, 2,};
        int[] nums = {0, 0, 0, 0};

        Solution solution = new Solution();
        List<List<Integer>> results = solution.fourSum(nums, 1);
        System.out.println(results);
    }
}
