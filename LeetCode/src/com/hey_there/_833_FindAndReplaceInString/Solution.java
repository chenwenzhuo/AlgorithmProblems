package com.hey_there._833_FindAndReplaceInString;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    //封装一次替换操作
    private class Operation {
        int start, end;//原字符串中的起始、结束下标，左闭右开区间
        String source, target;//替换的源、目标字符串

        public Operation(int start, int end, String source, String target) {
            this.start = start;
            this.end = end;
            this.source = source;
            this.target = target;
        }
    }

    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int k = indices.length;//操作的总数量
        int len_s = s.length();
        //将所有操作封装成一个Operation数组，并按起始下标升序排序
        Operation[] allOperations = new Operation[k];
        for (int i = 0; i < k; i++)
            allOperations[i] = new Operation(indices[i], indices[i] + sources[i].length(),
                    sources[i], targets[i]);
        Arrays.sort(allOperations, (o1, o2) -> o1.start - o2.start);

        //筛选所有有效操作
        ArrayList<Operation> validOperations = new ArrayList<>();
        for (Operation op : allOperations) {
            if (op.end <= len_s && s.substring(op.start, op.end).equals(op.source))
                validOperations.add(op);
        }
        //逐个应用队列中的替换操作
        StringBuilder builder = new StringBuilder(s);
        int offset = 0;//替换后长度可能发生变化，需要对下标进行修正
        for (Operation op : validOperations) {
            //加上偏移修正量之后的替换起始、终止下标
            int st_offset = op.start + offset;
            int ed_offset = op.end + offset;
            builder.replace(st_offset, ed_offset, op.target);
            //更新offset修正量
            offset += (op.target.length() - (ed_offset - st_offset));
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        /*String s = "abcd";
        int[] indices = {0, 2};
        String[] sources = {"a", "cd"};
        String[] targets = {"eee", "ffff"};*/
        String s = "vmokgggqzp";
        int[] indices = {0, 2};
        String[] sources = {"a", "cd"};
        String[] targets = {"eee", "ffff"};
        String res = new Solution().findReplaceString(s, indices, sources, targets);
        System.out.println(res);
    }
}
