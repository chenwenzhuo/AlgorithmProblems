package com.heythere;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入您想生成多少对括号的组合：");
        int parenthesesPairs = input.nextInt();

        BestSolution bestSolution = new BestSolution();
        List<String> combinations = bestSolution.generateParenthesis(parenthesesPairs);

        System.out.println("[");
        for (String combination : combinations) {
            System.out.println("   " + combination);
        }
        System.out.println("]");
    }
}
