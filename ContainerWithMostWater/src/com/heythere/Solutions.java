package com.heythere;

public class Solutions {
    public int maxArea(int[] height) {
        //处理非法输入
        if (null == height || height.length < 2) {
            return 0;
        }

        int numOfArgs = height.length;
        if (2 == height.length) {
            if (height[0] >= 0 && height[1] >= 0) {
                return height[0] < height[1] ? height[0] : height[1];
            } else {
                return 0;
            }
        }

        int areaMaxValue = 0;
        int tempMaxValue;
        boolean moveLeftOrRight = true;
        int left = 0, right = numOfArgs - 1;
        while (left < right) {
            tempMaxValue = calArea(left, height[left], right, height[right]);
            if (tempMaxValue > areaMaxValue) {
                areaMaxValue = tempMaxValue;
            }

            if (moveLeftOrRight) {
                left++;
            } else {
                right--;
            }
            moveLeftOrRight = !moveLeftOrRight;
        }

        return areaMaxValue;
    }

    private int calArea(int left, int leftHeight, int right, int rightHeight) {
        int waterHeight = leftHeight < rightHeight ? leftHeight : rightHeight;
        return (right - left) * waterHeight;
    }
}
