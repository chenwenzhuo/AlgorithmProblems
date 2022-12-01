package com.hey_there._1779_FindNearestPointThatHasTheSameX_or_YCoordinate;

public class Solution {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int idxOfNearest = -1;
        int nearestDistance = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            if (points[i][0] == x) {
                int mDistance = Math.abs(points[i][1] - y);
                if (mDistance < nearestDistance) {
                    idxOfNearest = i;
                    nearestDistance = mDistance;
                }
            } else if (points[i][1] == y) {
                int mDistance = Math.abs(points[i][0] - x);
                if (mDistance < nearestDistance) {
                    idxOfNearest = i;
                    nearestDistance = mDistance;
                }
            }
        }
        return idxOfNearest;
    }
}
