package com.hey_there._1769_MinimumNumberOfOperationsToMoveAllBallsToEachBox;

public class Solution {
    public int[] minOperations_1(String boxes) {
        int numBoxes = boxes.length();
        char[] boxesArr = boxes.toCharArray();
        int[] answer = new int[numBoxes];
        for (int i = 0; i < numBoxes; i++) {
            for (int j = 0; j < numBoxes; j++) {
                if (boxesArr[j] == '0' || i == j)
                    continue;
                answer[i] += Math.abs(i - j);
            }
        }
        return answer;
    }

    public int[] minOperations_2(String boxes) {
        int numBoxes = boxes.length();
        char[] boxesArr = boxes.toCharArray();
        int[] answer = new int[numBoxes];
        int[] leftBalls = new int[numBoxes];//leftBalls[i]表示下标i左边（不包含i）的球数
        int[] rightBalls = new int[numBoxes];//rightBalls[i]表示下标i右边（不包含i）的球数
        for (int i = 1; i < numBoxes; i++)//计算每个leftBalls[i]
            if (boxesArr[i - 1] == '1') leftBalls[i] = leftBalls[i - 1] + 1;
            else leftBalls[i] = leftBalls[i - 1];
        for (int i = numBoxes - 2; i >= 0; i--)//计算每个rightBalls[i]
            if (boxesArr[i + 1] == '1') rightBalls[i] = rightBalls[i + 1] + 1;
            else rightBalls[i] = rightBalls[i + 1];
        //计算answer[0]
        for (int i = 1; i < numBoxes; i++)
            if (boxesArr[i] == '1') answer[0] += i;
        //计算后面的每个answer[i]
        for (int i = 1; i < numBoxes; i++) {
            answer[i] = answer[i - 1] + leftBalls[i - 1] - rightBalls[i - 1];
            if (boxesArr[i - 1] == '1')
                answer[i] += 1;
        }
        return answer;
    }

    public int[] minOperations_3(String boxes) {
        int left = boxes.charAt(0) - '0', right = 0, operations = 0;
        int n = boxes.length();
        //计算下标0右边的球数right，和将所有球移动到下标0所需的步数operations
        for (int i = 1; i < n; i++) {
            if (boxes.charAt(i) == '1') {
                right++;
                operations += i;
            }
        }
        int[] answer = new int[n];
        answer[0] = operations;
        //left初始值为0，因为下标0左边一定没有球
        for (int i = 1; i < n; i++) {//计算后面的每个answer[i]
            operations += left - right;
            //每计算完一个answer[i]，更新left和right
            if (boxes.charAt(i) == '1') {
                left++;
                right--;
            }
            answer[i] = operations;
        }
        return answer;
    }

    public static void main(String[] args) {
        String boxes = "110";
        Solution solution = new Solution();
        int[] res = solution.minOperations_2(boxes);
        for (int n : res)
            System.out.print(n + "  ");
    }
}
