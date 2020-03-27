package com.hey_there.BinarySearch.Search2DMatrix;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int matrixRows = matrix.length;
        if (matrixRows == 0) {
            return false;
        }
        int matrixColumns = matrix[0].length;
        if (matrixColumns == 0) {
            return false;
        }
        //首先查找target可能在的行
        int targetRow = -1;
        int low = 0, high = matrixRows - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (target >= matrix[mid][0] &&
                    target <= matrix[mid][matrixColumns - 1]) {
                //若target大于等于mid行的行首且小于等于行末，则找到target所在的行
                targetRow = mid;
                break;
            } else if (target > matrix[mid][matrixColumns - 1]) {
                //若target大于mid行的行末，将low后移
                low = mid + 1;
            } else if (target < matrix[mid][0]) {
                //若target小于mid行的行首，将high前移
                high = mid - 1;
            }
        }
        //若未找到所在的行，则matrix中不存在target
        if (targetRow == -1) {
            return false;
        }
        //在查找到的行内查找target
        low = 0;
        high = matrixColumns - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (target == matrix[targetRow][mid]) {
                return true;
            } else if (target > matrix[targetRow][mid]) {
                low = mid + 1;
            } else if (target < matrix[targetRow][mid]) {
                high = mid - 1;
            }
        }
        return false;
    }
}
