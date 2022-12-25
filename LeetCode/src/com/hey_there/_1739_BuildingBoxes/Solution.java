package com.hey_there._1739_BuildingBoxes;

public class Solution {
    public int minimumBoxes(int n) {
        int layer = 0;//盒子堆叠的层数
        int total = 0;//层数为layer时，最多放的盒子数
        while (true) {
            layer++;
            int curLayerBoxes = (layer * (layer + 1)) / 2;//当前层可放置的盒子数
            if (total + curLayerBoxes < n) {
                //n个盒子，放满layer层，还有剩余
                total += curLayerBoxes;
            } else if (total + curLayerBoxes > n) {
                //n个盒子，放不满layer层
                //放满layer-1层后多出来的只能在layer-1层基础上增加
                layer--;
                break;
            } else {
                //n个盒子，刚好放满layer层
                total += curLayerBoxes;
                break;
            }
        }
        //放满layer层后，接触地面的盒子每增加j个，堆中盒子上限可增加j*(j+1)/2个
        int j = 0;
        while (total + j * (j + 1) / 2 < n)
            j++;
        return layer * (layer + 1) / 2 + j;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int res = solution.minimumBoxes(27);
        System.out.println(res);
    }
}
