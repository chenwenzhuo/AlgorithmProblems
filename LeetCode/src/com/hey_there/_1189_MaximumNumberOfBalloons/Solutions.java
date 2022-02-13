package com.hey_there._1189_MaximumNumberOfBalloons;

public class Solutions {
    public int maxNumberOfBalloons(String text) {
        //单词balloon包含5种字母，b、a、l、o、n，用数组统计各自的数量
        int[] count = new int[5];
        char[] chText = text.toCharArray();
        for (char c : chText) {
            switch (c) {
                case 'b':
                    count[0]++;
                    break;
                case 'a':
                    count[1]++;
                    break;
                case 'l':
                    count[2]++;
                    break;
                case 'o':
                    count[3]++;
                    break;
                case 'n':
                    count[4]++;
                    break;
            }
        }
        //在balloon种，l和o出现两次，将其数量除以2
        count[2] /= 2;
        count[3] /= 2;
        //找出数组中的最小值
        int min = Integer.MAX_VALUE;
        for (int n : count) min = Math.min(min, n);
        return min;
    }
}
