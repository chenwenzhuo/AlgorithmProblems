package com.hey_there.DailyProblems.April.FindTargetInMountainArray;

public class Solution {
    private MountainArray mountain;
    private int target;

    public int findInMountainArray(int target, MountainArray mountainArr) {
        this.mountain = mountainArr;
        this.target = target;

        int lenMountain = mountainArr.length();
        //查找数组中最大值的下标
        int peakIndex = findPeak(0, lenMountain - 1);
        //检查target是否是最大值
        if (mountainArr.get(peakIndex) == target) {
            return peakIndex;
        }
        //在最大值左右两侧进行二分查找
        //先找左侧
        int leftResult = binarySearchInAscendingPart(0, peakIndex - 1);
        //找到则直接返回
        if (leftResult >= 0) {
            return leftResult;
        }
        //左侧未找到则查找右侧
        return binarySearchInDescendingPart(peakIndex + 1, lenMountain - 1);
    }

    private int findPeak(int left, int right) {
        if (left > right) {
            return -1;
        }
        //左右边界相等时，当前位置即为最大值
        if (left == right) {
            return left;
        }
        int mid = (left + right) / 2;//中点
        int midVal = mountain.get(mid);//中点的值
        int nextVal = mountain.get(mid + 1);//中点的下一个位置的值
        int peakIndex;//最大值的下标
        if (midVal < nextVal) {
            peakIndex = findPeak(mid + 1, right);
        } else {
            peakIndex = findPeak(left, mid);
        }
        return peakIndex;
    }

    private int binarySearchInAscendingPart(int left, int right) {
        if (left > right) {
            return -1;
        }
        //搜索范围为1时，比较left位置的值与target
        if (left == right) {
            int curPosVal = mountain.get(left);
            if (curPosVal == target) {
                return left;//相等则返回下标
            }
            return -1;//不等则返回-1
        }

        int mid = (left + right) / 2;
        int midVal = mountain.get(mid);
        if (midVal == target) {
            return mid;
        } else if (midVal > target) {
            return binarySearchInAscendingPart(left, mid - 1);
        }
        return binarySearchInAscendingPart(mid + 1, right);
    }

    private int binarySearchInDescendingPart(int left, int right) {
        if (left > right) {
            return -1;
        }
        //搜索范围为1时，比较left位置的值与target
        if (left == right) {
            int curPosVal = mountain.get(left);
            if (curPosVal == target) {
                return left;//相等则返回下标
            }
            return -1;//不等则返回-1
        }
        int mid = (left + right) / 2;
        int midVal = mountain.get(mid);
        if (midVal == target) {
            return mid;
        } else if (midVal > target) {
            return binarySearchInDescendingPart(mid + 1, right);
        }
        return binarySearchInDescendingPart(left, mid - 1);
    }

    public static void main(String[] args) {
        MountainArrayImpl mountainArray = new MountainArrayImpl();
        Solution s = new Solution();
        int ans = s.findInMountainArray(1, mountainArray);
        System.out.println("ans = " + ans);
    }
}
