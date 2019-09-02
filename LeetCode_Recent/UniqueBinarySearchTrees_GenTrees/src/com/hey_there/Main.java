package com.hey_there;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        List<TreeNode> resultTrees = solution.generateTrees(3);
        for (TreeNode tree : resultTrees) {
            System.out.println(solution.non_recursivePreOrderTraversal(tree));
        }
    }
}
