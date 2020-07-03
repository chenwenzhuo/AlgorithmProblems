package com.hey_there.SatisfiabilityOfEqualityEquations;

public class Solution {
    //并查集
    public boolean equationsPossible(String[] equations) {
        int[] pres = new int[27];

        //首先处理等式，建立树状结构
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                continue;//跳过不等式
            }

            char var_1 = eq.charAt(0);
            char var_2 = eq.charAt(3);
            int order_var_1 = var_1 - 'a' + 1;
            int order_var_2 = var_2 - 'a' + 1;
            //若两个变量都没有前驱，此时可将两个变量的前驱均设为var_1
            if (pres[order_var_1] == 0 && pres[order_var_2] == 0) {
                pres[order_var_1] = order_var_1;
                pres[order_var_2] = order_var_1;
            }
            //若var_1有前驱，var_2没有
            else if (pres[order_var_1] != 0 && pres[order_var_2] == 0) {
                //此时将var_2的前驱设为var_1的前驱
                pres[order_var_2] = pres[order_var_1];
            }
            //若var_2有前驱，var_1没有
            else if (pres[order_var_2] != 0 && pres[order_var_1] == 0) {
                //此时将var_1的前驱设为var_2的前驱
                pres[order_var_1] = pres[order_var_2];
            }
            //若两个变量都有各自的前驱，且各自的前驱不相同
            else if (pres[order_var_1] != 0 && pres[order_var_2] != 0 &&
                    pres[order_var_1] != pres[order_var_2]) {
                //从var_1和var_2开始分别向上，找到其“根结点”，使var_1的“根结点”作为var_2的“根结点”的前驱
                int r_1 = order_var_1;
                while (pres[r_1] != r_1) {
                    r_1 = pres[r_1];
                }
                int r_2 = order_var_2;
                while (pres[r_2] != r_2) {
                    r_2 = pres[r_2];
                }
                pres[r_2] = pres[r_1];
            }
        }
        //进行路径压缩
        for (int i = 1; i < pres.length; i++) {
            //当前pres[i]为0，表示等式中未出现此变量，跳过
            if (pres[i] == 0) {
                continue;
            }
            //找到当前位置i的“根前驱”元素
            int root = i;
            while (pres[root] != root) {
                root = pres[root];
            }
            //从当前位置i开始向上，将路径上所有元素的前驱设为root
            int pos = i, temp;
            while (pos != root) {
                temp = pres[pos];
                pres[pos] = root;
                pos = temp;
            }
        }
        //处理不等式
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                continue;//跳过等式
            }
            char var_1 = eq.charAt(0);
            char var_2 = eq.charAt(3);
            //两个相同的变量不相等，矛盾，返回false
            if (var_1 == var_2) {
                return false;
            }
            int order_var_1 = var_1 - 'a' + 1;
            int order_var_2 = var_2 - 'a' + 1;
            //若两个不相等的变量具有相同的前驱，则产生矛盾，返回false
            if (pres[order_var_1] == pres[order_var_2] && pres[order_var_1] != 0) {
                return false;
            }
        }
        //没有产生矛盾，返回true
        return true;
    }

    public static void main(String[] args) {
        //String[] equations = {"a==b", "b==c", "a==c"};
        //String[] equations = {"a==b","b!=a"};
        //String[] equations = {"a==b", "b!=c", "c==a"};
        String[] equations = {"h!=e", "d==e", "c==k", "b!=h", "i==k", "b==k", "a!=g", "d==k", "f!=g", "g!=a", "e==i"};
        Solution solution = new Solution();
        boolean ans = solution.equationsPossible(equations);
        System.out.println(ans);
    }
}
