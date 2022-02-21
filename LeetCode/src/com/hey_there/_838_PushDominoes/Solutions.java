package com.hey_there._838_PushDominoes;

public class Solutions {
    public String pushDominoes(String dominoes) {
        char[] dominoChs = dominoes.toCharArray();
        int len = dominoChs.length, i = 0;
        char left = 'L';
        while (i < len) {
            int j = i;
            while (j < len && dominoChs[j] == '.') { // 找到一段连续的没有被推动的骨牌
                j++;
            }
            char right = j < len ? dominoChs[j] : 'R';
            if (left == right) { // 方向相同，那么这些竖立骨牌也会倒向同一方向
                while (i < j) {
                    dominoChs[i++] = right;
                }
            } else if (left == 'R' && right == 'L') { // 方向相对，那么就从两侧向中间倒
                int k = j - 1;
                while (i < k) {
                    dominoChs[i++] = 'R';
                    dominoChs[k--] = 'L';
                }
            }
            left = right;
            i = j + 1;
        }
        return String.valueOf(dominoChs);
    }

    public static void main(String[] args) {
        Solutions solutions = new Solutions();
        //String res = solutions.pushDominoes(".L.R...LR..L..");
        String res = solutions.pushDominoes("..R..");
        System.out.println(res);
    }
}
