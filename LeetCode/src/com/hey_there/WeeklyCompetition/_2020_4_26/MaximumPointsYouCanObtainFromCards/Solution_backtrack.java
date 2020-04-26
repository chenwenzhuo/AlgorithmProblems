package com.hey_there.WeeklyCompetition._2020_4_26.MaximumPointsYouCanObtainFromCards;

//回溯法，超时
public class Solution_backtrack {
    private int[] cardPoints;

    public int maxScore(int[] cardPoints, int k) {
        this.cardPoints = cardPoints;
        int numCards = cardPoints.length;
        if (k > numCards) {
            return 0;
        }
        return backtrack(0, numCards - 1, k, 0);
    }

    private int backtrack(int left, int right, int k, int curPoints) {
        if (k == 0) {
            return curPoints;
        }
        //拿left位置的牌
        curPoints += cardPoints[left];
        int score_left = backtrack(left + 1, right, k - 1, curPoints);
        curPoints -= cardPoints[left];
        //拿right位置的牌
        curPoints += cardPoints[right];
        int score_right = backtrack(left, right - 1, k - 1, curPoints);
        return Math.max(score_left, score_right);
    }

    public static void main(String[] args) {
        //int[] cardPoints = {1, 2, 3, 4, 5, 6, 1};
        int[] cardPoints = {9, 7, 7, 9, 7, 7, 9};
        Solution_backtrack solutionBacktrack = new Solution_backtrack();
        int ans = solutionBacktrack.maxScore(cardPoints, 7);
        System.out.println(ans);
    }
}
