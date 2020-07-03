package com.hey_there.RestoreIPAddresses;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int len_s;
    private char[] chs;
    private List<String> ipAddresses;

    public List<String> restoreIpAddresses(String s) {
        this.len_s = s.length();
        this.chs = s.toCharArray();
        this.ipAddresses = new ArrayList<>();
        backtrack(0, new StringBuilder(), 0);
        return ipAddresses;
    }

    private void backtrack(int start, StringBuilder ipBuilder, int dots) {
        if (start == len_s) {
            String ip = ipBuilder.toString();
            if (isValidIPAddress(ip)) {
                ipAddresses.add(ip);
            }
            return;
        }
        //记录ipBuilder的初始长度
        int len_ipbInit = ipBuilder.length();
        //当ipBuilder中有3个点符号时，当前应该将剩余所有数字加入ipBuilder
        if (dots == 3) {
            int idx = start;
            while (idx < len_s) {
                ipBuilder.append(chs[idx]);
                idx++;
            }
            backtrack(len_s, ipBuilder, dots);
            //返回上一级递归前，将此次递归加入ipBuilder的字符删除
            ipBuilder.delete(len_ipbInit, ipBuilder.length());
            return;
        }
        //当ipBuilder中点符号少于3个时，每次取一个字符加入ipBuilder
        for (int i = 0; i < 3; i++) {//ip地址每一段长度最多为3
            if (start + i < len_s) {
                ipBuilder.append(chs[start + i]);
                ipBuilder.append(".");
                backtrack(start + i + 1, ipBuilder, dots + 1);
                //移除点符号
                ipBuilder.delete(ipBuilder.length() - 1, ipBuilder.length());
            }
        }
        //返回上一级递归前，将此次递归加入ipBuilder的字符删除
        ipBuilder.delete(len_ipbInit, ipBuilder.length());
    }

    private boolean isValidIPAddress(String ipAddr) {
        //将ip地址以点分割成四个部分
        String[] sepIp = ipAddr.split("\\.");
        //正确的ip地址一定有且仅有4段
        if (sepIp.length != 4) {
            return false;
        }
        for (String ip : sepIp) {
            int len = ip.length();
            /* 对于ip地址的每一段，
             * 长度为0和长度大于3的为无效，
             * 值大于255的为无效，
             * 转换为整数再转回字符串，与原字符串不同，也为无效*/
            if (len > 3 || len == 0) {
                return false;
            }
            //在判断长度合法后再进行值的转换，否则可能由于ip字符串过长导致val溢出
            int val = Integer.parseInt(ip);
            if (val > 255 || !ip.equals(String.valueOf(val))) {
                return false;
            }
        }
        return true;
    }

    private int[] toIntArray(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int[] ints = new int[len];
        for (int i = 0; i < len; len++) {
            ints[i] = Integer.parseInt(String.valueOf(chs[i]));
        }
        return ints;
    }

    public static void main(String[] args) {
        //String s = "25525511135";
        String s = "0000";
        Solution solution = new Solution();
        List<String> ipAddresses = solution.restoreIpAddresses(s);
        System.out.println(ipAddresses);
        System.out.println("00 val is  " + Integer.parseInt("010"));
    }
}
