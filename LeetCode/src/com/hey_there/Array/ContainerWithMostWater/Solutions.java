package com.hey_there.Array.ContainerWithMostWater;

public class Solutions {
    /**
     * 双指针法
     * 这种方法背后的思路在于，两线段之间形成的区域总是会受到其中较短那条长度的限制。此外，两线段距离越远，得到的面积就越大。
     * 我们在由线段长度构成的数组中使用两个指针，一个放在开始，一个置于末尾。
     * 此外，我们会使用变量 maxareamaxarea 来持续存储到目前为止所获得的最大面积。
     * 在每一步中，我们会找出指针所指向的两条线段形成的区域，更新 maxareamaxarea，并将指向较短线段的指针向较长线段那端移动一步。
     *
     * 最初我们考虑由最外围两条线段构成的区域。现在，为了使面积最大化，我们需要考虑更长的两条线段之间的区域。
     * 如果我们试图将指向较长线段的指针向内侧移动，矩形区域的面积将受限于较短的线段而不会获得任何增加。
     * 但是，在同样的条件下，移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大。
     * 因为移动较短线段的指针会得到一条相对较长的线段，这可以克服由宽度减小而引起的面积减小。
     * @param height 一组线段高度
     * @return 最大面积
     */
    public int maxArea_DoubleReference(int[] height) {
        //处理非法输入
        if (null == height || height.length < 2) {
            return 0;
        }

        int numOfHeights = height.length;
        int left = 0, right = numOfHeights - 1;
        int maxArea = 0;
        while (left < right) {
            int tempMaxArea = calArea(left, height[left], right, height[right]);
            maxArea = Math.max(maxArea, tempMaxArea);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    /**
     * 暴力搜索法，尝试所有可能的组合，时间复杂度O(n^2)
     *
     * @param height 一组线段高度
     * @return 最大面积
     */
    public int maxArea_SimpleSearch(int[] height) {
        //处理非法输入
        if (null == height || height.length < 2) {
            return 0;
        }

        int numOfHeights = height.length;
        int areaMaxValue = 0;
        for (int i = 0; i < numOfHeights; i++) {
            for (int j = i + 1; j < numOfHeights; j++) {
                int tempMaxArea = calArea(i, height[i], j, height[j]);
                areaMaxValue = Math.max(areaMaxValue, tempMaxArea);
            }
        }

        return areaMaxValue;
    }

    private int calArea(int left, int leftHeight, int right, int rightHeight) {
        int waterHeight = leftHeight < rightHeight ? leftHeight : rightHeight;
        return (right - left) * waterHeight;
    }
}
