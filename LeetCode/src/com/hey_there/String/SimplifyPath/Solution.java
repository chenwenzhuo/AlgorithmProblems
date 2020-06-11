package com.hey_there.String.SimplifyPath;

import java.util.ArrayDeque;

public class Solution {
    public String simplifyPath(String path) {
        //把字符串以"/"为分隔符分割成数组,此时数组有"路径"、""、"."、".."这四种情况
        String[] splitPath = path.split("/");
        ArrayDeque<String> stack = new ArrayDeque<>();
        for (String s : splitPath) {
            if (s.equals("..") && !stack.isEmpty()) {
                stack.pop();
            } else if (!s.equals("") && !s.equals(".") && !s.equals("..")) {
                stack.push(s);
            }
        }
        //栈为空，返回"/"
        if (stack.isEmpty()) {
            return "/";
        }
        //栈非空，将字符串用"/"连接后返回
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
            sb.insert(0, "/");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //String path = "/a/./b/../../c/";
        String path = "/home/";
        Solution solution = new Solution();
        String simplifiedPath = solution.simplifyPath(path);
        System.out.println(simplifiedPath);
    }
}
