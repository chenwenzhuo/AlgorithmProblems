package com.hey_there.DailyProblems.March.RectangleOverlap;

public class Solution {
    public boolean isRectangleOverlap_my(int[] rec1, int[] rec2) {
        int x_bottomLeft = Math.min(rec1[0], rec2[0]);
        int y_bottomLeft = Math.min(rec1[1], rec2[1]);
        int x_topRight = Math.max(rec1[2], rec2[2]);
        int y_topRight = Math.max(rec1[3], rec2[3]);

        int rows = y_topRight - y_bottomLeft + 1;
        int columns = x_topRight - x_bottomLeft + 1;

        int[][] bigRec = new int[rows][columns];
        //在大矩形内将rec1覆盖的范围值加一
        int r1_topRow = y_topRight - rec1[3];//rec1在大矩形中最上方行的行号
        int r1_bottomRow = y_topRight - rec1[1];//rec1在大矩形中最下方行的行号
        int r1_leftColumn = rec1[0] - x_bottomLeft;//rec1在大矩形中最左边列的列号
        int r1_rightColumn = rec1[2] - x_bottomLeft;//rec1在大矩形中最右边列的列号
        for (int i = r1_topRow; i <= r1_bottomRow; i++) {
            for (int j = r1_leftColumn; j <= r1_rightColumn; j++) {
                bigRec[i][j]++;
            }
        }
        //在大矩形内将rec2覆盖的范围值加一
        int r2_topRow = y_topRight - rec2[3];//rec2在大矩形中最上方行的行号
        int r2_bottomRow = y_topRight - rec2[1];//rec2在大矩形中最下方行的行号
        int r2_leftColumn = rec2[0] - x_bottomLeft;//rec2在大矩形中最左边列的列号
        int r2_rightColumn = rec2[2] - x_bottomLeft;//rec2在大矩形中最右边列的列号
        for (int i = r2_topRow; i <= r2_bottomRow; i++) {
            for (int j = r2_leftColumn; j <= r2_rightColumn; j++) {
                bigRec[i][j]++;
            }
        }

        //查找大矩形内最左上角的值为2的点
        int val2_topLeft_x = -1, val2_topLeft_y = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (bigRec[i][j] == 2) {
                    val2_topLeft_x = i;
                    val2_topLeft_y = j;
                    break;
                }
            }
            if (val2_topLeft_x != -1) {
                break;
            }
        }
        //查找大矩形内最右下角的值为2的点
        int val2_bottomRight_x = -1, val2_bottomRight_y = -1;
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = columns - 1; j >= 0; j--) {
                if (bigRec[i][j] == 2) {
                    val2_bottomRight_x = i;
                    val2_bottomRight_y = j;
                    break;
                }
            }
            if (val2_bottomRight_x != -1) {
                break;
            }
        }
        //计算大矩形中值为2的区域的面积
        int val2_area = (val2_bottomRight_x - val2_topLeft_x) * (val2_bottomRight_y - val2_topLeft_y);
        return val2_area != 0;
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||   // left
                rec1[3] <= rec2[1] ||   // bottom
                rec1[0] >= rec2[2] ||   // right
                rec1[1] >= rec2[3]);    // top
    }

    public static void main(String[] args) {
        /*int[] rec1 = {7, 8, 13, 15};
        int[] rec2 = {10, 8, 12, 20};*/
        int[] rec1 = {-687153884, -854669644, -368882013, -788694078};
        int[] rec2 = {540420176, -909203694, 655002739, -577226067};

        Solution solution = new Solution();
        System.out.println(solution.isRectangleOverlap(rec1, rec2));
    }
}
