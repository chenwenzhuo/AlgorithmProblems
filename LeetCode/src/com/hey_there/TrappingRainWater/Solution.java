package com.hey_there.TrappingRainWater;

import java.util.Stack;

public class Solution {
    //1.暴力解法。算法描述见solution_1_direct.png
    public int trap_direct(int[] height) {
        int trapCapacity = 0;//对雨水的容量
        int len = height.length;

        for (int i = 0; i < len; i++) {
            int leftHighest = 0, rightHighest = 0;//遍历数组时，第i个元素左右两侧的最大高度
            //找出左侧最大高度
            for (int j = i - 1; j >= 0; j--) {
                leftHighest = Math.max(height[j], leftHighest);
            }
            //找出右侧最大高度
            for (int j = i + 1; j < len; j++) {
                rightHighest = Math.max(height[j], rightHighest);
            }

            int curPosCapacity = Math.min(leftHighest, rightHighest) - height[i];
            if (curPosCapacity > 0) {
                trapCapacity += curPosCapacity;
            }
        }
        return trapCapacity;
    }

    //2.动态解法，以空间换时间。算法描述见solution_2_dynamic
    public int trap_dynamic(int[] height) {
        int len = height.length;
        //当数组长度小于等于2时都无法接住雨水
        if (len <= 2) {
            return 0;
        }
        int trapCapacity = 0;
        //以数组存储每个元素左侧和右侧的最大高度
        int[] leftHighest = new int[len];
        int[] rightHighest = new int[len];
        leftHighest[0] = 0;
        for (int i = 1; i < len; i++) {
            leftHighest[i] = Math.max(leftHighest[i - 1], height[i - 1]);
        }
        rightHighest[len - 1] = 0;
        for (int i = len - 2; i >= 0; i--) {
            rightHighest[i] = Math.max(rightHighest[i + 1], height[i + 1]);
        }

        for (int i = 0; i < len; i++) {
            int curPosCapacity = Math.min(leftHighest[i], rightHighest[i]) - height[i];
            if (curPosCapacity > 0) {
                trapCapacity += curPosCapacity;
            }
        }
        return trapCapacity;
    }

    //3.使用栈
    public int trap_stack(int[] height) {
        int len = height.length;
        //当数组长度小于等于2时都无法接住雨水
        if (len <= 2) {
            return 0;
        }
        int trapCapacity = 0;
        Stack<Integer> stack = new Stack<>();
        for (int cur = 0; cur < len; cur++) {
            while (!stack.empty() && height[cur] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.empty()) {
                    break;
                }
                int distance = cur - stack.peek() - 1;
                int boundedHeight = Math.min(height[cur], height[stack.peek()]) - height[top];
                trapCapacity += distance * boundedHeight;
            }
            stack.push(cur);
        }
        return trapCapacity;
    }
}
