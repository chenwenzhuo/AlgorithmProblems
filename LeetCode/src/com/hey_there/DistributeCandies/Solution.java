package com.hey_there.DistributeCandies;

public class Solution {
    public int[] distributeCandies(int candies, int num_people) {
        int[] results = new int[num_people];//结果数组

        //计算能完整分完几轮
        int rounds = -1;//能完整分完的轮数
        int tempTotal = 0;
        while (tempTotal < candies) {
            rounds++;
            tempTotal += rounds * num_people * num_people + (1 + num_people) * num_people / 2;
        }

        //将完整的 rounds 轮中每人所得填入数组
        tempTotal = 0;//此处tempTotal用来计数已经分了多少
        for (int r = 0; r < rounds; r++) {
            for (int i = 0; i < num_people; i++) {
                int shouldGive = r * num_people + (i + 1);
                results[i] += shouldGive;
                tempTotal += shouldGive;
            }
        }
        candies -= tempTotal;

        int index = 0;
        while (true) {
            //此次应该给出去的数量
            int shouldGive = rounds * num_people + (index + 1);
            if (shouldGive < candies) {
                //若剩余量大于此次应该给的量
                results[index] += shouldGive;//分给当前位置shouldGive个
                candies -= shouldGive;//更新剩余量
                index++;
            } else {
                //若剩余量不足以完成此次分配
                results[index] += candies;//将剩余的全部给当前位置
                break;
            }
        }

        return results;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] results = solution.distributeCandies(7, 4);
        for (int r : results) {
            System.out.print(r + "  ");
        }
        System.out.println();
    }
}
