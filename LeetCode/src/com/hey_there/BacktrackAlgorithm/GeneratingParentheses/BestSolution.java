package com.hey_there.BacktrackAlgorithm.GeneratingParentheses;

import java.util.ArrayList;
import java.util.List;

public class BestSolution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        helper("",0,0,n,ans);
        return ans;
    }

    public void helper(String s, int open, int close, int n, List<String> ans) {
        if(s.length() == 2*n) {
            ans.add(s);
            return;
        }
        if(open < n) {
            helper(s+"(",open+1,close,n,ans);
        }
        if(close < open) {
            helper(s+")",open,close+1,n,ans);
        }
    }
}
