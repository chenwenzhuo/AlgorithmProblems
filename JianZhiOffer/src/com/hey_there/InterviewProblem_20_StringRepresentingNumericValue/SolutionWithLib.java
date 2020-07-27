package com.hey_there.InterviewProblem_20_StringRepresentingNumericValue;

//跑不通
public class SolutionWithLib {
    public boolean isNumber(String s) {
        int first_e = s.indexOf('e');
        int last_e = s.lastIndexOf('e');
        //s不能以e字符开始和结尾
        if (first_e == 0 || last_e == s.length() - 1) {
            return false;
        }
        //将输入字符串按字符'e'分割为底数和指数两段
        String[] sParts = s.split("e");
        /* 分割后的数组长度大于2，说明s中有多个'e'字符，为非法
         * 分割后的数组长度为0，说明s中仅有'e'字符，为非法*/
        if (sParts.length > 2 || sParts.length == 0) {
            return false;
        }
        //分别检查底数和指数
        try {
            //在有指数的情况下，底数不能以空格结尾
            if (sParts.length == 2) {
                int last_space = sParts[0].lastIndexOf(' ');
                if (last_space == sParts[0].length() - 1) {
                    return false;
                }
            }
            double baseValue = Double.parseDouble(sParts[0]);
        } catch (NumberFormatException numberFormatException) {
            //发生异常则表示字符串无法转换为有效的数字
            return false;
        }
        if (sParts.length == 2) {
            try {
                //底数不能以空格开头
                int first_space = sParts[1].indexOf(' ');
                if (first_space == 0) {
                    return false;
                }
                //去除指数字符串中结尾处的空格
                int nonSpaceIdx = sParts[1].length() - 1;
                while (sParts[1].charAt(nonSpaceIdx) == ' ') {
                    nonSpaceIdx--;
                }
                int expValue = Integer.parseInt(sParts[1].substring(0, nonSpaceIdx + 1));
            } catch (NumberFormatException numberFormatException) {
                //发生异常则表示字符串无法转换为有效的数字
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //String s = "-1.e49046 ";
        String s = "959440.94f";
        SolutionWithLib solution = new SolutionWithLib();
        boolean res = solution.isNumber(s);
        System.out.println(res);
        double ds = Double.parseDouble(s);
        System.out.println(ds);
    }
}
