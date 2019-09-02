package com.heythere;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solutions solutions = new Solutions();
        List<String> resultStrings = solutions.letterCombinations("372");
        System.out.println(resultStrings);
        System.out.println(resultStrings.size());
    }
}
